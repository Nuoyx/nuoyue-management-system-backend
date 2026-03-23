package org.nuoyue.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.nuoyue.pojo.Emp;
import org.nuoyue.pojo.OperateLog;

import java.util.List;

@Mapper
public interface OperateLogMapper {

    // Insert operation log
    @Insert("insert into operate_log (operate_emp_id, operate_time, method_name, method_params, return_value, cost_time) " +
            "values (#{operateEmpId}, #{operateTime}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime});")
    public void insert(OperateLog log);

    // Query operation logs with pagination
    List<OperateLog> list(Integer page, Integer pageSize);
}
