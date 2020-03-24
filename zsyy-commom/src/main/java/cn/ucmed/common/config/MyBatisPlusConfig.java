package cn.ucmed.common.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatisPlus 配置
 */
@Configuration
public class MyBatisPlusConfig {

    @Bean
    public ISqlInjector sqlInjector() {
        // 逻辑删除
        return new LogicSqlInjector();
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        //分页拦截器
        return new PaginationInterceptor();
    }

}
