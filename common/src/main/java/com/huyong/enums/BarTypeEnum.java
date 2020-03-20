package com.huyong.enums;

/**
 * 描述: 导航类型
 *
 * @author huyong
 * @date 2020-03-10 8:55 下午
 */
public enum BarTypeEnum {
    /**
     * 左边
     */
    LEFT(0, "left"),
    /**
     * 右边
     */
    RIGHT(1, "right"),
    ;
    private int code;
    private String desc;
    BarTypeEnum(int code, String desc) {
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
