package com.app.ggshop.v1.dto;

import com.app.ggshop.v1.common.enumeration.Status;
import com.app.ggshop.v1.domain.CarVO;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class CarDTO {

    private Long   id;
    private String carPlateNumber;   // 차량 번호판
    private String carEnergyGauge;   // 차량 수전 에너지 용량
    private Status carStatus;        // active / inactive
    private String carFilter;        // 양방향 / 일반
    private Long   carMemberId;      // tbl_member FK
    private String createdDate;
    private String updatedDate;

    // JOIN 조회용 필드
    private String memberName;       // tbl_member.member_name

    /**
     * VO → DTO 변환
     */
    public static CarDTO fromCarVO(CarVO vo) {
        CarDTO dto = new CarDTO();
        dto.setId(vo.getId());
        dto.setCarPlateNumber(vo.getCarPlateNumber());
        dto.setCarEnergyGauge(vo.getCarEnergyGauge());
        dto.setCarStatus(vo.getCarStatus());
        dto.setCarFilter(vo.getCarFilter());
        dto.setCarMemberId(vo.getCarMemberId());
        dto.setCreatedDate(vo.getCreatedDate());
        dto.setUpdatedDate(vo.getUpdatedDate());
        return dto;
    }

    /**
     * DTO → VO 변환
     */
    public CarVO toCarVO() {
        return CarVO.builder()
                .id(id)
                .carPlateNumber(carPlateNumber)
                .carEnergyGauge(carEnergyGauge)
                .carStatus(carStatus)
                .carFilter(carFilter)
                .carMemberId(carMemberId)
                .build();
    }
}