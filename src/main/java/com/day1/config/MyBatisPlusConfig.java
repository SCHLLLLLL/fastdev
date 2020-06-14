package com.day1.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author: Day1
 * @Date: 2020/6/13 23:51
 * @Description:
 */

@Configuration
@EnableTransactionManagement
@MapperScan("com.day1.mapper")
public class MyBatisPlusConfig {

    //PaginationInterceptor是一个分页插件。
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }

}
