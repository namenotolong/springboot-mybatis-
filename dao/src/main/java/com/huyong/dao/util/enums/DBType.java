package com.huyong.dao.util.enums;

/**
 * 数据库类型
 * @author huyong
 */
public enum DBType {
    /**
     * mysql
     */
    MYSQL(0, "mysql"),
    /**
     * oracle
     */
    ORACLE(1, "oracle");

    DBType(final int type, final String desc){
        this.type = type;
        this.desc = desc;
    }

    private final int type;
    private final String desc;

    public int getType() {
        return type;
    }
    public String getDesc() {
        return desc;
    }
}
