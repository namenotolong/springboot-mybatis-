package com.huyong.enums;

/**
 * 描述: 文件类型枚举
 *
 * @author huyong
 * @date 2020-03-10 8:55 下午
 */
public enum FileTypeEnum {
    /**
     * JPG
     */
    JPG(0, "JPG"),
    /**
     * VIDEO
     */
    VIDEO(1, "VIDEO"),
    ;
    private int code;
    private String desc;
    FileTypeEnum(int code, String desc) {
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
