package com.app.ggshop.v1.dto;

import com.app.ggshop.v1.common.enumeration.Status;
import com.app.ggshop.v1.domain.CompanyEmployeeVO;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class CompanyEmployeeDTO {
    private Long id;

    // HTML 폼 필드명
    private String employeeName;      // → company_employee_name
    private String position;          // → company_employee_rank
    private String email;             // → company_employee_email
    private String employeeNumber;    // → company_employee_number
    private String department;        // → company_employee_department
    private String employeeNote;      // → ev_charger_content

    private Status companyEmployeeStatus;
    private String joinDate;          // → created_date
    private String createdDatetime;
    private String updatedDatetime;
    private Long employeeCompanyId;

    public CompanyEmployeeVO toVO() {
        return CompanyEmployeeVO.builder()
                .id(id)
                .companyEmployeeName(employeeName)
                .companyEmployeeRank(position)
                .companyEmployeeEmail(email)
                .companyEmployeeNumber(employeeNumber)
                .companyEmployeeDepartment(department)
                .evChargerContent(employeeNote)
                .companyEmployeeStatus(companyEmployeeStatus)
                .createdDate(joinDate != null ? joinDate : createdDatetime)
                .employeeCompanyId(employeeCompanyId)
                .build();
    }
}