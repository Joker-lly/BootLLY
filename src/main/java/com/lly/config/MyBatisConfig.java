package com.lly.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.lly.plugins.MyPagePlugin;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * 数据库配置文件
 * @author Joker-lly
 * @since 2020-06-07
 */
@MapperScan("com.mapper")
@Configuration
public class MyBatisConfig {
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(@Autowired DataSource dataSource) throws IOException {
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver=new PathMatchingResourcePatternResolver();
        String packageXMLConfigPath = PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + "Mapper/*.xml";
        bean.setPlugins(new Interceptor[]{getMyPagePlugin()});
        bean.setMapperLocations(resolver.getResources(packageXMLConfigPath));
        return bean;
    }

    @Bean
    public Interceptor getMyPagePlugin(){
        MyPagePlugin myPagePlugin=new MyPagePlugin();
        myPagePlugin.setDatabaseType("mysql");
        myPagePlugin.setPageSqlId(".*ByPage$");
        return myPagePlugin;
    }

    @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/springboot?characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setMaxActive(5);
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return dataSource;
    }






















}
