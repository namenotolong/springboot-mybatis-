package com.huyong.enums;

/**
 * 描述: 文章类型
 *
 * @author huyong
 * @date 2020-03-10 8:55 下午
 */
public enum ArticleTypeEnum {
    /**
     * 草稿
     */
    DRAFT(0, "草稿"),
    /**
     * 右边
     */
    TEXT(1, "正文"),
    ;
    private int code;
    private String desc;
    ArticleTypeEnum(int code, String desc) {
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
