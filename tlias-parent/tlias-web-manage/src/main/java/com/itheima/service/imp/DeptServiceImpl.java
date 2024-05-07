package com.itheima.service.imp;

import com.itheima.entity.po.Dept;
import com.itheima.mapper.DeptMapper;
import com.itheima.service.DeptService;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service(value = "service1")
public class DeptServiceImpl implements DeptService {
    @Autowired
   private DeptMapper deptMapper;
    @Autowired
    private EmpService empService;
    @Override
    public List<Dept> list() {
        return deptMapper.deptlist();
    }

    @Override
    public Boolean delete(Integer id) {
        if(empService.countEmp(id) != 0)
        {
            return false;
        }
        deptMapper.deletedept(id);
        return true;
    }

    @Override
    public void insert(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        dept.setCreateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    @Override
    public void update(Dept dept) {
//        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
