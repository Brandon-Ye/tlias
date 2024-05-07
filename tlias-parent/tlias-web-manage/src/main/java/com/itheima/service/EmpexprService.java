package com.itheima.service;

import com.itheima.entity.po.EmpExpr;

import java.util.List;

public interface EmpexprService {
    void insertExpr(List<EmpExpr> exprList);

    void deleteBactch(List<Integer> ids);

    void deleteById(Integer id);
}
