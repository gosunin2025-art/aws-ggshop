package com.app.ggshop.v1.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * V2G 거래 등록 INSERT용 DTO
 * HTML form에서 넘어오는 값 + INSERT 후 생성된 ID 보관
 */
@Getter
@Setter
@ToString
public class VtogInsertDTO {

    // ① 회원 조회 결과 (hidden으로 넘어옴)
    private Long   memberId;        // tbl_member.id
    private String memberAddress;   // tbl_member.member_address → ev_charger_address 사용

    // ② tbl_car INSERT
    private String carPlateNumber;  // 차량 번호판 (수동입력)
    private String carEnergyGauge;  // 차량 수전 에너지 용량 (수동입력)
    private Long   carId;           // INSERT 후 생성된 car.id (useGeneratedKeys 자동세팅) → ④에서 사용

    // ③ tbl_ev_charger INSERT
    private String evChargerUid;    // 충전기 고유번호 (수동입력)
    private Long   chargerId;       // INSERT 후 생성된 charger.id (useGeneratedKeys 자동세팅) → ④에서 사용

    // ④ tbl_vtog_payment INSERT
    private Long   vtogPaymentSalesKwh;    // 판매 가능한 전기 용량 (수동입력)
    private Long   vtogPaymentSalesPrice;  // V2G 전력 판매 금액 (수동입력)
    private Long   vtogEvChargerId;        // ③ chargerId 복사해서 사용
    private String vtogTransactionNumber;  // 자동생성 예) V2G-20260223-000001

}
