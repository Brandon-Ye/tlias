package com.itheima.mapper;

import com.itheima.entity.vo.Student;
import com.itheima.entity.dto.StudentParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    List<Student> list(StudentParam param);

    void insert(Student student);

    void delete(List<Integer> ids);

    Student getById(Integer id);

    void update(Student student);

    void updateScore(Integer id, Short count, Short score);

    List<Map> studentCountData();

    List<Map> studentDegreeData();

    Integer selectByClazzId(Integer id);
}
