package com.itheima.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.entity.vo.Clazz;
import com.itheima.entity.vo.PageBean;
import com.itheima.mapper.ClazzMapper;
import com.itheima.service.ClazzService;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;

    @Autowired
    private StudentService studentService;

    /**
     * 新增班级
     * @param clazz
     */
    @Override
    public void addClazz(Clazz clazz) {
        //补全基础数据
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        //新增操作
        clazzMapper.insert(clazz);
    }

    /**
     * 查询所有班级
     * @return
     */
    @Override
    public List<Clazz> selectAll() {
        return clazzMapper.selectall();
    }

    /**
     * 班级分页查询
     * @param begin
     * @param end
     * @param name
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageBean select(LocalDate begin, LocalDate end, String name,Integer page,Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Clazz> clazzList = clazzMapper.selectPage(begin, end, name);
        clazzList.forEach(list->{
            if (LocalDate.now().isBefore(list.getBeginDate())){
                list.setStatus("未开班");
            }else if (LocalDate.now().isAfter(list.getEndDate())) {
                list.setStatus("已结课");
            }else {
                list.setStatus("在读");
            }
        });
        Page<Clazz> p = (Page<Clazz>) clazzList;
        return new PageBean(p.getTotal(), p.getResult());
    }

    /**
     * 根据id删除班级信息
     * @param id
     */
    @Override
    public Boolean delete(Integer id) {

        if (studentService.selectStudent(id) != 0)
        {
            return false;
        }
        clazzMapper.delete(id);
        return true;
    }

    /**
     * 根据id查找班级信息
     * @param id
     * @return
     */
    @Override
    public Clazz selectById(Integer id) {
        return clazzMapper.selectById(id);
    }

    /**
     * 修改班级
     * @param clazz
     */
    @Override
    public void update(Clazz clazz) {
        clazzMapper.update(clazz);
    }
}
