package org.nuoyue.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.nuoyue.mapper.EmpExprMapper;
import org.nuoyue.mapper.EmpMapper;
import org.nuoyue.pojo.*;
import org.nuoyue.service.EmpLogService;
import org.nuoyue.service.EmpService;
import org.nuoyue.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;


//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        // Query total record count
//        Long total = empMapper.count();
//
//        // Query the employee data for the current page
//        int start = (page - 1) * pageSize;
//        List<Emp> list = empMapper.list(start, pageSize);
//
//        // Return the paginated result
//        return new PageResult<Emp>(total, list);
//    }

    /*
        * PageHelper pagination implementation
        * @param page Current page number
        * @param pageSize Number of records per page
     */
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize,
//                                String name, Integer gender, LocalDate begin, LocalDate end) {
//        // Set pagination parameters
//        PageHelper.startPage(page, pageSize);
//        // Query the employee data for the current page
//        List<Emp> empList = empMapper.list(name, gender, begin, end);
//
//        // PageHelper automatically handle pagination and return a Page object
//        PageInfo<Emp> pageInfo = new PageInfo<>(empList);
//        System.out.println("Total records: " + pageInfo.getTotal());
//        return new PageResult<Emp>(pageInfo.getTotal(), pageInfo.getList());
//    }
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        // Set pagination parameters
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        // Query the employee data for the current page
        List<Emp> empList = empMapper.list(empQueryParam);

        // PageHelper automatically handle pagination and return a Page object
        PageInfo<Emp> pageInfo = new PageInfo<>(empList);
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }

    // Enable transaction management and specify rollback for any exception
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void save(Emp emp) {

        try {
            //Save the employee data to the database
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            //Save the employee work experience data to the database
            List<EmpExpr> exprList = emp.getExprList();
            if(!CollectionUtils.isEmpty(exprList)){
                // Loop through the work experience list and set the employee ID for each record
                for(EmpExpr expr : exprList){
                    expr.setEmpId(emp.getId());
                }
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "Added employee: " + emp);
            empLogService.insertLog(empLog);
        }

    }
    // Enable transaction management and specify rollback for any exception
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        // Delete employee records by IDs
        empMapper.deleteByIds(ids);
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        // Query employee information by ID
        return empMapper.getInfo(id);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void update(Emp emp) {
        // Update employee information
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        // Update employee work experience information
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            // Loop through the work experience list and set the employee ID for each record
            for(EmpExpr expr : exprList){
                expr.setEmpId(emp.getId());
            }
            empExprMapper.deleteByEmpIds(List.of(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public List<Emp> list() {
        // Query all employee data
        return empMapper.findAll();
    }


    @Override
    public LoginInfo login(Emp emp) {
        Emp e = empMapper.selectByUsernameAndPassword(emp);

        if(e != null){
            log.info("User {} logged in successfully", emp.getUsername());

            // Generate JWT token for the logged-in user
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("username", e.getUsername());
            String jwt = JwtUtils.generateToken(claims);
            return new LoginInfo(e.getId(), e.getUsername(), e.getName(), jwt);
        }

        // Login failed, return null or throw an exception
        return null;
    }

}
