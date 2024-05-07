package com.itheima.service;

import com.itheima.entity.vo.ClazzOption;
import com.itheima.entity.vo.PageBean;
import com.itheima.entity.vo.Student;
import com.itheima.entity.dto.StudentParam;

import java.util.List;

public interface StudentService {
    PageBean list(StudentParam param);

    void add(Student student);

    void delete(List<Integer> ids);

    Student getById(Integer id);

    void update(Student student);

    void updateScore(Integer id, Short score);

    ClazzOption studentCountData();

    List studentDegreeData();

    Integer selectStudent(Integer id);
}
