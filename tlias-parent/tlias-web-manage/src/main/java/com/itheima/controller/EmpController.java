package com.itheima.controller;

import com.itheima.annotation.Log;
import com.itheima.entity.vo.Emp;
import com.itheima.entity.dto.EmpQueryParam;
import com.itheima.entity.vo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;

    /**
     * 员工信息分组查询
     * @param param
     * @return
     */
    @GetMapping("/emps")
    public Result getList(EmpQueryParam param){
//        log.info("开始: {} {} {} {} {} {}",param.getName(),param.getGender(),param.getBegin(),
//                param.getEnd(),param.getPage(),param.getPageSize());
        return Result.success(empService.getList(param));
    }


    /**
     * 新增员工
     * @param emp
     * @return
     */
    @Log
    @PostMapping("/emps")
    public Result add(@RequestBody Emp emp){
        log.info("数据:{}",emp.getName());
        empService.add(emp);
        return Result.success();
    }

    /**
     * 删除员工
     * @return
     */
    @Log
    @DeleteMapping("/emps")
    public Result delete(@RequestParam List<Integer> ids){
        empService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 数据回显
     * @param id
     * @return
     */
    @GetMapping("/emps/{id}")
    public Result getById(@PathVariable Integer id){
       return Result.success(empService.getById(id));
    }

    /**
     * 修改员工信息
     * @param emp
     * @return
     */
    @Log
    @PutMapping("/emps")
    public Result update(@RequestBody Emp emp){
        empService.update(emp);
        return Result.success();
    }

    /**
     * 查询所有员工信息
     * @return
     */
    @GetMapping("/emps/list")
    public Result list(){
        return Result.success(empService.list());
    }
}

