package com.app.ggshop.v1.mapper;

import com.app.ggshop.v1.common.enumeration.Status;
import com.app.ggshop.v1.common.pagination.Criteria;
import com.app.ggshop.v1.domain.EvChargerVO;
import com.app.ggshop.v1.dto.EvChargerDTO;
import com.app.ggshop.v1.dto.PostWithPagingDTO;
import com.app.ggshop.v1.repository.EvChargerDAO;
import com.app.ggshop.v1.service.EvChargerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
@Transactional
@Commit
class EvChargerTests {

    @Autowired
    private EvChargerMapper evChargerMapper;

    @Autowired
    private EvChargerDAO evChargerDAO;

    @Autowired
    private EvChargerService evChargerService;

//    @Test
//    void insertEvChargerTest() {
//        // given
//        EvChargerVO evChargerVO = EvChargerVO.builder()
//                .evChargerUid("EV-TEST-1533333")
//                .evChargerAddress("서울시 강남구 테헤란로 123")
//                .evChargerContent("지하 1층, 장애인 주차구역 옆")
//                .evChargerStatus(Status.ACTIVE)   // enum('active','inactive')
//                .evChargerMod(Status.ACTIVE)      // enum('active','inactive')
//                .setupDate("2026-02-01 10:00:00") // DATETIME
//                .companyId(2L)                    // 반드시 tbl_company에 존재
//                .build();
//
//        // when
//        evChargerMapper.insertEvCharger(evChargerVO);
//
//        // then
//        log.info("▶ 생성된 충전기 ID: {}", evChargerVO.getId());
//    }

    @Test
    void selectEvChargerListTest() {
        // given
        Long companyId = 1L;

        // when
        List<EvChargerVO> chargerList =
                evChargerMapper.selectEvChargerList(companyId);

        // then
        log.info("▶ 충전소 개수: {}", chargerList.size());

        chargerList.forEach(charger -> {
            log.info("===============");
            log.info("ID           : {}", charger.getId());
            log.info("UID          : {}", charger.getEvChargerUid());
            log.info("주소          : {}", charger.getEvChargerAddress());
            log.info("특이사항      : {}", charger.getEvChargerContent());
            log.info("상태          : {}", charger.getEvChargerStatus());
            log.info("설치일        : {}", charger.getSetupDate());
            log.info("회사 ID       : {}", charger.getCompanyId());
        });
    }


    @Test
    void selectAllEvChargerListTest() {
        List<EvChargerVO> chargerList =
                evChargerMapper.selectAllEvChargers();

        log.info("▶ 전체 충전소 개수: {}", chargerList.size());

        chargerList.forEach(charger -> {
            log.info("===============");
            log.info("ID           : {}", charger.getId());
            log.info("UID          : {}", charger.getEvChargerUid());
            log.info("주소          : {}", charger.getEvChargerAddress());
            log.info("특이사항      : {}", charger.getEvChargerContent());
            log.info("상태          : {}", charger.getEvChargerStatus());
            log.info("설치일        : {}", charger.getSetupDate());
            log.info("회사 ID       : {}", charger.getCompanyId());
        });
    }


    @Test
    void selectAllEvChargerList_Test() {

        // given
        int page = 1;
        int size = 10;
        int offset = (page - 1) * size;

        Criteria criteria = new Criteria(page, size);

        // when
        List<EvChargerDTO> chargerList = evChargerMapper.selectAll(criteria); //


        int totalCount = evChargerMapper.selectTotalCount();

        // then
        log.info("▶ 전체 충전소 개수: {}", totalCount);
        log.info("▶ 현재 페이지 데이터 개수: {}", chargerList.size());

        chargerList.forEach(charger -> {
            log.info("===============");
            log.info("ID           : {}", charger.getId());
            log.info("UID          : {}", charger.getStationNumber());
            log.info("주소          : {}", charger.getInstallAddress());
            log.info("특이사항      : {}", charger.getChargerNote());
            log.info("상태          : {}", charger.getEvChargerStatus());
            log.info("설치일        : {}", charger.getInstallDate());
            log.info("회사 ID       : {}", charger.getCompanyId());
        });


    }

//    @Test
//    void list_ShouldReturnPagedDTO_With10PerPage() {
//        // given
//        int page = 1;
//
//        // when
//        PostWithPagingDTO result = evChargerService.list(page);
//
//        // then
//        assertNotNull(result);
//        assertNotNull(result.getEvChargerList());
//
//        List<EvChargerDTO> list = result.getEvChargerList();
//
//        System.out.println("▶ 전체 조회 페이지: " + page);
//        System.out.println("▶ 현재 페이지 데이터 개수: " + list.size());
//        System.out.println("▶ Criteria 확인: " + result.getCriteria());
//
//        // DB 11개 기준, 한 페이지 10개
//        assertEquals(10, list.size());
//        assertEquals(1, result.getCriteria().getPage());
//
//        // 일부 데이터 확인
//        EvChargerDTO first = list.get(0);
//        System.out.println("첫 번째 충전소 UID: " + first.getStationNumber());
//        System.out.println("첫 번째 충전소 주소: " + first.getInstallAddress());
//        System.out.println("첫 번째 충전소 상태: " + first.getEvChargerStatus());
//    }

