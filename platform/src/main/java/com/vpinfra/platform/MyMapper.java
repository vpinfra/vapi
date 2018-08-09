package com.vpinfra.platform;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author <a href="mailto:yinjunfeng@vpgame.cn">Yinjf</a>
 * @date 2018年5月28日
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T>{
    
}
