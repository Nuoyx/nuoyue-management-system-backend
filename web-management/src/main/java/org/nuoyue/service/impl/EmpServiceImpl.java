package org.nuoyue.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.nuoyue.mapper.EmpExprMapper;
import org.nuoyue.mapper.EmpMapper;
import org.nuoyue.pojo.Emp;
import org.nuoyue.pojo.EmpExpr;
import org.nuoyue.pojo.EmpQueryParam;
import org.nuoyue.pojo.PageResult;
import org.nuoyue.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;


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
        System.out.println("Total records: " + pageInfo.getTotal());
        return new PageResult<Emp>(pageInfo.getTotal(), pageInfo.getList());
    }

    @Transactional // Enable transaction management for this method
    @Override
    public void save(Emp emp) {

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
        
    }
}
