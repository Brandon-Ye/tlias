package com.itheima.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 员工登录响应实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpLoginVo {
    private Integer id;
    private String username;
    private String name;
    private String token;
}
