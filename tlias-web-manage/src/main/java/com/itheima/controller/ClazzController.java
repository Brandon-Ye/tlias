package com.itheima.controller;

import com.itheima.annotation.Log;
import com.itheima.entity.vo.Clazz;
import com.itheima.entity.vo.Result;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    /**
     *新增班级
     * @param clazz
     * @return
     */
    @Log
    @PostMapping("/clazzs")
    public Result addClazz(@RequestBody Clazz clazz){
        clazzService.addClazz(clazz);
        return Result.success();
    }

    /**
     * 所有班级查询
     */
    @GetMapping("/clazzs/list")
    public Result selectClazz(){
        List<Clazz> clazzList = clazzService.selectAll();
        return Result.success(clazzList);
    }

    /**
     * 分页查询
     * @param begin
     * @param end
     * @param name
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/clazzs")
    public Result select(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                         @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end,
                         String name,
                         @RequestParam(defaultValue = "1") Integer page,
                         @RequestParam(defaultValue = "10") Integer pageSize){
        return Result.success(clazzService.select(begin, end, name,page,pageSize));
    }

    /**
     * 删除班级信息
     * @param id
     * @return
     */
    @Log
    @DeleteMapping("/clazzs/{id}")
    public Result delete(@PathVariable Integer id){
        if (clazzService.delete(id)){
            return Result.success();
        }else {
            return Result.error("该班级还存在学生，无法删除！");
        }
    }

    /**
     * 根据id查询班级信息
     * @param id
     * @return
     */
    @GetMapping("/clazzs/{id}")
    public Result selectById(@PathVariable Integer id){
        return Result.success(clazzService.selectById(id));
    }

    /**
     * 接收需要修改的班级信息
     * @param clazz
     * @return
     */
    @Log
    @PutMapping("/clazzs")
    public Result update(@RequestBody Clazz clazz){
        clazzService.update(clazz);
        return Result.success();
    }

}
