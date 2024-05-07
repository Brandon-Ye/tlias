package com.itheima.interceptor;

import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 拦截器类
 */
@Slf4j
@Component
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取url
        String uri = request.getRequestURI();

        String jwt = request.getHeader("token");
        //判断是否携带jwt
        if (jwt == null){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        //解析jwt
        try {
            Claims claims = JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            log.error(e.toString());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        return true;
    }
}
