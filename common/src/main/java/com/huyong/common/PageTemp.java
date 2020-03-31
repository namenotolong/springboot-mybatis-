package com.huyong.common;

import lombok.Data;

import java.util.List;

/**
 * 描述: 分页
 *
 * @author huyong
 * @date 2020-02-19 3:46 下午
 */
@Data
public class PageTemp<T> {
    /**
     * 数据量
     */
    private int count;
    /**
     * 数据
     */
    private List<T> data;
    /**
     * 每页数量
     */
    private int pageSize;
    /**
     * 当前页数
     */
    private int pageNum;
    /**
     * 最大页
     */
    private int maxPage;
    /**
     * 是否有下一页
     */
    private boolean hasNext;
    /**
     * 是否有上一页
     */
    private boolean hasPrevious;
    public PageTemp(){}
    public PageTemp(List<T> data, int pageSize, int pageNum, int count) {
        if (pageNum <= 0) {
            pageNum = 1;
        }
        if (pageSize <= 0) {
            pageSize = 1;
        }
        this.count = count;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.data = data;
        this.maxPage = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
        this.hasNext = this.maxPage > this.pageNum;
        this.hasPrevious = this.pageNum > 1;
    }
}
