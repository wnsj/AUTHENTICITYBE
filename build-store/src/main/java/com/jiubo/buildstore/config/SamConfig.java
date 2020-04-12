package com.jiubo.buildstore.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @desc:
 * @date: 2019-09-07 13:17
 * @author: dx
 * @version: 1.0
 */
@Configuration
public class SamConfig {

    //分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        //List<ISqlParser> sqlParserList = new ArrayList<>();
        // 攻击 SQL 阻断解析器、加入解析链
        //sqlParserList.add(new BlockAttackSqlParser());
        //paginationInterceptor.setSqlParserList(sqlParserList);
        return paginationInterceptor;
    }

}
