package com.huyong.enums;

/**
 * 描述: 引用文件实体枚举
 *
 * @author huyong
 * @date 2020-03-10 8:55 下午
 */
public enum FileRefEnum {
    /**
     * 博客
     */
    ARTICLE(0, "ARTICLE"),
    /**
     * 聊天
     */
    CHAT(1, "CHAT"),
    /**
     * 评论回复
     */
    TOPIC(2, "TOPIC"),
    ;
    private int code;
    private String desc;
    FileRefEnum(int code, String desc) {
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
