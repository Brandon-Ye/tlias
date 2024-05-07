package com.itheima.service.imp;

import com.itheima.entity.po.EmpExpr;
import com.itheima.mapper.EmpexprMapper;
import com.itheima.service.EmpexprService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpexprServiceImpl implements EmpexprService {
    @Autowired
    private EmpexprMapper empexprMapper;

    @Override
    public void insertExpr(List<EmpExpr> exprList) {
        empexprMapper.insertExpr(exprList);
    }

    @Override
    public void deleteBactch(List<Integer> ids) {
        empexprMapper.deleteBactch(ids);
    }

    @Override
    public void deleteById(Integer Empid) {
        empexprMapper.deleteById(Empid);
    }
}
