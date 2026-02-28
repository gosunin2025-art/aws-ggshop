package com.app.ggshop.v1.dto;

import com.app.ggshop.v1.common.enumeration.Status;
import com.app.ggshop.v1.domain.EvChargerVO;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class EvChargerDTO {

    private Long id;

    // HTML input name 매칭
    private String installDate;      // setup_date
    private String stationNumber;    // ev_charger_uid
    private String installAddress;   // ev_charger_address
    private String chargerNote;      // ev_charger_content
    private Status evChargerStatus;
    private Status evChargerMod;
    private Long companyId;


    public EvChargerVO toVO() {
        return EvChargerVO.builder()
                .evChargerUid(stationNumber)
                .evChargerAddress(installAddress)
                .evChargerContent(chargerNote)
                .setupDate(installDate)
                .evChargerStatus(evChargerStatus)
                .evChargerMod(evChargerMod)
                .companyId(companyId)
                .build();
    }
}
