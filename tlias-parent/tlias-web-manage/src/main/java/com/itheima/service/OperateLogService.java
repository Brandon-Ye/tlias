package com.itheima.service;

import com.itheima.entity.vo.PageBean;

public interface OperateLogService {
    PageBean selectByPage(Integer page, Integer pageSize);
}
