package com.itheima.controller;

import com.itheima.annotation.Log;
import com.itheima.entity.vo.PageBean;
import com.itheima.entity.vo.Result;
import com.itheima.entity.vo.Student;
import com.itheima.entity.dto.StudentParam;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 学员列表查询
     * @param param
     * @return
     */
    @GetMapping("/students")
    public Result list(StudentParam param){
        PageBean paramList = studentService.list(param);
        return Result.success(paramList);
    }

    /**
     * 新增学员
     * @param student
     * @return
     */
    @Log
    @PostMapping("/students")
    public Result add(@RequestBody Student student){
        studentService.add(student);
        return Result.success();
    }

    /**
     * 删除学员
     * @param ids
     * @return
     */
    @Log
    @DeleteMapping("/students/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
//        log.info("message: {}",ids.toString());
//        List<Integer> idsList = new ArrayList<>();
//        Collections.addAll(idsList,ids);
        studentService.delete(ids);
        return Result.success();
    }

    /**
     * 数据回显
     * @return
     */
    @GetMapping("/students/{id}")
    public Result getById(@PathVariable Integer id){
        return Result.success(studentService.getById(id));
    }

    /**
     * 修改学员
     * @param student
     * @return
     */
    @Log
    @PutMapping("/students")
    public Result update(@RequestBody Student student){
        studentService.update(student);
        return Result.success();
    }

    /**
     * 违纪扣分
     * @param id
     * @param score
     * @return
     */
    @Log
    @PutMapping("/students/violation/{id}/{score}")
    public Result updateScore(@PathVariable  Integer id,@PathVariable Short score){
        log.info("id为：{}，score为：{}",id,score);
        if (score <= 0){
            return Result.error("输入的违纪分数不对，请检查后重新输入！");
        }
        studentService.updateScore(id,score);
        return Result.success();
    }
}
