package org.nuoyue.controller;

import lombok.extern.slf4j.Slf4j;
import org.nuoyue.pojo.*;
import org.nuoyue.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@Slf4j
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    /// emps?page=1&pageSize=10
    @GetMapping()
    public Result getPage(EmpQueryParam empQueryParam) {
        log.info("Received request: " + empQueryParam);

        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    /*
     * Add a new employee
     */
    @PostMapping()
    public Result save(@RequestBody Emp emp){
        log.info("Received request to add employee: " + emp);
        empService.save(emp);
        return Result.success();

    }

}
