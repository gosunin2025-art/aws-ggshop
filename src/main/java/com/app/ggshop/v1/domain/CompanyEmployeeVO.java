package com.app.ggshop.v1.domain;

import com.app.ggshop.v1.audit.dateTime.Period;
import com.app.ggshop.v1.common.enumeration.Status;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class CompanyEmployeeVO extends Period {
    private Long id;
    private String companyEmployeeName;
    private String companyEmployeeRank;
    private String companyEmployeeEmail;
    private String companyEmployeeNumber;
    private String companyEmployeeDepartment;
    private String evChargerContent;  // 특이사항
    private Status companyEmployeeStatus;
    private Long employeeCompanyId;
}