package com.itheima.mapper;



import com.itheima.entity.dto.OperateLog;

import com.itheima.entity.po.EmpLoginLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OperateLogMapper {

    //插入日志数据
    @Insert("insert into operate_log (operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time) " +
            "values (#{operateEmpId}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime});")
    public void insert(OperateLog log);

    /**
     * 回显日志
     * @return
     */
    @Select("select e1.name operateEmpName,o1.* from operate_log o1 join emp e1 on o1.operate_emp_id=e1.id")
    List<OperateLog> selectByPage();

    //插入登录记录日志
    @Insert("insert into emp_login_log (username,password,login_time,is_success,jwt,cost_time)" +
            "values (#{username},#{password},#{loginTime},#{isSuccess},#{jwt},#{costTime})")
    void insertLogin(EmpLoginLog empLoginLog);
}
