package com.huyong.enums;

/**
 * 描述: 聊天信息类别枚举
 *
 * @author huyong
 * @date 2020-03-10 9:00 下午
 */
public enum EventTypeEnum {
    /**
     * 评论
     */
    REPLY(0, "common"),
    /**
     * 私信
     */
    MESSAGE(1, "message"),
    /**
     * 关注
     */
    FOLLOW(2, "follow"),
    /**
     * 赞
     */
    PRAISE(3, "praise"),
    /**
     * 系统消息
     */
    SYSTEM(4, "system"),
    ;
    private int code;
    private String desc;
    EventTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
