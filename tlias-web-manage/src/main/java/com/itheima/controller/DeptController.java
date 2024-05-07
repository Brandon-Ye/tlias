package com.itheima.controller;

import com.itheima.annotation.Log;
import com.itheima.entity.po.Dept;
import com.itheima.entity.vo.Result;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    @Qualifier("service1")
    private DeptService deptService;


    /**
     * 获取所有部门信息
     * @return
     */
//    @RequestMapping("/depts")
    @GetMapping("/depts")
    public Result getAll(){
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @DeleteMapping("/depts")
    @Log
    // @RequestParam加上之后，required默认值为true代表前端就必须要传递该参数，否则报错400，Bad Request, 如果不需要限制，可以将其设置为required=false
    // public Result delete(@RequestParam(value = "id", required = false) Integer deptId){
    public Result deletedept(Integer id){
        if(deptService.delete(id)){
            return Result.success();
        }
        return Result.error("部门下还有员工，无法删除！");
    }

//    public Result delete(HttpServletRequest request){
//        String id = request.getParameter("id");G
//        int i = Integer.parseInt(id);
//        System.out.println(i);
//        return Result.success();
//    }

    /**
     * 新增部门
     * @param dept
     * @return
     */
    @Log
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept){

        deptService.insert(dept);
        return Result.success();
    }

    /**
     * 数据回显
     * @param id
     * @return
     */
    @GetMapping("/depts/{id}")
    public Result getById(@PathVariable Integer id){
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    /**
     * 更新部门
     * @param dept
     * @return
     */
    @Log
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept){
        deptService.update(dept);
        return Result.success();
    }
}
