package org.nuoyue.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.nuoyue.mapper.EmpMapper;
import org.nuoyue.pojo.JobOption;
import org.nuoyue.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;

    public JobOption getEmpJobReport() {
        // Get employee job data from the database
        List<Map<String, Object>> list = empMapper.countEmpJobData();

        // Extract job names and counts into separate lists
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("job")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("count")).toList();
        log.info("Job names: {}", jobList);
        log.info("Job counts: {}", dataList);

        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

}
