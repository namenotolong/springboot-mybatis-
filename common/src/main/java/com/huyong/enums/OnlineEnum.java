package com.huyong.enums;

/**
 * 描述: 上线离线状态枚举
 *
 * @author huyong
 * @date 2020-03-10 8:55 下午
 */
public enum OnlineEnum {
    /**
     * 未读
     */
    ONLINE(0, "上线"),
    /**
     * 已读
     */
    OFFLINE(1, "离线"),
    ;
    private int code;
    private String desc;
    OnlineEnum(int code, String desc) {
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
