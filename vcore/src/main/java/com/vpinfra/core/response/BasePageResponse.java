package com.vpinfra.core.response;


import com.vpinfra.core.model.FrontendPageBean;

/**
 * 分页返回基类，对外接口中使用
 *
 * @author 尹俊峰
 * @date 2017年6月23日
 * @since 2.1.1
 */
public class BasePageResponse<T> extends BaseResponse<T>{

    /**
     * 分页信息
     */
    private FrontendPageBean paging;

    /**
     * 构造 BasePageResponse 的静态方法
     *
     * <pre>
     * BasePageResponse.builder().data("some data..").code(200)
     * </pre>
     *
     * @return BasePageResponse
     */
    public static BasePageResponse builder() {
        return new BasePageResponse();
    }

    public BasePageResponse build() {
        return this;
    }

    public FrontendPageBean getPaging() {
        return paging;
    }

    public BasePageResponse paging(FrontendPageBean paging) {
        this.paging = paging;
        return this;
    }

}
