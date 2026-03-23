package org.nuoyue.controller;


import lombok.extern.slf4j.Slf4j;
import org.nuoyue.pojo.*;
import org.nuoyue.service.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/log")
@RestController
public class LogController {
    @Autowired
    OperateLogService operateLogService;

    @GetMapping("/page")
    public Result login(@RequestParam Integer page, @RequestParam Integer pageSize) {
        log.info("Received request for logs page: page={}, pageSize={}", page, pageSize);

        PageResult<OperateLog> pageResult = operateLogService.page(page, pageSize);
        return Result.success(pageResult);
    }
}
