package com.huyong.dao.helper;

/**
 * 描述: 排序类
 *
 * @author huyong
 * @date 2020-03-13 10:24 下午
 */
public class Sort {
    public Sort(String sortField, SortType sortType){
        this.sortField = sortField;
        this.sortType = sortType;
    }

    /**
     * 排序字段名称
     */
    private String sortField;

    /**
     * 排序类型 asc/desc
     */
    private SortType sortType = SortType.ASC;

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public SortType getSortType() {
        return sortType;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }

    public enum SortType{
        /**
         * asc
         */
        ASC("asc"),
        /**
         * desc
         */
        DESC("desc");

        SortType(String type){
            this.type = type;
        }

        private String type;

        public String getType() {
            return type;
        }
    }
}
