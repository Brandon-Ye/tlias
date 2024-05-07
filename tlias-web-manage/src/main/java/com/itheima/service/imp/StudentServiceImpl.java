package com.itheima.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.entity.dto.StudentParam;
import com.itheima.entity.vo.ClazzOption;
import com.itheima.entity.vo.PageBean;
import com.itheima.entity.vo.Student;
import com.itheima.mapper.StudentMapper;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 学员列表查询
     * @param param
     * @return
     */
    @Override
    public PageBean list(StudentParam param) {

        PageHelper.startPage(param.getPage(),param.getPageSize());
        List<Student> studentList = studentMapper.list(param);
        Page p = (Page) studentList;
        return new PageBean(p.getTotal(),p.getResult());
    }

    /**
     * 新增学员
     * @param student
     */
    @Override
    public void add(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.insert(student);
    }

    /**
     * 删除学员
     * @param ids
     */
    @Override
    public void delete(List<Integer> ids) {
        studentMapper.delete(ids);
    }

    /**
     * 数据回显
     * @param id
     */
    @Override
    public Student getById(Integer id) {
        return studentMapper.getById(id);
    }

    /**
     * 修改学员
     * @param student
     */
    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    /**
     * 违纪扣分
     * @param id
     * @param score
     */
    @Override
    public void updateScore(Integer id, Short score) {
        Student student = studentMapper.getById(id);
        Short violationCount = (short) (student.getViolationCount() + 1);
        Short violationScore = (short) (student.getViolationScore() + score);
        studentMapper.updateScore(id,violationCount,violationScore);
    }

    /**
     * 班级人数统计
     * @return
     */
    @Override
    public ClazzOption studentCountData() {
        List<Map> list = studentMapper.studentCountData();

        List classname = list.stream().map(item -> item.get("classname")).toList();

        List number = list.stream().map(item -> item.get("dataList")).toList();
        return new ClazzOption(classname,number);
    }

    /**
     * 统计学历人数
     * @return
     */
    @Override
    public List studentDegreeData() {
        List<Map> list = studentMapper.studentDegreeData();
//        List<StudentBean> studentBeanList = new ArrayList<>();
//        for (int i = 0; i < list.size(); i++) {
//            StudentBean student =new StudentBean();
//            Map map = list.get(i);
//            student.setName((String) map.get("degrees"));
//            student.setValue((Long) map.get("value"));
//            studentBeanList.add(student);
//        }
        return list;
    }

    @Override
    public Integer selectStudent(Integer id) {
        return studentMapper.selectByClazzId(id);
    }
}
