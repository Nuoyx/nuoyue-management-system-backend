package org.nuoyue.controller;


/*
 * LoginController
 */

import lombok.extern.slf4j.Slf4j;
import org.nuoyue.pojo.Emp;
import org.nuoyue.pojo.LoginInfo;
import org.nuoyue.pojo.Result;
import org.nuoyue.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("Received login request: {}", emp);
        LoginInfo login = empService.login(emp);
        if (login != null) {
            log.info("Login successful for user: {}", emp.getUsername());
            return Result.success(login);
        }
        log.warn("Login failed for user: {}", emp.getUsername());
        return Result.error("Invalid username or password");
    }
}