    @Test
    void searchEvCharger_Test() {
        // given
        String searchType = "all";  // "all", "stationNumber", "address"
        String keyword = "서울";
        int page = 1;
        Criteria criteria = new Criteria(page, 100); // 임시로 total=100

        // when
        List<EvChargerDTO> searchResult = evChargerMapper.selectBySearch(searchType, keyword, criteria);
        int totalCount = evChargerMapper.selectTotalBySearch(searchType, keyword);

        // then
        log.info("▶ 검색 타입: {}", searchType);
        log.info("▶ 검색 키워드: {}", keyword);
        log.info("▶ 검색 결과 개수: {}", searchResult.size());
        log.info("▶ 전체 결과 개수: {}", totalCount);

        searchResult.forEach(charger -> {
            log.info("===============");
            log.info("ID           : {}", charger.getId());
            log.info("충전소번호    : {}", charger.getStationNumber());
            log.info("주소          : {}", charger.getInstallAddress());
            log.info("특이사항      : {}", charger.getChargerNote());
        });
    }

    @Test
    void searchByStationNumber_Test() {
        // given
        String searchType = "stationNumber";
        String keyword = "153";
        int page = 1;
        Criteria criteria = new Criteria(page, 100);

        // when
        List<EvChargerDTO> searchResult = evChargerMapper.selectBySearch(searchType, keyword, criteria);
        int totalCount = evChargerMapper.selectTotalBySearch(searchType, keyword);

        // then
        log.info("▶ 검색 타입: {}", searchType);
        log.info("▶ 검색 키워드: {}", keyword);
        log.info("▶ 검색 결과 개수: {}", searchResult.size());
        log.info("▶ 전체 결과 개수: {}", totalCount);

        searchResult.forEach(charger -> {
            log.info("충전소번호: {}, 주소: {}",
                    charger.getStationNumber(),
                    charger.getInstallAddress());
        });
    }

    @Test
    void searchByAddress_Test() {
        // given
        String searchType = "address";
        String keyword = "강남구";
        int page = 1;
        Criteria criteria = new Criteria(page, 100);

        // when
        List<EvChargerDTO> searchResult = evChargerMapper.selectBySearch(searchType, keyword, criteria);
        int totalCount = evChargerMapper.selectTotalBySearch(searchType, keyword);

        // then
        log.info("▶ 검색 타입: {}", searchType);
        log.info("▶ 검색 키워드: {}", keyword);
        log.info("▶ 검색 결과 개수: {}", searchResult.size());
        log.info("▶ 전체 결과 개수: {}", totalCount);

        searchResult.forEach(charger -> {
            log.info("주소: {}", charger.getInstallAddress());
        });
    }


    @Test
    void searchEvChargerDAO_Test() {
        // given
        String searchType = "all";
        String keyword = "서울";
        int page = 1;
        Criteria criteria = new Criteria(page, 100);

        // when
        List<EvChargerDTO> searchResult = evChargerDAO.findBySearch(searchType, keyword, criteria);
        int totalCount = evChargerDAO.findTotalBySearch(searchType, keyword);

        // then
        log.info("▶ 검색 타입: {}", searchType);
        log.info("▶ 검색 키워드: {}", keyword);
        log.info("▶ 검색 결과 개수: {}", searchResult.size());
        log.info("▶ 전체 결과 개수: {}", totalCount);

        searchResult.forEach(charger -> {
            log.info("ID: {}, 충전소번호: {}, 주소: {}",
                    charger.getId(),
                    charger.getStationNumber(),
                    charger.getInstallAddress());
        });
    }


    @Test
    void searchEvChargerService_Test() {
        // given
        String searchType = "all";
        String keyword = "서울";
        int page = 1;

        // when
        PostWithPagingDTO result = evChargerService.searchEvChargers(searchType, keyword, page);

        // then
        log.info("▶ 검색 타입: {}", searchType);
        log.info("▶ 검색 키워드: {}", keyword);
        log.info("▶ 검색 결과 개수: {}", result.getEvChargerList().size());
        log.info("▶ startPage: {}, endPage: {}, realEnd: {}",
                result.getCriteria().getStartPage(),
                result.getCriteria().getEndPage(),
                result.getCriteria().getRealEnd());

        result.getEvChargerList().forEach(charger -> {
            log.info("ID: {}, 충전소번호: {}, 주소: {}",
                    charger.getId(),
                    charger.getStationNumber(),
                    charger.getInstallAddress());
        });
    }

