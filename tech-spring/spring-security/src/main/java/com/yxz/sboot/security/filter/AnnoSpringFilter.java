package com.yxz.sboot.security.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Description TODO
 * @Date 2025-06-09
 * @Created by Yolo
 */
@Slf4j
@WebFilter
public class AnnoSpringFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("this is my spring filter ....");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
