package org.nuoyue.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpExpr {
    private Integer id; // ID, primary key
    private Integer empId; // employee ID
    private LocalDate startDate; // start date
    private LocalDate endDate; // end date
    private String company; // company name
    private String jobTitle; // job title
}
