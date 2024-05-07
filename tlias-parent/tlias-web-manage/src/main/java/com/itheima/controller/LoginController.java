package com.itheima.controller;

import com.itheima.entity.dto.EmpLoginDto;
import com.itheima.entity.vo.EmpLoginVo;
import com.itheima.entity.vo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result loginCheck(@RequestBody EmpLoginDto empLoginDto){
        EmpLoginVo ulv = empService.loginCheck(empLoginDto);
        if ( ulv != null){
            return Result.success(ulv);
        }
        return Result.error("用户名或者密码不正确！");
    }
}
