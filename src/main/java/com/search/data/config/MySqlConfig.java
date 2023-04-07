package com.search.data.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


@Configuration
public class MySqlConfig {

    private final static Logger logger= LoggerFactory.getLogger(MySqlConfig.class);
    @Value("${spring.datasource.username}")
    private  String mySqlUserName;
    @Value("${spring.datasource.driver-class-name}")
    private String mySqlDriverName;
    @Value("${spring.datasource.password}")
    private String mySqlPassword;
    @Value("${spring.datasource.url}")
    private String mySqlUrl;


    @Bean
  //  @Primary
    @Qualifier("searchData")
    public DataSource sqlConfig(){

        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setDriverClassName(mySqlDriverName);
        dataSource.setPassword(mySqlPassword);
        dataSource.setUrl(mySqlUrl);
        dataSource.setUsername(mySqlUserName);
        logger.info("---------Data Source driver created-----");
        return dataSource;
    }

    @Bean
    @Qualifier("searchDataJdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("searchData") DataSource dataSource) {
        logger.info("---------MySql connection created-----");
        return new JdbcTemplate(dataSource);
    }
}
