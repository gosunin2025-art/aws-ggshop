package com.app.ggshop.v1.dto;

import com.app.ggshop.v1.common.pagination.Criteria;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostWithPagingDTO {
    private List<EvChargerDTO> evChargerList;

    // 추가 필드
    private List<CompanyEmployeeDTO> employeeList;

    private Criteria criteria;
}

