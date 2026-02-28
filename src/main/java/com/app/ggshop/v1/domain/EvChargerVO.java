package com.app.ggshop.v1.domain;

import com.app.ggshop.v1.audit.dateTime.Period;
import com.app.ggshop.v1.common.enumeration.Status;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class EvChargerVO extends Period {
    private Long id;
    private String evChargerAddress;
    private String evChargerUid;
    private String evChargerContent;
    private Status evChargerStatus;
    private Status evChargerMod;
    private String setupDate;
    private Long companyId;
}