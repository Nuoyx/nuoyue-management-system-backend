package org.nuoyue.service;

import org.nuoyue.pojo.Emp;
import org.nuoyue.pojo.EmpQueryParam;
import org.nuoyue.pojo.PageResult;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {

    /*
        * Query employee data with pagination
        * @param page Current page number
        * @param pageSize Number of records per page
     */
//    PageResult<Emp> page(Integer page, Integer pageSize,
//                         String name, Integer gender, LocalDate begin, LocalDate end);

    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void save(Emp emp);

    void delete(List<Integer> ids);

    Emp getInfo(Integer id);
}
