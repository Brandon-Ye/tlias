package com.itheima.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.entity.dto.EmpLoginDto;
import com.itheima.entity.dto.EmpQueryParam;
import com.itheima.entity.po.EmpExpr;
import com.itheima.entity.po.EmpLog;
import com.itheima.entity.vo.Emp;
import com.itheima.entity.vo.EmpLoginVo;
import com.itheima.entity.vo.JobOption;
import com.itheima.entity.vo.PageBean;
import com.itheima.mapper.EmpMapper;
import com.itheima.service.EmpService;
import com.itheima.service.EmpexprService;
import com.itheima.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpexprService empexprService;

    @Autowired
    private EmpLogServiceImpl empLogService;

    /**
     * 员工分组查询
     * @param param
     * @return
     */
    @Override
    public PageBean getList(EmpQueryParam param) {

        PageHelper.startPage(param.getPage(),param.getPageSize());
        List<Emp> empList = empMapper.select(param);
        Page<Emp> p = (Page<Emp>) empList;
        return new PageBean(p.getTotal(),p.getResult());
    }

    /**
     * 新增员工
     * @param emp
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void add(Emp emp) {

        try {
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            emp.setPassword("123456");
            empMapper.insertEmp(emp);


            List<EmpExpr> exprList = emp.getExprList();
            if(!CollectionUtils.isEmpty(exprList)) {
                Integer id = emp.getId();
                exprList.forEach(e -> {
                    e.setEmpId(id);
                });
                empexprService.insertExpr(exprList);
            }
        } finally {
            empLogService.insert(new EmpLog(null,LocalDateTime.now(),emp.toString()));
        }
    }

    /**
     * 批量删除员工数据
     * @param ids
     */
    @Transactional
    @Override
    public void deleteBatch(List<Integer> ids) {
        //删除员工基本数据
        empMapper.deleteBatch(ids);
        //删除员工经历数据
        empexprService.deleteBactch(ids);
    }

    /**
     * 根据id回显数据
     * @param id
     * @return
     */
    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    /**
     * 修改员工信息
     * @param emp
     */
    @Transactional
    @Override
    public void update(Emp emp) {
        //修改员工基础信息
        empMapper.update(emp);

        //修改员工工作经历信息

        Integer id = emp.getId();
        empexprService.deleteById(id);
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(id);
            });
            empexprService.insertExpr(exprList);
        }
    }

    /**
     *  员工职位人数统计
     * @return
     */
    @Override
    public JobOption empJobData() {
        //获取数据
        List<Map> list = empMapper.empJobData();

        //获取name
        List jobs = list.stream().map(item -> item.get("deptId")).toList();
        //获取人数
        List numbers = list.stream().map(item -> item.get("cnt")).toList();

        return new JobOption(jobs,numbers);
    }

    /**
     * 员工性别统计
     * @return
     */
    @Override
    public List empGenderData() {
        return empMapper.empGenderData();
    }

    /**
     * 查询所有员工信息
     * @return
     */
    @Override
    public List<Emp> list() {
        return empMapper.selectall();
    }

    /**
     * 登录验证
     * @param empLoginDto
     * @return
     */
    @Override
    public EmpLoginVo loginCheck(EmpLoginDto empLoginDto) {
        //判断数据库内的账号名密码是否一致
        Emp emp = empMapper.loginCheck(empLoginDto);
        if (emp !=null){
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",emp.getId());
            claims.put("username",emp.getUsername());

            //生成jwt
            String jwt = JwtUtils.generateJwt(claims);

            EmpLoginVo ulv= new EmpLoginVo(emp.getId(),emp.getUsername(),emp.getName(),jwt);
            return ulv;
        }
        return null;
    }

    /**
     * 统计部门员工数
     * @param id
     * @return
     */
    @Override
    public Integer countEmp(Integer id) {
        return empMapper.getByDeptId(id);
    }


}
