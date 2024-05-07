package com.itheima.mapper;

import com.itheima.entity.po.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpexprMapper {
    void insertExpr(List<EmpExpr> exprList);

    void deleteBactch(List<Integer> ids);

    void deleteById(Integer empid);
}
