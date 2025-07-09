package com.yxz.sboot.security.config;

import com.yxz.security.filter.ConfigBeanFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description TODO
 * @Date 2025-06-09
 * @Created by Yolo
 */
@Slf4j
@Configuration
public class FilterConfig {


    @Bean
    public FilterRegistrationBean configBeanFilter() {
        FilterRegistrationBean<ConfigBeanFilter> registration = new FilterRegistrationBean<>();
        registration.addUrlPatterns("/*");
        registration.setFilter(new ConfigBeanFilter());
        registration.setName("configBeanFilter");
        log.info(String.format("配置过滤器【%s】至过滤器链...", ConfigBeanFilter.class.getName()));
        return registration;
    }

}
