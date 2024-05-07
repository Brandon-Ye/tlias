package com.itheima.service;

import com.itheima.entity.dto.EmpLoginDto;
import com.itheima.entity.dto.EmpQueryParam;
import com.itheima.entity.vo.Emp;
import com.itheima.entity.vo.EmpLoginVo;
import com.itheima.entity.vo.JobOption;
import com.itheima.entity.vo.PageBean;

import java.util.List;

public interface EmpService {
    PageBean getList(EmpQueryParam page);

    void add(Emp emp);

    void deleteBatch(List<Integer> ids);

    Emp getById(Integer id);

    void update(Emp emp);

    JobOption empJobData();

    List empGenderData();

    List<Emp> list();


    EmpLoginVo loginCheck(EmpLoginDto empLoginDto);

    Integer countEmp(Integer id);
}
