package com.huyong.service;

import com.huyong.constant.CommonConstant;
import com.huyong.dao.entity.EmailAdviceDO;
import com.huyong.dao.helper.Sort;
import com.huyong.dao.mapper.EmailAdviceMapper;
import com.huyong.enums.EmailTypeEnum;
import com.huyong.exception.CommonException;
import com.huyong.utils.DataUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述: 邮件通知
 *
 * @author huyong
 * @date 2020-03-20 1:43 下午
 */
@Service
public class EmailAdviceService {
    @Resource
    private EmailAdviceMapper emailAdviceMapper;
    @Resource
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String from;

    /**
     * 返送验证码
     * @param email
     * @return
     */
    public void sendCode(String email) {
        String random = DataUtils.getRandom();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email);
        message.setSubject("验证码");
        message.setText("本次验证码为：" + random + ",有效时长为" + CommonConstant.CODE_EXPIRE_TIME + "分钟。");
        mailSender.send(message);
        insertEmailAdvice(from, email, random, EmailTypeEnum.CODE.getCode(), System.currentTimeMillis() + CommonConstant.CODE_EXPIRE_TIME * 60 * 1000);
    }

    /**
     * 获取用户的最近一次的验证码
     * @param email
     * @return
     */
    private EmailAdviceDO getCode(String email) {
        EmailAdviceDO condition = new EmailAdviceDO();
        condition.setOwner(email);
        condition.setType(EmailTypeEnum.CODE.getCode());
        Sort sort = new Sort("create_time", Sort.SortType.DESC);
        List<EmailAdviceDO> advices = emailAdviceMapper.queryByPageWithSort(condition, 0, 1, sort);
        if (CollectionUtils.isNotEmpty(advices)) {
            return advices.get(0);
        }
        return null;
    }

    public void checkCode(String email, String code) {
        EmailAdviceDO advice = getCode(email);
        if (null == code) {
            throw new CommonException("请获取验证码！");
        }
        if (!advice.getData().equals(code)) {
            throw new CommonException("验证码不正确！");
        }
        if (System.currentTimeMillis() > advice.getExpireTime()) {
            throw new CommonException("验证码过期！");
        }
    }

    /**
     * 存储邮箱通知事件
     * @param from
     * @param to
     * @param data
     */
    private void insertEmailAdvice(String from, String to, String data, int type) {
        insertEmailAdvice(from, to, data, type, null);
    }
    private void insertEmailAdvice(String from, String to, String data, int type, Long expireTime) {
        EmailAdviceDO advice = new EmailAdviceDO();
        advice.setFrom(from);
        advice.setData(data);
        advice.setOwner(to);
        advice.setExpireTime(expireTime);
        advice.setType(type);
        emailAdviceMapper.insert(advice);
    }
}
