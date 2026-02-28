//package com.app.ggshop.v1.mapper;
//
//import com.app.ggshop.v1.dto.MemberDTO;
//import com.app.ggshop.v1.dto.VtogInsertDTO;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
///**
// * VtogMapper 단위 테스트
// * - @SpringBootTest : 실제 DB 연결하여 테스트
// * - @Transactional  : 테스트 후 롤백 (DB 데이터 오염 방지)
// */
//@SpringBootTest
//@Transactional
//class VtogMapperTest {
//
//    @Autowired
//    private VtogMapper vtogMapper;
//
//    // ================================================================
//    // ① findMemberByEmail 테스트
//    // ================================================================
//
//    @Test
//    @DisplayName("이메일로 회원 조회 - 성공 케이스")
//    void findMemberByEmail_success() {
//        // given
//        // ※ 실제 DB에 존재하는 테스트 이메일로 변경하세요
//        String testEmail = "minpre@empas.com";
//
//        // when
//        MemberDTO member = vtogMapper.findMemberByEmail(testEmail);
//
//        // then
//        System.out.println("조회 결과 : " + member);
//
//        assertThat(member).isNotNull();
//        assertThat(member.getId()).isNotNull();
//        assertThat(member.getMemberEmail()).isEqualTo(testEmail);
//        assertThat(member.getMemberName()).isNotBlank();
//
//        System.out.println("회원 조회 성공");
//        System.out.println("   - id      : " + member.getId());
//        System.out.println("   - name    : " + member.getMemberName());
//        System.out.println("   - email   : " + member.getMemberEmail());
//        System.out.println("   - address : " + member.getMemberAddress());
//        System.out.println("   - status  : " + member.getMemberStatus());
//    }
//
//    @Test
//    @DisplayName("이메일로 회원 조회 - 존재하지 않는 이메일")
//    void findMemberByEmail_notFound() {
//        // given
//        String wrongEmail = "notexist_9999@example.com";
//
//        // when
//        MemberDTO member = vtogMapper.findMemberByEmail(wrongEmail);
//
//        // then
//        assertThat(member).isNull();
//        System.out.println("존재하지 않는 이메일 → null 반환 확인");
//    }
//
//    @Test
//    @DisplayName("이메일로 회원 조회 - member_status = active 인 회원만 조회")
//    void findMemberByEmail_onlyActive() {
//        // given
//        // ※ DB에 inactive 상태인 회원 이메일로 변경하세요
//        String inactiveEmail = "inactive@example.com";
//
//        // when
//        MemberDTO member = vtogMapper.findMemberByEmail(inactiveEmail);
//
//        // then
//        assertThat(member).isNull();
//        System.out.println("inactive 회원 → null 반환 확인 (active 회원만 조회)");
//    }
//
//
//
//
//    /**
//     * 공통 테스트용 DTO 생성
//     * ※ memberId는 실제 DB에 존재하는 값으로 변경하세요
//     */
//    private VtogInsertDTO createTestDto() {
//        VtogInsertDTO dto = new VtogInsertDTO();
//        dto.setMemberId(12L);                        // ※ 실제 DB member.id 로 변경
//        dto.setMemberAddress("서울 강남구 가로수길 5");  // ※ 실제 DB member.address 로 변경
//        dto.setCarPlateNumber("12가 3456");
//        dto.setCarEnergyGauge("60");
//        dto.setEvChargerUid("CHARGER-TEST-001");
//        dto.setVtogPaymentSalesKwh(30L);
//        dto.setVtogPaymentSalesPrice(15000L);
//        return dto;
//    }
//
//    // ================================================================
//    // ② insertCar 테스트
//    // ================================================================
//    @Test
//    @DisplayName("② tbl_car INSERT - carId 자동 세팅 확인")
//    void insertCar_success() {
//        // given
//        VtogInsertDTO dto = createTestDto();
//
//        // when
//        vtogMapper.insertCar(dto);
//
//        // then
//        System.out.println("② tbl_car INSERT 완료");
//        System.out.println("   - carId : " + dto.getCarId());
//
//        assertThat(dto.getCarId()).isNotNull();
//        assertThat(dto.getCarId()).isGreaterThan(0L);
//        System.out.println("carId 자동 세팅 확인");
//    }
//
//    // ================================================================
//    // ③ insertEvCharger 테스트
//    // ================================================================
//    @Test
//    @DisplayName("③ tbl_ev_charger INSERT - chargerId 자동 세팅 확인")
//    void insertEvCharger_success() {
//        // given
//        VtogInsertDTO dto = createTestDto();
//
//        // when
////        vtogMapper.insertEvCharger(dto);
//
//        // then
//        System.out.println("③ tbl_ev_charger INSERT 완료");
//        System.out.println("   - chargerId : " + dto.getChargerId());
//
//        assertThat(dto.getChargerId()).isNotNull();
//        assertThat(dto.getChargerId()).isGreaterThan(0L);
//        System.out.println("chargerId 자동 세팅 확인");
//    }
//
//    // ================================================================
//    // ④ insertVtogPayment 테스트
//    // ================================================================
//    @Test
//    @DisplayName("④ tbl_vtog_payment INSERT - 전체 흐름 확인")
//    void insertVtogPayment_success() {
//        // given
//        VtogInsertDTO dto = createTestDto();
//
//        // ② car INSERT 먼저
//        vtogMapper.insertCar(dto);
//        System.out.println("② carId : " + dto.getCarId());
//
//        // ③ charger INSERT
////        vtogMapper.insertEvCharger(dto);
////        System.out.println("③ chargerId : " + dto.getChargerId());
//
//        // ④ payment 전 세팅
//        dto.setVtogEvChargerId(dto.getChargerId());
//        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
//        dto.setVtogTransactionNumber(String.format("V2G-%s-%06d", today, dto.getCarId()));
//        System.out.println("④ 거래번호 : " + dto.getVtogTransactionNumber());
//
//        // when
//        vtogMapper.insertVtogPayment(dto);
//
//        // then
//        System.out.println("tbl_vtog_payment INSERT 완료");
//        assertThat(dto.getCarId()).isNotNull();
//        assertThat(dto.getVtogEvChargerId()).isNotNull();
//        assertThat(dto.getVtogTransactionNumber()).isNotBlank();
//    }
//
//
//
//
//
//
//
//}
