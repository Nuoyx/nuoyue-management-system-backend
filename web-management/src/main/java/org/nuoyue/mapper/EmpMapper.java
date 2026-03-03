package org.nuoyue.mapper;

import org.apache.ibatis.annotations.*;
import org.nuoyue.pojo.Emp;
import org.nuoyue.pojo.EmpQueryParam;

import java.time.LocalDate;
import java.util.List;

/*
 * Employee
 */
@Mapper
public interface EmpMapper {

//    /*
//     * Count the total number of employees
//     */
//    @Select("SELECT COUNT(*) FROM emp LEFT JOIN dept on emp.dept_id = dept.id")
//    public Long count();
//
//    @Select("SELECT emp.*, dept.name AS deptName FROM emp LEFT JOIN dept ON emp.dept_id = dept.id " +
//            "ORDER BY emp.update_time LIMIT #{start}, #{pageSize}")
//    public List<Emp> list(@Param("start") Integer start, @Param("pageSize") Integer pageSize);
//    @Select("SELECT emp.*, dept.name AS deptName FROM emp LEFT JOIN dept ON emp.dept_id = dept.id " +
//            "ORDER BY emp.update_time DESC")
//    List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);

//     Dynamic SQL implementation using MyBatis XML configuration
//     empQueryParam contains all the query parameters for filtering and pagination

    /*
     * Count the total number of employees
     * Dynamic SQL implementation using MyBatis XML configuration
     * empQueryParam contains all the query parameters for filtering and pagination
     */
    List<Emp> list(EmpQueryParam empQueryParam);

    /*
     * Insert a new employee record
     */

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "VALUES (#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, NOW(), NOW())")
    void insert(Emp emp);

}
