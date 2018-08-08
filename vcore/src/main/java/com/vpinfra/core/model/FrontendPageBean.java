package com.vpinfra.core.model;

import java.io.Serializable;

/**
 * 前端分页模型
 *
 * @author 尹俊峰
 * @date 2017年6月26日
 * @since 2.1.1
 */
public class FrontendPageBean implements Serializable {

    private static final long serialVersionUID = 3882784222696522898L;

    /**
     * 每页条数
     */
    private Integer limit;

    /**
     * 起始条数
     */
    private Integer offset;

    /**
     * 总数量
     */
    private Long total;

    /**
     * 当前页
     */
    private transient Integer pageNumber;

    public FrontendPageBean builder() {
        return new FrontendPageBean();
    }

    public FrontendPageBean build() {
        return this;
    }

    public Integer getLimit() {
        return limit;
    }

    public FrontendPageBean limit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public Integer getOffset() {
        return offset;
    }

    public FrontendPageBean offset(Integer offset) {
        this.offset = offset;
        return this;
    }

    public Long getTotal() {
        return total;
    }

    public FrontendPageBean setTotal(Long total) {
        this.total = total;
        return this;
    }

    public Integer getPageNumber() {
        if (null == limit || null == offset) {
            pageNumber = 1;
        } else if (limit != 0) {
            pageNumber = offset / limit + 1;
        }
        return pageNumber;
    }
}
