package com.huyong.enums;

/**
 * 描述: 操作枚举
 *
 * @author huyong
 * @date 2020-03-10 9:00 下午
 */
public enum OpsEnum {
    /**
     * insert
     */
    INSERT(0, "新增"),
    /**
     * update
     */
    UPDATE(1, "更新"),
    ;
    private int code;
    private String desc;
    OpsEnum(int code, String desc) {
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

    @Override
    public String toString() {
        return desc;
    }
}
