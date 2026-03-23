package org.nuoyue.service.impl;

import org.nuoyue.mapper.DeptMapper;
import org.nuoyue.mapper.EmpMapper;
import org.nuoyue.pojo.Dept;
import org.nuoyue.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;


    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        Integer count = empMapper.countByDeptId(id);
        if(count > 0){
            throw new RuntimeException("Cannot delete department with id " + id + " because it has associated employees.");
        }
        deptMapper.deleteById(id);
    }

    @Override
    public void insert(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public Dept getById(Integer deptId) {
        return deptMapper.getById(deptId);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(java.time.LocalDateTime.now());
        deptMapper.update(dept);
    }


}
