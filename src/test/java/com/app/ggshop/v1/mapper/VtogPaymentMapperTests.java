//package com.app.ggshop.v1.mapper;
//
//import com.app.ggshop.v1.dto.VtogPaymentDTO;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//@Slf4j
//@SpringBootTest
//@Transactional
//class VtogPaymentMapperTests {
//
//    @Autowired
//    private VtogPaymentMapper vtogPaymentMapper;
//
//    @Test
//    @DisplayName("V2G 거래내역 전체 조회 테스트")
//    void findAll() {
//        // when
//        List<VtogPaymentDTO> list = vtogPaymentMapper.findAll();
//
//        // then
//        log.info("====================================");
//        log.info("조회 결과 수 : {}", list.size());
////        list.forEach(v -> log.info(
////                "고객명 : {} / 충전기 : {} / 충전량 : {} / 판매금액 : {}",
////                v.getMemberName(),
////                v.getEvChargerUid(),
////                v.getVtogPaymentSalesKwh(),
////                v.getVtogPaymentSalesPrice()
////        ));
//
////        list.forEach(v -> log.info(
////                "충전일자 : {} / 거래번호 : {} / 고객명 : {} / 충전기 : {} / 충전량 : {} / 판매금액 : {}",
////                v.getCreatedDate(),
////                v.getTransactionNumber(),
////                v.getMemberName(),
////                v.getEvChargerUid(),
////                v.getVtogPaymentSalesKwh(),
////                v.getVtogPaymentSalesPrice()
////        ));
//        log.info("====================================");
//
//        assertThat(list).isNotNull();
//    }
//}