    @Test
    void selectById_Test() {
        // given
        Long id = 1L;  // 존재하는 ID로 테스트

        // when
        EvChargerDTO charger = evChargerMapper.selectById(id);

        // then
        log.info("▶ 조회된 충전소 정보:");
        log.info("ID           : {}", charger.getId());
        log.info("충전소번호    : {}", charger.getStationNumber());
        log.info("설치주소      : {}", charger.getInstallAddress());
        log.info("특이사항      : {}", charger.getChargerNote());
        log.info("설치일자      : {}", charger.getInstallDate());
        log.info("회사 ID       : {}", charger.getCompanyId());

        // 검증
        assertNotNull(charger);
        assertEquals(id, charger.getId());
    }


    @Test
    void findById_DAO_Test() {
        // given
        Long id = 1L;

        // when
        EvChargerDTO charger = evChargerDAO.findById(id);

        // then
        log.info("▶ DAO 조회된 충전소 정보:");
        log.info("ID           : {}", charger.getId());
        log.info("충전소번호    : {}", charger.getStationNumber());
        log.info("설치주소      : {}", charger.getInstallAddress());
        log.info("특이사항      : {}", charger.getChargerNote());
        log.info("설치일자      : {}", charger.getInstallDate());

        // 검증
        assertNotNull(charger);
        assertEquals("EV-SEOUL-001", charger.getStationNumber());
    }

    @Test
    void getEvChargerById_Service_Test() {
        // given
        Long id = 1L;

        // when
        EvChargerDTO charger = evChargerService.getEvChargerById(id);

        // then
        log.info("▶ Service 조회된 충전소 정보:");
        log.info("ID           : {}", charger.getId());
        log.info("충전소번호    : {}", charger.getStationNumber());
        log.info("설치주소      : {}", charger.getInstallAddress());
        log.info("특이사항      : {}", charger.getChargerNote());
        log.info("설치일자      : {}", charger.getInstallDate());

        // 검증
        assertNotNull(charger);
        assertEquals("EV-SEOUL-001", charger.getStationNumber());
        assertEquals("Seoul Gangnam Station Parking Lot", charger.getInstallAddress());
    }


    @Test
    void updateEvCharger_Test() {
        // given
        Long id = 1L;

        // 수정 전 데이터 조회
        EvChargerDTO beforeUpdate = evChargerMapper.selectById(id);
        log.info("▶ 수정 전 데이터:");
        log.info("ID           : {}", beforeUpdate.getId());
        log.info("주소          : {}", beforeUpdate.getInstallAddress());
        log.info("특이사항      : {}", beforeUpdate.getChargerNote());

        // 수정할 데이터 준비
        EvChargerDTO updateData = new EvChargerDTO();
        updateData.setId(id);
        updateData.setInstallAddress("서울 강남구 테헤란로 123 (수정됨)");
        updateData.setChargerNote("테스트 수정 - 특이사항 변경");

        // when
        evChargerMapper.updateEvCharger(updateData);

        // 수정 후 데이터 조회
        EvChargerDTO afterUpdate = evChargerMapper.selectById(id);

        // then
        log.info("▶ 수정 후 데이터:");
        log.info("ID           : {}", afterUpdate.getId());
        log.info("주소          : {}", afterUpdate.getInstallAddress());
        log.info("특이사항      : {}", afterUpdate.getChargerNote());

        // 검증
        assertEquals("서울 강남구 테헤란로 123 (수정됨)", afterUpdate.getInstallAddress());
        assertEquals("테스트 수정 - 특이사항 변경", afterUpdate.getChargerNote());
    }

    @Test
    void updateEvCharger_DAO_Test() {
        // given
        Long id = 1L;

        // 수정 전 데이터 조회
        EvChargerDTO beforeUpdate = evChargerDAO.findById(id);
        log.info("▶ DAO 수정 전 데이터:");
        log.info("주소          : {}", beforeUpdate.getInstallAddress());
        log.info("특이사항      : {}", beforeUpdate.getChargerNote());

        // 수정할 데이터 준비
        EvChargerDTO updateData = new EvChargerDTO();
        updateData.setId(id);
        updateData.setInstallAddress("서울 서초구 서초대로 78길 (DAO 테스트)");
        updateData.setChargerNote("DAO 레벨 테스트 - 수정 완료");

        // when
        evChargerDAO.update(updateData);

        // 수정 후 데이터 조회
        EvChargerDTO afterUpdate = evChargerDAO.findById(id);

        // then
        log.info("▶ DAO 수정 후 데이터:");
        log.info("주소          : {}", afterUpdate.getInstallAddress());
        log.info("특이사항      : {}", afterUpdate.getChargerNote());

        // 검증
        assertEquals("서울 서초구 서초대로 78길 (DAO 테스트)", afterUpdate.getInstallAddress());
        assertEquals("DAO 레벨 테스트 - 수정 완료", afterUpdate.getChargerNote());
    }

