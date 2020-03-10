package com.huyong.enums;

/**
 * 描述: 聊天信息类别枚举
 *
 * @author huyong
 * @date 2020-03-10 9:00 下午
 */
public enum MsgTypeEnum {
    /**
     * 文本
     */
    TEXT(0, "text"),
    /**
     * 图片
     */
    PICTURE(1, "picture"),
    ;
    private int code;
    private String desc;
    MsgTypeEnum(int code, String desc) {
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
