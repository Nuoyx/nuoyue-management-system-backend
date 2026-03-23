package org.nuoyue.service;

import org.nuoyue.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {


    JobOption getEmpJobReport();

    List<Map<String, Object>> getEmpGenderData();
}
