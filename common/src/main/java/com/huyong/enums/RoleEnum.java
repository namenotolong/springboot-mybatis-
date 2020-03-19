package com.huyong.enums;

/**
 * 描述: 角色枚举
 *
 * @author huyong
 * @date 2020-03-10 8:55 下午
 */
public enum RoleEnum {
    /**
     * 普通用户
     */
    CUSTOMER(0, "普通用户"),
    /**
     * 管理员
     */
    ADMIN(1, "管理员"),
    ;
    private int code;
    private String desc;
    RoleEnum(int code, String desc) {
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
