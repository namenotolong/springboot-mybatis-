package com.huyong.enums;

/**
 * 描述: 话题枚举
 *
 * @author huyong
 * @date 2020-03-10 9:00 下午
 */
public enum TopicEnum {
    /**
     * 评论
     */
    COMMON(0, "评论"),
    /**
     * 回复
     */
    REPLY(1, "回复"),
    ;
    private int code;
    private String desc;
    TopicEnum(int code, String desc) {
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
