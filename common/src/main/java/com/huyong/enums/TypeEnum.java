package com.huyong.enums;

/**
 * 描述: 消息状态枚举
 *
 * @author huyong
 * @date 2020-03-10 8:55 下午
 */
public enum TypeEnum {
    /**
     * 未读
     */
    PRESENT(0, "未读"),
    /**
     * 已读
     */
    DELETE(1, "已读"),
    ;
    private int code;
    private String desc;
    TypeEnum(int code, String desc) {
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
