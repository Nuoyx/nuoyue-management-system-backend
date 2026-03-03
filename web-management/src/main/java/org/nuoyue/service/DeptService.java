package org.nuoyue.service;

import org.nuoyue.pojo.Dept;

import java.util.List;

public interface DeptService {

    /*
     * Query all department data
     */
    List<Dept> findAll();

    /*
     * Delete department data by id
     */
    void deleteById(Integer id);

    void insert(Dept dept);

    Dept getById(Integer deptId);

    void update(Dept dept);
}
