package com.app.ggshop.v1.dto;

import com.app.ggshop.v1.domain.VtogPaymentVO;
import lombok.*;


@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class VtogPaymentDTO {

    private Long   id;
    private Long   vtogPaymentSalesKwh;
    private Long   vtogPaymentSalesPrice;
    private Long   vtogCarId;
    private Long   vtogEvChargerId;          // tbl_ev_charger FK
    private String vtogTransactionNumber;    // 거래번호 V2G-yyyyMMdd-000001
    private String createdDate;
    private String updatedDate;

    // JOIN 조회용 필드
    private String memberName;        // tbl_member.member_name
    private String evChargerUid;      // tbl_ev_charger.ev_charger_uid

    /**
     * VO → DTO 변환
     */
    public static VtogPaymentDTO fromVtogPaymentVO(VtogPaymentVO vo) {
        VtogPaymentDTO dto = new VtogPaymentDTO();
        dto.setId(vo.getId());
        dto.setVtogPaymentSalesKwh(vo.getVtogPaymentSalesKwh());
        dto.setVtogPaymentSalesPrice(vo.getVtogPaymentSalesPrice());
        dto.setVtogCarId(vo.getVtogCarId());
        dto.setVtogEvChargerId(vo.getVtogEvChargerId());
        dto.setVtogTransactionNumber(vo.getVtogTransactionNumber());
        dto.setCreatedDate(vo.getCreatedDate());
        dto.setUpdatedDate(vo.getUpdatedDate());
        return dto;
    }

    /**
     * DTO → VO 변환
     */
    public VtogPaymentVO toVtogPaymentVO() {
        return VtogPaymentVO.builder()
                .id(id)
                .vtogPaymentSalesKwh(vtogPaymentSalesKwh)
                .vtogPaymentSalesPrice(vtogPaymentSalesPrice)
                .vtogCarId(vtogCarId)
                .vtogEvChargerId(vtogEvChargerId)
                .vtogTransactionNumber(vtogTransactionNumber)
                .build();
    }
}