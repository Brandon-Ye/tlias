package com.itheima.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.entity.dto.OperateLog;
import com.itheima.entity.vo.PageBean;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.service.OperateLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OperateLogServiceImpl implements OperateLogService {

    @Autowired
    private OperateLogMapper operateLogMapper;

    /**
     * 分页查询log
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageBean selectByPage(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<OperateLog> loglist = operateLogMapper.selectByPage();
        Page p = (Page) loglist;
        return new PageBean(p.getTotal(),p.getResult());
    }
}
