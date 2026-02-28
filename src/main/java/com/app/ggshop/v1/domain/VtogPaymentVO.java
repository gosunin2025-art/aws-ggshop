package com.app.ggshop.v1.domain;

import com.app.ggshop.v1.audit.dateTime.Period;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(of="id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class VtogPaymentVO extends Period {
    private Long   id;
    private Long   vtogPaymentSalesKwh;
    private Long   vtogPaymentSalesPrice;
    private Long   vtogCarId;
    private Long   vtogEvChargerId;          // tbl_ev_charger FK
    private String vtogTransactionNumber;    // 거래번호 V2G-yyyyMMdd-000001
}