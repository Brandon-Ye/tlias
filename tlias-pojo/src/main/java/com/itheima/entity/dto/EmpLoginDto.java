package com.itheima.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 员工登录请求实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpLoginDto {
    private String username;
    private String password;
}
