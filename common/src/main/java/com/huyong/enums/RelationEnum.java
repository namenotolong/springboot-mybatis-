package com.huyong.enums;

/**
 * 描述: 用户关系枚举
 *
 * @author huyong
 * @date 2020-03-10 9:00 下午
 */
public enum RelationEnum {
    /**
     * 关注 one -> userId
     */
    FOLLOW(0, "关注"),
    /**
     * 粉丝 one -> userId
     */
    FAN(1, "粉丝"),
    /**
     * 黑名单
     */
    BLACK(2, "黑名单"),
    /**
     * 好友
     */
    FRIEND(3, "好友"),
    /**
     * 收藏文章 one -> userId
     */
    STORE(4, "收藏"),
    /**
     * 被用户收藏文章 one -> articleId
     */
    BY_STORE(5, "被收藏"),
    /**
     * 点赞用户 one -> userId
     */
    PRAISE(6, "点赞"),
    /**
     * 被用户点赞 one -> otherId
     */
    BY_PRAISE(7, "被点赞"),
    /**
     * 点赞文章 one -> userId
     */
    PRAISE_ARTICLE(8, "点赞文章"),
    /**
     * 被用户点赞文章,one -> article_id
     */
    BY_PRAISE_ARTICLE(9, "被用户点赞文章"),
    /**
     * 点赞评论 one -> userId
     */
    PRAISE_TOPIC(10, "点赞评论"),
    /**
     * 被点赞评论 one -> topic_id
     */
    BY_PRAISE_TOPIC(11, "被点赞评论"),
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
