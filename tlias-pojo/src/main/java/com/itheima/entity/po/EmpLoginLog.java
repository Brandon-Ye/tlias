package com.itheima.entity.po;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpLoginLog {
    private Integer id; //ID
    private String username; //登录用户名
    private String password; //登录密码
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginTime; //登录时间
    private Short isSuccess; //是否登录成功, 1:成功, 0:失败
    private String jwt; //成功后, 下发的JWT令牌
    private Long costTime; //登录耗时, 单位:ms
}