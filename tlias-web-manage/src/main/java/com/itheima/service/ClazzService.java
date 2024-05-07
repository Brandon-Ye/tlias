package com.itheima.service;

import com.itheima.entity.vo.Clazz;
import com.itheima.entity.vo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface ClazzService {
    void addClazz(Clazz clazz);

    List<Clazz> selectAll();

    PageBean select(LocalDate begin, LocalDate end, String name,Integer page,Integer pageSize);

    Boolean delete(Integer id);

    Clazz selectById(Integer id);

    void update(Clazz clazz);
}
