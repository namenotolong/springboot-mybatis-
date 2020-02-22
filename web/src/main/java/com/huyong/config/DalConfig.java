package com.huyong.config;

import com.huyong.interceptor.SqlLogInterceptor;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;


/**
 * @author huyong
 */
@Component
@MapperScan(basePackages = {"com.huyong.dao.mapper"},sqlSessionFactoryRef = "demoSqlSessionFactory")
public class DalConfig {

    @Resource(name = "demoDataSource")
    private DataSource demoJdbcTemplate;

    @Resource
    private SqlLogInterceptor sqlLogInterceptor;

    @Resource(name = "demoConfiguration")
    private Configuration configuration;

    @Bean(name = "demoJdbcTemplate")
    public JdbcTemplate demoJdbcTemplate(){
        return new JdbcTemplate(demoJdbcTemplate);
    }

    @Bean(name="demoSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory()throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(demoJdbcTemplate);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.huyong.dao.entity");
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
        sqlSessionFactoryBean.setPlugins(sqlLogInterceptor);
        sqlSessionFactoryBean.setConfiguration(configuration);
        return sqlSessionFactoryBean.getObject();

    }

    @Bean(name = "transactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(demoJdbcTemplate);
    }

    @Bean(name = "demoConfiguration")
    public Configuration setConfiguration() {
        final Configuration configuration = new Configuration();
        //设置驼峰命名
        configuration.setMapUnderscoreToCamelCase(true);
        return configuration;
    }


}

    
