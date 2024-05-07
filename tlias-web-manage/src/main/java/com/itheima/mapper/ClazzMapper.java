package com.itheima.mapper;

import com.itheima.entity.vo.Clazz;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ClazzMapper {
    /**
     * 新增班级数据
     * @param clazz
     */
    void insert(Clazz clazz);

    List<Clazz> selectall();

    List<Clazz> selectPage(LocalDate begin, LocalDate end, String name);


    void delete(Integer id);

    Clazz selectById(Integer id);

    void update(Clazz clazz);

}
