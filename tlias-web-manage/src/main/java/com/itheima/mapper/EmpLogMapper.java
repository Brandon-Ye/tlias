package com.itheima.mapper;

import com.itheima.entity.po.EmpLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpLogMapper {

    void insert(EmpLog empLog);
}
