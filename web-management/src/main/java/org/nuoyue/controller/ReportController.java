package org.nuoyue.controller;


import lombok.extern.slf4j.Slf4j;
import org.nuoyue.pojo.JobOption;
import org.nuoyue.pojo.Result;
import org.nuoyue.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;
    /*
     * Get employee job report data
     */
    @GetMapping("/empJobData")
    public Result getEmpJobReport() {
        log.info("Received request for employee report");
        JobOption jobOption = reportService.getEmpJobReport();
        return Result.success(jobOption);
    }

    @GetMapping("/empGenderData")
    public Result getEmpGenderData() {
        log.info("Received request for employee gender report");
        List<Map<String, Object>> genderList = reportService.getEmpGenderData();
        return Result.success(genderList);
    }
}
