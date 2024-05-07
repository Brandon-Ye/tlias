package com.itheima.mapper;

import com.itheima.entity.vo.Emp;
import com.itheima.entity.dto.EmpQueryParam;
import com.itheima.entity.dto.EmpLoginDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
    List<Emp> select(EmpQueryParam param);

    void insertEmp(Emp emp);

    void deleteBatch(List<Integer> ids);

    Emp getById(Integer id);

    void update(Emp emp);

    List<Map> empJobData();

    List<Map> empGenderData();

    List<Emp> selectall();

    Emp loginCheck(EmpLoginDto empLoginDto);

    Integer getByDeptId(Integer id);
}
