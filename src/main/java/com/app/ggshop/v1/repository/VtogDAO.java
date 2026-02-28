package com.app.ggshop.v1.repository;

import com.app.ggshop.v1.dto.EvChargerDTO;
import com.app.ggshop.v1.dto.VtogInsertDTO;
import com.app.ggshop.v1.mapper.VtogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VtogDAO {

    private final VtogMapper vtogMapper;

//    // ② tbl_car INSERT
//    public void insertCar(VtogInsertDTO dto) {
//        vtogMapper.insertCar(dto);
//    }
//
//
//
//    // ③ tbl_ev_charger 조회 (기존 충전기 UID로 id 조회)
//    public Long findEvChargerByUid(String evChargerUid) {
//        return vtogMapper.findEvChargerByUid(evChargerUid);
//    }
//
//    // ④ tbl_vtog_payment INSERT
//    public void insertVtogPayment(VtogInsertDTO dto) {
//        vtogMapper.insertVtogPayment(dto);
//    }

    // ② tbl_car INSERT
    public void insertCar(VtogInsertDTO dto) {
        vtogMapper.insertCar(dto);
    }

    // ③ tbl_ev_charger 조회 (기존 충전기 UID로 EvChargerDTO 반환)
    public EvChargerDTO findEvChargerByUid(String evChargerUid) {
        return vtogMapper.findEvChargerByUid(evChargerUid);
    }

    // ④ tbl_vtog_payment INSERT
    public void insertVtogPayment(VtogInsertDTO dto) {
        vtogMapper.insertVtogPayment(dto);
    }


}