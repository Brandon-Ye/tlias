package com.itheima.aspect;

import com.itheima.entity.dto.OperateLog;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.itheima.annotation.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {

        OperateLog operateLog = new OperateLog();

        //获取操作时间
        operateLog.setOperateTime(LocalDateTime.now());
        //获取操作人id
        String token = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(token);
        operateLog.setOperateEmpId((Integer) claims.get("id"));
        //获取操作类名
        String name = joinPoint.getTarget().getClass().getName();
        operateLog.setClassName(name);
        //获取操作方法名
        String methodName = joinPoint.getSignature().getName();
        operateLog.setMethodName(methodName);
        //获取操作方法参数
        Object[] args = joinPoint.getArgs();
        operateLog.setMethodParams(Arrays.toString(args));
        //操作耗时
        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        operateLog.setCostTime(end-begin);
        //获取操作方法返回值
        operateLog.setReturnValue(result.toString());

        operateLogMapper.insert(operateLog);

        return result;
    }
}
