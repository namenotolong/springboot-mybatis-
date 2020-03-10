package com.huyong.enums;

/**
 * 描述: 状态枚举
 *
 * @author huyong
 * @date 2020-03-10 8:55 下午
 */
public enum StatusEnum {
    /**
     * 存在
     */
    PRESENT(0, "存在"),
    /**
     * 删除
     */
    DELETE(1, "已删除"),
    ;
    private int code;
    private String desc;
    StatusEnum(int code, String desc) {
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
