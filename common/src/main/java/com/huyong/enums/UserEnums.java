package com.huyong.enums;


/**
 * 描述: 用户角色
 *
 * @author huyong
 * @date 2020-02-21 9:28 下午
 */
public enum UserEnums {
    /**
     * 游客
     */
    VISITOR(1, "游客"),
    /**
     * 普通用户
     */
    COMMON(2, "普通用户"),
    /**
     * 管理员
     */
    ADMIN(3, "管理员"),
    /**
     * 自己
     */
    OWN(4, "自己");
    /**
     * 角色代码
     */
    private int code;
    /**
     * 角色名称
     */
    private String actor;
    UserEnums(int code, String actor){
        this.code = code;
        this.actor = actor;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    @Override
    public String toString() {
        return "UserEnums{" +
                "code=" + code +
                ", actor='" + actor + '\'' +
                '}';
    }
}
