package com.app.ggshop.v1.service;

import com.app.ggshop.v1.dto.EvChargerDTO;
import com.app.ggshop.v1.dto.MemberDTO;
import com.app.ggshop.v1.dto.VtogInsertDTO;
import com.app.ggshop.v1.repository.MemberDAO;
import com.app.ggshop.v1.repository.VtogDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Slf4j
@Service
@RequiredArgsConstructor
public class VtogService {

    private final MemberDAO memberDAO;  // 기존 DAO 재사용
    private final VtogDAO vtogDAO;

    /**
     * 이메일로 회원 조회
     */
    public MemberDTO findMemberByEmail(String email) {
        return memberDAO.findByMemberEmail(email)
                .orElse(null);
    }

    /**
     * 충전기 UID로 충전기 조회
     */
    public EvChargerDTO findEvChargerByUid(String evChargerUid) {
        return vtogDAO.findEvChargerByUid(evChargerUid);
    }

    /**
     * V2G 거래 등록
     * ② tbl_car INSERT
     * ③ tbl_ev_charger 조회 (기존 충전기 UID로 id 가져오기)
     * ④ tbl_vtog_payment INSERT
     */
    @Transactional
    public void insertVtogTransaction(VtogInsertDTO dto) {

        log.info("==== V2G INSERT 시작 ====");
        log.info("dto : {}", dto);

        // ② tbl_car INSERT → dto.carId 자동 세팅
        vtogDAO.insertCar(dto);
        log.info("② tbl_car INSERT 완료 - carId: {}", dto.getCarId());

        // ③ 충전기 UID로 기존 충전기 조회
        EvChargerDTO charger = vtogDAO.findEvChargerByUid(dto.getEvChargerUid());
        if (charger == null) {
            throw new IllegalArgumentException("존재하지 않는 충전기 UID입니다: " + dto.getEvChargerUid());
        }
        dto.setVtogEvChargerId(charger.getId());
        log.info("③ tbl_ev_charger 조회 완료 - chargerId: {}", charger.getId());

        // ④ 거래번호 자동생성
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String transactionNumber = String.format("V2G-%s-%06d", today, dto.getCarId());
        dto.setVtogTransactionNumber(transactionNumber);
        log.info("④ 거래번호 생성 : {}", transactionNumber);

        // ④ tbl_vtog_payment INSERT
        vtogDAO.insertVtogPayment(dto);
        log.info("④ tbl_vtog_payment INSERT 완료");

        log.info("==== V2G INSERT 전체 완료 ====");
    }

    /**
     * V2G 거래 등록
     * ② tbl_car INSERT
     * ③ tbl_ev_charger 조회 (기존 충전기 UID로 id 가져오기)
     * ④ tbl_vtog_payment INSERT
     * 하나라도 실패하면 전체 롤백
     */
//    @Transactional
//    public void insertVtogTransaction(VtogInsertDTO dto) {
//
//        log.info("==== V2G INSERT 시작 ====");
//        log.info("dto : {}", dto);
//
//        // ② tbl_car INSERT → dto.carId 자동 세팅
//        vtogDAO.insertCar(dto);
//        log.info("② tbl_car INSERT 완료 - carId: {}", dto.getCarId());
//
//        // ③ 충전기 UID로 기존 충전기 조회 → chargerId 세팅
//        Long chargerId = vtogDAO.findEvChargerByUid(dto.getEvChargerUid());
//        if (chargerId == null) {
//            throw new IllegalArgumentException("존재하지 않는 충전기 UID입니다: " + dto.getEvChargerUid());
//        }
//        dto.setVtogEvChargerId(chargerId);
//        log.info("③ tbl_ev_charger 조회 완료 - chargerId: {}", chargerId);
//
//        // ④ 거래번호 자동생성 V2G-yyyyMMdd-carId (예: V2G-20260223-000004)
//        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
//        String transactionNumber = String.format("V2G-%s-%06d", today, dto.getCarId());
//        dto.setVtogTransactionNumber(transactionNumber);
//        log.info("④ 거래번호 생성 : {}", transactionNumber);
//
//        // ④ tbl_vtog_payment INSERT
//        vtogDAO.insertVtogPayment(dto);
//        log.info("④ tbl_vtog_payment INSERT 완료");
//
//        log.info("==== V2G INSERT 전체 완료 ====");
//    }


}
