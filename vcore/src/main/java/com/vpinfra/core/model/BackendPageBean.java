package com.vpinfra.core.model;

import java.io.Serializable;
import java.util.List;

/**
 * 分页模型，后台表格分页中使用
 *
 * @author 尹俊峰
 * @date 2016年11月10日
 * @since 2.1.1
 */
public class BackendPageBean implements Serializable {

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
     * 总页数
     */
    private Integer page;

    /**
     * 当前页
     */
    private transient Integer pageNumber;

    /**
     * 总数量
     */
    private Long total;

    /**
     * 查询结果
     */
    private List<?> rows;

    public BackendPageBean builder() {
        return new BackendPageBean();
    }

    public BackendPageBean build() {
        return this;
    }

    public Integer getLimit() {
        return limit;
    }

    public BackendPageBean limit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public Integer getOffset() {
        return offset;
    }

    public BackendPageBean offset(Integer offset) {
        this.offset = offset;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public BackendPageBean page(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getPageNumber() {
        if (null == limit || offset == null) {
            pageNumber = 1;
        } else if (limit != 0) {
            pageNumber = offset / limit + 1;
        }
        return pageNumber;
    }

    public Long getTotal() {
        return total;
    }

    public BackendPageBean total(Long total) {
        this.total = total;
        return this;
    }

    public List<?> getRows() {
        return rows;
    }

    public BackendPageBean rows(List<?> rows) {
        this.rows = rows;
        return this;
    }
}
