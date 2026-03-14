package org.nuoyue.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.nuoyue.pojo.Emp;
import org.nuoyue.pojo.EmpExpr;

import java.util.List;

/*
 * Employee Working Experience
 */
@Mapper
public interface EmpExprMapper {


    /*
     * Batch insert employee records
     */
    void insertBatch(List<EmpExpr> empList);


    /*
    * Batch delete employee records by their IDs
    */
    void deleteByEmpIds(List<Integer> empIds);
}
