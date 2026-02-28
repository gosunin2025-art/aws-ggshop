package com.app.ggshop.v1.domain;

import com.app.ggshop.v1.audit.dateTime.Period;
import com.app.ggshop.v1.common.enumeration.Status;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(of="id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class CarVO extends Period {
    private Long   id;
    private String carPlateNumber;   // 차량 번호판
    private String carEnergyGauge;   // 차량 수전 에너지 용량
    private Status carStatus;        // active / inactive
    private String carFilter;        // 양방향 / 일반
    private Long   carMemberId;      // tbl_member FK
}