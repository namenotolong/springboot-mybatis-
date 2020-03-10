package com.huyong.enums;

/**
 * 描述: 用户关系枚举
 *
 * @author huyong
 * @date 2020-03-10 9:00 下午
 */
public enum RelationEnum {
    /**
     * 关注
     */
    MAN(0, "关注"),
    /**
     * 粉丝
     */
    WOMAN(1, "粉丝"),
    /**
     * 黑名单
     */
    BLACK(2, "黑名单"),
    /**
     * 好友
     */
    FRIEND(3, "好友"),
    ;
    private int code;
    private String desc;
    RelationEnum(int code, String desc) {
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
