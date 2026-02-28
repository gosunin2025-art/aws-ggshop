package com.app.ggshop.v1.mapper;

import com.app.ggshop.v1.dto.EvChargerDTO;
import com.app.ggshop.v1.dto.MemberDTO;
import com.app.ggshop.v1.dto.VtogInsertDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * V2G 거래 관련 MyBatis Mapper 인터페이스
 */
@Mapper
public interface VtogMapper {

    /**
     * 이메일로 회원 조회
     *
     * @param email 회원 이메일
     * @return MemberDTO (없으면 null)
     */
    MemberDTO findMemberByEmail(@Param("email") String email);

    // ② tbl_car INSERT (useGeneratedKeys → dto.carId 자동 세팅)
    void insertCar(VtogInsertDTO dto);

    // ③ tbl_ev_charger INSERT (useGeneratedKeys → dto.chargerId 자동 세팅)
//    void insertEvCharger(VtogInsertDTO dto);
    // ③ tbl_ev_charger 조회 (INSERT 아님! 기존 충전기 UID로 id 조회)
//    Long findEvChargerByUid(@Param("evChargerUid") String evChargerUid);

    // ③ tbl_ev_charger 조회 (기존 충전기 UID로 EvChargerDTO 반환)
    EvChargerDTO findEvChargerByUid(@Param("evChargerUid") String evChargerUid);




    // ④ tbl_vtog_payment INSERT (dto.carId, dto.chargerId 사용)
    void insertVtogPayment(VtogInsertDTO dto);







}


