package com.itheima.service;

import com.itheima.entity.po.Dept;

import java.util.List;

public interface DeptService {
    public List<Dept> list();

    Boolean delete(Integer id);

    void insert(Dept dept);

    Dept getById(Integer id);

    void update(Dept dept);
}
