package org.nuoyue.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp {
    private Integer id; // ID, primary key
    private String username; // username
    private String password; // password
    private String name; // full name
    private Integer gender; // gender, 1: Male, 2: Female
    private String phone; // phone number
    private Integer job; // position, 1: HR Specialist, 2: Software Engineer, 3: Accountant, 4: Marketing Specialist, 5: Sales Representative, 6: Research Scientist, 7: Support Specialist, 8: Operations Coordinator, 9: Legal Counsel, 10: Admin Assistant
    private Integer salary; // salary
    private String image; // profile image
    private LocalDate entryDate; // hire date
    private Integer deptId; // associated department ID
    private LocalDateTime createTime; // creation time
    private LocalDateTime updateTime; // modification time


    //Encapsulated field for department name
    private String deptName; // department name

    //Encapsulated field for work experience
    private List<EmpExpr> exprList;

}
