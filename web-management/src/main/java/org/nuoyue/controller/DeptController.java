package org.nuoyue.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.nuoyue.pojo.Dept;
import org.nuoyue.pojo.Result;
import org.nuoyue.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping
    public Result list(){
        System.out.println("Query department list data");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    @DeleteMapping
    public Result delete(@RequestParam(value = "id", required = false) Integer deptId){
        System.out.println("Delete department data with id: " + deptId);
        deptService.deleteById(deptId);
        return Result.success();
    }

    @PostMapping
    public Result insert(@RequestBody Dept dept){
        System.out.println("Insert department data: " + dept);
        deptService.insert(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable(value = "id", required = false) Integer deptId){
        System.out.println("data with id: " + deptId);
        Dept dept = deptService.getById(deptId);
        return Result.success(dept);
    }

    @PutMapping
    public Result update(@RequestBody Dept dept){
        System.out.println("Update department data: " + dept);
        deptService.update(dept);
        return Result.success();
    }
}
