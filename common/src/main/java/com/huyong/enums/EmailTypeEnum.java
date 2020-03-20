package com.huyong.enums;

/**
 * 描述: 邮件通知类型
 *
 * @author huyong
 * @date 2020-03-10 8:55 下午
 */
public enum EmailTypeEnum {
    /**
     * 验证码
     */
    CODE(0, "ARTICLE"),
    /**
     * 消息
     */
    MESSAGE(1, "消息"),
    ;
    private int code;
    private String desc;
    EmailTypeEnum(int code, String desc) {
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
