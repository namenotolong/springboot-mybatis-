package com.huyong.config;

import com.alibaba.fastjson.JSONObject;
import com.huyong.dao.entity.EventDO;
import com.huyong.dao.entity.HistoryChatUsersDO;
import com.huyong.dao.entity.UserDO;
import com.huyong.dao.mapper.EventMapper;
import com.huyong.dao.mapper.HistoryChatUsersMapper;
import com.huyong.dao.mapper.UserMapper;
import com.huyong.enums.EventTypeEnum;
import com.huyong.enums.OnlineEnum;
import com.huyong.enums.StatusEnum;
import com.huyong.utils.SpringUtils;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


/**
 * @author huyong
 */
@ServerEndpoint(value = "/websocket/{id}")
@Component
@Data
public class MyWebSocket {

    /**
     * 在线人数
     */
    public static int onlineNumber = 0;
    private Session session;
    private Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyWebSocket)) return false;
        MyWebSocket that = (MyWebSocket) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    /**
     * 所有的对象
     */
    public static Set<MyWebSocket> webSockets = new LinkedHashSet<>();


    /**
     * 建立连接
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("id") Long id) throws IOException {
        this.session = session;
        this.id = id;
        if (!webSockets.contains(this)) {
            setOnline(id, OnlineEnum.ONLINE.getCode());
            onlineNumber++;
            webSockets.add(this);
        }
    }

    /**
     * 更改id的用户为在线
     * @param id
     */
    public void setOnline(Long id, int status) {
        UserDO userDO = SpringUtils.getBean(UserMapper.class).selectByPrimary(id);
        if (null != userDO) {
            userDO.setOnline(status);
            SpringUtils.getBean(UserMapper.class).updateByPrimary(userDO);
        }
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose() {
        setOnline(this.id, OnlineEnum.OFFLINE.getCode());
        onlineNumber--;
        webSockets.remove(this);
        System.out.println("有连接关闭！ 当前在线人数" + onlineNumber);
    }

    /**
     * 收到客户端的消息
     *
     * @param message 消息
     * @param session 会话
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        //存储 如果对方在线 提示对方
        EventDO save = save(message);
        sendMessage(JSONObject.toJSONString(save), save.getToUserId());
        System.out.println("来自客户端消息：" + message);
    }

    /**
     * 将消息存进db，在接收人中增加最近联系人
     * @param message
     */
    public EventDO save(String message) {
        EventDO eventDO = JSONObject.parseObject(message, EventDO.class);
        eventDO.setFromUserId(this.id);
        eventDO.setStatus(0);
        eventDO.setType(EventTypeEnum.MESSAGE.getCode());
        // 存储到数据库
        SpringUtils.getBean(EventMapper.class).insert(eventDO);
        //增加toUser的最近联系人
        HistoryChatUsersDO condition = new HistoryChatUsersDO();
        condition.setUserId(eventDO.getToUserId());
        condition.setCustomer(eventDO.getFromUserId());
        condition.setStatus(StatusEnum.PRESENT.getCode());
        HistoryChatUsersMapper bean = SpringUtils.getBean(HistoryChatUsersMapper.class);
        List<HistoryChatUsersDO> list = bean.queryByCondition(condition);
        if (CollectionUtils.isEmpty(list)) {
            bean.insert(condition);
        }
        return eventDO;
    }
    /**
     * 提示通知者浏览器
     * @param message
     * @param id
     */
    private void sendMessage(String message, Long id) {
        for (MyWebSocket webSocket : webSockets) {
            if (webSocket.getId().equals(id)) {
                try {
                    webSocket.getSession().getBasicRemote().sendText(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 发送消息
     *
     * @param message 消息
     */
    public void sendMessage(String message) {
        webSockets.forEach(x -> {
            try {
                x.getSession().getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