    @Test
    void updateEvCharger_Service_Test() {
        // given
        Long id = 1L;

        // 수정 전 데이터 조회
        EvChargerDTO beforeUpdate = evChargerService.getEvChargerById(id);
        log.info("▶ Service 수정 전 데이터:");
        log.info("주소          : {}", beforeUpdate.getInstallAddress());
        log.info("특이사항      : {}", beforeUpdate.getChargerNote());

        // 수정할 데이터 준비
        EvChargerDTO updateData = new EvChargerDTO();
        updateData.setId(id);
        updateData.setInstallAddress("서울 종로구 종로 1 (Service 테스트)");
        updateData.setChargerNote("Service 레벨 테스트 - 최종 수정");

        // when
        evChargerService.updateEvCharger(updateData);

        // 수정 후 데이터 조회
        EvChargerDTO afterUpdate = evChargerService.getEvChargerById(id);

        // then
        log.info("▶ Service 수정 후 데이터:");
        log.info("주소          : {}", afterUpdate.getInstallAddress());
        log.info("특이사항      : {}", afterUpdate.getChargerNote());

        // 검증
        assertEquals("서울 종로구 종로 1 (Service 테스트)", afterUpdate.getInstallAddress());
        assertEquals("Service 레벨 테스트 - 최종 수정", afterUpdate.getChargerNote());
    }


    @Test
    @DisplayName("존재하는 충전소 번호 - count 1 반환")
    void countByEvChargerUid_exists() {
        // given
        String existingUid = "EV-SEOUL-001";

        // when
        int count = evChargerMapper.countByEvChargerUid(existingUid);

        // then
        log.info("====================================");
        log.info("테스트 uid : {}", existingUid);
        log.info("조회 결과 count : {}", count);
        log.info("중복 여부 : {}", count > 0 ? "중복 있음" : "중복 없음");
        log.info("====================================");

        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("존재하지 않는 충전소 번호 - count 0 반환")
    void countByEvChargerUid_notExists() {
        // given
        String notExistingUid = "NOT-EXIST-999";

        // when
        int count = evChargerMapper.countByEvChargerUid(notExistingUid);

        // then
        log.info("====================================");
        log.info("테스트 uid : {}", notExistingUid);
        log.info("조회 결과 count : {}", count);
        log.info("중복 여부 : {}", count > 0 ? "중복 있음" : "중복 없음");
        log.info("====================================");

        assertThat(count).isEqualTo(0);
    }

    @Autowired
    private EvChargerDAO evChargerDao;

    @Test
    @DisplayName("중복된 충전소 번호 - true 반환")
    void isDuplicateEvChargerUid_exists() {
        // given
        String existingUid = "EV-SEOUL-001";

        // when
        boolean result = evChargerDao.isDuplicateEvChargerUid(existingUid);

        // then
        log.info("====================================");
        log.info("테스트 uid : {}", existingUid);
        log.info("중복 여부 : {}", result);
        log.info("====================================");

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("존재하지 않는 충전소 번호 - false 반환")
    void isDuplicateEvChargerUid_notExists() {
        // given
        String notExistingUid = "NOT-EXIST-999";

        // when
        boolean result = evChargerDao.isDuplicateEvChargerUid(notExistingUid);

        // then
        log.info("====================================");
        log.info("테스트 uid : {}", notExistingUid);
        log.info("중복 여부 : {}", result);
        log.info("====================================");

        assertThat(result).isFalse();
    }


    @Test
    @DisplayName("중복된 충전소 번호 - true 반환")
    void isDuplicateEvChargerUid_service_exists() {
        // given
        String existingUid = "EV-SEOUL-001";

        // when
        boolean result = evChargerService.isDuplicateEvChargerUid(existingUid);

        // then
        log.info("====================================");
        log.info("테스트 uid : {}", existingUid);
        log.info("중복 여부 : {}", result);
        log.info("====================================");

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("존재하지 않는 충전소 번호 - false 반환")
    void isDuplicateEvChargerUid_service_notExists() {
        // given
        String notExistingUid = "NOT-EXIST-999";

        // when
        boolean result = evChargerService.isDuplicateEvChargerUid(notExistingUid);

        // then
        log.info("====================================");
        log.info("테스트 uid : {}", notExistingUid);
        log.info("중복 여부 : {}", result);
        log.info("====================================");

        assertThat(result).isFalse();
    }
}