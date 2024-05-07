package com.itheima.controller;

import com.itheima.entity.vo.PageBean;
import com.itheima.entity.vo.Result;
import com.itheima.service.EmpService;
import com.itheima.service.OperateLogService;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ReportController {

    @Autowired
    private EmpService empService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private OperateLogService operateLogService;

    /**
     *  员工职位人数统计
     * @return
     */
    @GetMapping("/report/empJobData")
    public Result empJobData(){
        return Result.success(empService.empJobData());
    }

    /**
     * 员工性别统计
     * @return
     */
    @GetMapping("/report/empGenderData")
    public Result empGenderData(){
        return Result.success(empService.empGenderData());
    }

    /**
     * 班级人数统计
     * @return
     */
    @GetMapping("/report/studentCountData")
    public Result studentCountData(){
        return Result.success(studentService.studentCountData());
    }

    /**
     * 学历统计
     * @return
     */
    @GetMapping("/report/studentDegreeData")
    public Result studentDegreeData(){
        return Result.success(studentService.studentDegreeData());
    }

    /**
     * 分页查询log
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/log/page")
    public Result logPage(@RequestParam(defaultValue = "1") Integer page,
                          @RequestParam(defaultValue = "10") Integer pageSize){
        PageBean pageBean = operateLogService.selectByPage(page,pageSize);
        return Result.success(pageBean);
    }
}
