package com.itheima.filter;

import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;


/**
 * 过滤器类
 */
@Slf4j
//@WebFilter(filterName = "loginfilter",urlPatterns = "/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("过滤器启动~~~~~~");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取url
        String uri = request.getRequestURI();
        //判断是否为login操作
        if (uri.contains("login")){
            filterChain.doFilter(request,response);
            return;
        }

        String jwt = request.getHeader("token");
        //判断是否携带jwt
        if (jwt == null){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //解析jwt
        try {
            Claims claims = JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            log.error(e.toString());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        //放行
        filterChain.doFilter(request,response);
        log.info("放行后。。。。。。。。。。。。。。。");
    }

}
