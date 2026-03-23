package org.nuoyue.service;

import org.nuoyue.pojo.Emp;
import org.nuoyue.pojo.OperateLog;
import org.nuoyue.pojo.PageResult;

import java.util.List;

public interface OperateLogService {

    PageResult<OperateLog> page(Integer page, Integer pageSize);
}
