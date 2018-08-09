package com.vpinfra.platform.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

/**
 * @author <a href="mailto:yinjunfeng@vpgame.cn">Yinjf</a>
 * @date 2018年5月28日
 */
@Configuration
@MapperScan(basePackages = {"com.vpinfra.platform.module.mapper", "com.vpinfra.platform.module.repository"}, sqlSessionTemplateRef  = "vpmsSqlSessionTemplate")
public class VpmsDbConfig {

    @Bean(name = "vapiDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.vapi")
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "vapiSqlSessionFactory")
    @Primary
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("vapiDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/vapi/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "vapiTransactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("vapiDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "vapiSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("vapiSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
