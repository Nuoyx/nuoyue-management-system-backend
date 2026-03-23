package org.nuoyue.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.nuoyue.mapper.OperateLogMapper;
import org.nuoyue.pojo.Emp;
import org.nuoyue.pojo.OperateLog;
import org.nuoyue.pojo.PageResult;
import org.nuoyue.service.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OperateLogServiceImpl implements OperateLogService {

    @Autowired
    OperateLogMapper operateLogMapper;

    @Override
    public PageResult<OperateLog> page(Integer page, Integer pageSize) {

        // Set pagination parameters
        PageHelper.startPage(page, pageSize);
        // Query the employee data for the current page
        List<OperateLog> operateLogList = operateLogMapper.list(page, pageSize);

        // PageHelper automatically handle pagination and return a Page object
        PageInfo<OperateLog> pageInfo = new PageInfo<>(operateLogList);
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }
}
