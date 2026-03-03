package org.nuoyue.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class EmpQueryParam {
    private Integer page = 1; // Default to the first page
    private Integer pageSize = 10; // Default to 10 records per page
    private String name;
    private Integer gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String begin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String end;
}
