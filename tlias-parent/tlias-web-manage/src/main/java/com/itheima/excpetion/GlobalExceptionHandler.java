package com.itheima.excpetion;

import com.itheima.entity.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice  //作用：用来捕获控制器controller层抛出的所有异常
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)   //指定处理何种异常
    @ResponseBody
    public Result doException(Exception exception){
        log.error(exception.getMessage());
        if (exception.getMessage().contains("clazz_id"))
        {
            return Result.error("班级信息不能为空！");
        }
        return Result.error("出错了!");
    }
}
