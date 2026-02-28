//package com.app.ggshop.v1.mapper;
//
//import com.app.ggshop.v1.common.pagination.Criteria;
//import com.app.ggshop.v1.dto.CompanyEmployeeDTO;
//import com.app.ggshop.v1.dto.PostWithPagingDTO;
//import com.app.ggshop.v1.repository.CompanyEmployeeDAO;
//import com.app.ggshop.v1.service.CompanyEmployeeService;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Slf4j
//public class CompanyEmployeeMapperTests {
//
//    @Autowired
//    private CompanyEmployeeMapper companyEmployeeMapper;
//
//    @Autowired
//    private CompanyEmployeeDAO companyEmployeeDAO;
//
//    @Test
//    void insertEmployee_Test() {
//        // given
//        CompanyEmployeeDTO employeeDTO = new CompanyEmployeeDTO();
//        employeeDTO.setEmployeeName("홍길동");
//        employeeDTO.setPosition("사원");
//        employeeDTO.setEmail("hong@company.com");
//        employeeDTO.setEmployeeNumber("EMP-001");
//        employeeDTO.setDepartment("개발팀");
//        employeeDTO.setEmployeeNote("신입 개발자");
//        employeeDTO.setJoinDate("2026-02-15");
//        employeeDTO.setEmployeeCompanyId(1L);  // 회사 ID (FK)
//
//        // when
//        companyEmployeeMapper.insertEmployee(employeeDTO);
//
//        // then
//        log.info("▶ 임직원 등록 완료");
//        log.info("ID: {}", employeeDTO.getId());
//        log.info("이름: {}", employeeDTO.getEmployeeName());
//        log.info("직급: {}", employeeDTO.getPosition());
//        log.info("이메일: {}", employeeDTO.getEmail());
//        log.info("사원번호: {}", employeeDTO.getEmployeeNumber());
//        log.info("부서: {}", employeeDTO.getDepartment());
//        log.info("가입일: {}", employeeDTO.getJoinDate());
//
//        assertNotNull(employeeDTO.getId());
//        assertEquals("홍길동", employeeDTO.getEmployeeName());
//    }
//
//    @Test
//    void saveEmployee_DAO_Test() {
//        // given
//        CompanyEmployeeDTO employeeDTO = new CompanyEmployeeDTO();
//        employeeDTO.setEmployeeName("김철수");
//        employeeDTO.setPosition("대리");
//        employeeDTO.setEmail("kim@company.com");
//        employeeDTO.setEmployeeNumber("EMP-002");
//        employeeDTO.setDepartment("영업팀");
//        employeeDTO.setEmployeeNote("영업 담당");
//        employeeDTO.setJoinDate("2026-02-15");
//        employeeDTO.setEmployeeCompanyId(1L);
//
//        // when
//        companyEmployeeDAO.save(employeeDTO);
//
//        // then
//        log.info("▶ DAO 임직원 등록 완료");
//        log.info("ID: {}", employeeDTO.getId());
//        log.info("이름: {}", employeeDTO.getEmployeeName());
//        log.info("직급: {}", employeeDTO.getPosition());
//        log.info("부서: {}", employeeDTO.getDepartment());
//
//        assertNotNull(employeeDTO.getId());
//        assertEquals("김철수", employeeDTO.getEmployeeName());
//    }
//
//
//    @Autowired
//    private CompanyEmployeeService companyEmployeeService;
//
//    @Test
//    void registerEmployee_Service_Test() {
//        // given
//        CompanyEmployeeDTO employeeDTO = new CompanyEmployeeDTO();
//        employeeDTO.setEmployeeName("박영희");
//        employeeDTO.setPosition("과장");
//        employeeDTO.setEmail("park@company.com");
//        employeeDTO.setEmployeeNumber("EMP-003");
//        employeeDTO.setDepartment("인사팀");
//        employeeDTO.setEmployeeNote("인사 담당");
//        employeeDTO.setJoinDate("2026-02-15");
//        employeeDTO.setEmployeeCompanyId(1L);
//
//        // when
//        companyEmployeeService.registerEmployee(employeeDTO);
//
//        // then
//        log.info("▶ Service 임직원 등록 완료");
//        log.info("ID: {}", employeeDTO.getId());
//        log.info("이름: {}", employeeDTO.getEmployeeName());
//        log.info("직급: {}", employeeDTO.getPosition());
//        log.info("이메일: {}", employeeDTO.getEmail());
//        log.info("부서: {}", employeeDTO.getDepartment());
//
//        assertNotNull(employeeDTO.getId());
//        assertEquals("박영희", employeeDTO.getEmployeeName());
//        assertEquals("과장", employeeDTO.getPosition());
//    }
//
//    @Test
//    void selectAll_Test() {
//        // given
//        int page = 1;
//        int total = companyEmployeeMapper.selectTotal();
//        Criteria criteria = new Criteria(page, total);
//
//        log.info("▶ 전체 개수: {}", total);
//
//        // when
//        List<CompanyEmployeeDTO> employeeList = companyEmployeeMapper.selectAll(criteria);
//
//        // then
//        log.info("▶ 조회된 임직원 목록:");
//        log.info("조회 개수: {}", employeeList.size());
//
//        employeeList.forEach(emp -> {
//            log.info("=========================================");
//            log.info("ID: {}", emp.getId());
//            log.info("이름: {}", emp.getEmployeeName());
//            log.info("직급: {}", emp.getPosition());
//            log.info("사원번호: {}", emp.getEmployeeNumber());
//            log.info("부서: {}", emp.getDepartment());
//            log.info("이메일: {}", emp.getEmail());
//            log.info("가입일: {}", emp.getJoinDate());
//        });
//
//        assertTrue(employeeList.size() <= criteria.getRowCount());
//    }
//
//    @Test
//    void selectTotal_Test() {
//        // when
//        int total = companyEmployeeMapper.selectTotal();
//
//        // then
//        log.info("▶ 전체 임직원 수: {}", total);
//
//        assertTrue(total >= 0);
//    }
//
//
//    @Test
//    void findAll_DAO_Test() {
//        // given
//        int page = 1;
//        int total = companyEmployeeDAO.findTotal();
//        Criteria criteria = new Criteria(page, total);
//
//        log.info("▶ 전체 임직원 수: {}", total);
//
//        // when
//        List<CompanyEmployeeDTO> employeeList = companyEmployeeDAO.findAll(criteria);
//
//        // then
//        log.info("▶ DAO 조회된 임직원 목록:");
//        log.info("조회 개수: {}", employeeList.size());
//
//        employeeList.forEach(emp -> {
//            log.info("=========================================");
//            log.info("ID: {}", emp.getId());
//            log.info("이름: {}", emp.getEmployeeName());
//            log.info("직급: {}", emp.getPosition());
//            log.info("사원번호: {}", emp.getEmployeeNumber());
//            log.info("부서: {}", emp.getDepartment());
//            log.info("이메일: {}", emp.getEmail());
//            log.info("가입일: {}", emp.getJoinDate());
//        });
//
//        assertTrue(employeeList.size() <= criteria.getRowCount());
//        assertEquals(total, companyEmployeeDAO.findTotal());
//    }
//
//    @Test
//    void findTotal_DAO_Test() {
//        // when
//        int total = companyEmployeeDAO.findTotal();
//
//        // then
//        log.info("▶ DAO 전체 임직원 수: {}", total);
//
//        assertTrue(total >= 0);
//    }
//
//    @Test
//    void list_Service_Test() {
//        // given
//        int page = 1;
//
//        // when
//        PostWithPagingDTO result = companyEmployeeService.list(page);
//
//        // then
//        log.info("▶ Service 목록 조회 결과:");
//        log.info("조회 개수: {}", result.getEmployeeList().size());
//        log.info("startPage: {}, endPage: {}, realEnd: {}",
//                result.getCriteria().getStartPage(),
//                result.getCriteria().getEndPage(),
//                result.getCriteria().getRealEnd());
//
//        result.getEmployeeList().forEach(emp -> {
//            log.info("=========================================");
//            log.info("ID: {}, 이름: {}, 직급: {}, 부서: {}",
//                    emp.getId(),
//                    emp.getEmployeeName(),
//                    emp.getPosition(),
//                    emp.getDepartment());
//        });
//
//        assertNotNull(result.getEmployeeList());
//        assertNotNull(result.getCriteria());
//    }
//
//        @Test
//        @DisplayName("존재하는 사원번호 - count 1 반환")
//        void countByEmployeeNumber_exists() {
//            // given
//            String existingNumber = "EMP-001"; // DB에 실제 존재하는 사원번호로 변경
//
//            // when
//            int count = companyEmployeeMapper.countByEmployeeNumber(existingNumber);
//
//            // then
//            log.info("====================================");
//            log.info("테스트 사원번호 : {}", existingNumber);
//            log.info("조회 결과 count : {}", count);
//            log.info("중복 여부 : {}", count > 0 ? "중복 있음" : "중복 없음");
//            log.info("====================================");
//
//            assertThat(count).isEqualTo(1);
//        }
//
//        @Test
//        @DisplayName("존재하지 않는 사원번호 - count 0 반환")
//        void countByEmployeeNumber_notExists() {
//            // given
//            String notExistingNumber = "NOT-EXIST-999";
//
//            // when
//            int count = companyEmployeeMapper.countByEmployeeNumber(notExistingNumber);
//
//            // then
//            log.info("====================================");
//            log.info("테스트 사원번호 : {}", notExistingNumber);
//            log.info("조회 결과 count : {}", count);
//            log.info("중복 여부 : {}", count > 0 ? "중복 있음" : "중복 없음");
//            log.info("====================================");
//
//            assertThat(count).isEqualTo(0);
//        }
//}