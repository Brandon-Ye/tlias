package com.itheima.aspect;

import com.itheima.entity.dto.EmpLoginDto;
import com.itheima.entity.dto.OperateLog;
import com.itheima.entity.po.EmpLoginLog;
import com.itheima.entity.vo.EmpLoginVo;
import com.itheima.entity.vo.Result;
import com.itheima.mapper.OperateLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 登录日志切面类
 */
@Aspect
@Component
@Slf4j
public class LoginAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @After("execution(* com.itheima.controller.LoginController.loginCheck(*))")
    public void recordLogin(JoinPoint joinPoint) throws Throwable {
        EmpLoginLog empLoginLog = new EmpLoginLog();
        //获取传入参数
        Object[] args = joinPoint.getArgs();
        List list = Arrays.asList(args);
        for (Object arg : list) {
            if (arg instanceof EmpLoginDto){
                EmpLoginDto empLogin = (EmpLoginDto) arg;
                //登录名
                empLoginLog.setUsername(empLogin.getUsername());
                //登录密码
                empLoginLog.setPassword(empLogin.getPassword());
            }
        }
        //获取登录时间
        empLoginLog.setLoginTime(LocalDateTime.now());
        //登录耗时
        long begin = System.currentTimeMillis();
        ProceedingJoinPoint joinPoint1 = (ProceedingJoinPoint) joinPoint;
        long end = System.currentTimeMillis();
        empLoginLog.setCostTime(end-begin);
        //是否登录成功
        Object result = joinPoint1.proceed();
        Object data = null;
//        List<Object> list1 = Arrays.asList(result);
//        for (Object object : list1) {
//            if (object instanceof Result){
//                Result result1 = (Result) object;
//                empLoginLog.setIsSuccess(result1.getCode().shortValue());
//                data = result1.getData();
//            }
//        }
        if (result instanceof Result){
            Result result1 = (Result) result;
            empLoginLog.setIsSuccess(result1.getCode().shortValue());
            data = result1.getData();
        }
        //成功后, 下发的JWT令牌
        List<Object> list2 = Arrays.asList(data);
        for (Object object : list2) {
            if (object instanceof EmpLoginVo)
            {
                EmpLoginVo empLoginVo = (EmpLoginVo) object;
                empLoginLog.setJwt(empLoginVo.getToken());
            }
        }

        operateLogMapper.insertLogin(empLoginLog);
    }
}
