package com.itheima.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 班级人数统计类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClazzOption {
    private List clazzList;   //班级列表
    private List dataList;  //各班级对应的总人数
}
