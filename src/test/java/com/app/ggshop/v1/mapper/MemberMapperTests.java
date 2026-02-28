//package com.app.ggshop.v1.mapper;
//
//import com.app.ggshop.v1.common.enumeration.Provider;
//import com.app.ggshop.v1.common.enumeration.Status;
//import com.app.ggshop.v1.dto.MemberDTO;
//import com.app.ggshop.v1.repository.MemberDAO;
//import com.app.ggshop.v1.service.member.MemberService;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Slf4j
//public class MemberMapperTests {
//
//    @Autowired
//    private MemberMapper memberMapper;
//    @Autowired
//    private MemberService memberService;
//
//    @Test
//    public void testInsert(){
//        MemberDTO memberDTO = new MemberDTO();
//        memberDTO.setMemberEmail("sunofgod@gmail.com");
//        memberDTO.setMemberPassword("1234567890");
//        memberDTO.setMemberName("예수그리스도");
//        memberDTO.setMemberAddress("이스라엘 예루살렘");
//
//        memberDTO.setProvider(Provider.THREETIER);
//
//        memberMapper.insert(memberDTO);
//        memberMapper.insertOauth(memberDTO.toOAuthVO());
//    }
//
//
//    @Autowired
//    private MemberDAO memberDao;
//
//
//    @Test
//    @DisplayName("회원 DAO insert 테스트")
//    void test_Insert() {
//        // given
//        MemberDTO memberDTO = new MemberDTO();
//        memberDTO.setMemberEmail("dao@test.com");
//        memberDTO.setMemberPassword("Test1234!");
//        memberDTO.setMemberName("테스트");
//        memberDTO.setMemberAddress("서울특별시 용산구 새창로 181");
//
//        // when
//        memberDao.save(memberDTO);
//
//        // then
//        log.info("====================================");
//        log.info("insert id : {}", memberDTO.getId());
//        log.info("insert email : {}", memberDTO.getMemberEmail());
//        log.info("insert name : {}", memberDTO.getMemberName());
//        log.info("====================================");
//
//        assertThat(memberDTO.getId()).isNotNull();
//    }
//
//
//
//    // 1. 이메일 검사 테스트
//    @Test
//    void checkEmail_Exist_Test() {
//        // given
//        String existEmail = "test@gmail.com";  // DB에 있는 이메일
//
//        // when
//        boolean result = memberService.checkEmail(existEmail);
//
//        // then
//        log.info("▶ 이메일 중복 검사 (존재하는 이메일)");
//        log.info("이메일: {}", existEmail);
//        log.info("결과: {}", result ? "사용 가능" : "사용 불가");
//
//        assertFalse(result);  // 이미 존재하므로 false
//    }
//
//    // 2. 일반 회원가입 테스트
//    @Test
//    void join_Test() {
//        // given
//        MemberDTO memberDTO = new MemberDTO();
//        memberDTO.setMemberEmail("newuser@gmail.com");
//        memberDTO.setMemberPassword("password123");
//        memberDTO.setMemberName("새로운유저");
//
//        // when
//        memberService.join(memberDTO);
//
//        // then
//        log.info("▶ 일반 회원가입 완료");
//        log.info("ID: {}", memberDTO.getId());  // insert 후 자동 생성된 ID
//        log.info("이메일: {}", memberDTO.getMemberEmail());
//        log.info("이름: {}", memberDTO.getMemberName());
//        log.info("Provider: {}", memberDTO.getProvider());
//
//        assertNotNull(memberDTO.getId());
//        assertEquals(Provider.THREETIER, memberDTO.getProvider());
//    }
//
//    @Test
//    @DisplayName("회원 목록 조회 테스트")
//    void findAll() {
//        // given
//        Map<String, Object> params = new HashMap<>();
//        params.put("searchType", "all");
//        params.put("keyword", "");
//        params.put("pageSize", 10);
//        params.put("offset", 0);
//
//        // when
//        List<MemberDTO> memberList = memberMapper.findAll(params);
//
//        // then
//        log.info("====================================");
//        log.info("조회 결과 수 : {}", memberList.size());
//        memberList.forEach(m -> log.info("회원 : {} / {}", m.getMemberName(), m.getMemberEmail()));
//        log.info("====================================");
//
//        assertThat(memberList).isNotNull();
//    }
//
//    @Test
//    @DisplayName("회원 전체 수 조회 테스트")
//    void countAll() {
//        // given
//        Map<String, Object> params = new HashMap<>();
//        params.put("searchType", "all");
//        params.put("keyword", "");
//
//        // when
//        int count = memberMapper.countAll(params);
//
//        // then
//        log.info("====================================");
//        log.info("전체 회원 수 : {}", count);
//        log.info("====================================");
//
//        assertThat(count).isGreaterThanOrEqualTo(0);
//    }
//
//    @Test
//    @DisplayName("회원 단건 조회 테스트")
//    void findById() {
//        // given
//        Long id = 1L; // DB에 실제 존재하는 id로 변경
//
//        // when
//        MemberDTO member = memberMapper.findById(id);
//
//        // then
//        log.info("====================================");
//        log.info("회원 id : {}", member.getId());
//        log.info("회원 이메일 : {}", member.getMemberEmail());
//        log.info("회원 이름 : {}", member.getMemberName());
//        log.info("====================================");
//
//        assertThat(member).isNotNull();
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("회원 수정 테스트")
//    void update() {
//        // given
//        MemberDTO memberDTO = new MemberDTO();
//        memberDTO.setId(1L);
//        memberDTO.setMemberName("수정된이름");
//        memberDTO.setMemberNickname("수정닉네임");
//        memberDTO.setMemberBirth("1990-01-01");
//        memberDTO.setMemberAddress("서울특별시 강남구");
//        memberDTO.setMemberStatus(Status.ACTIVE); // ← 대문자
//
//        // when
//        memberMapper.update(memberDTO);
//
//        // then
//        MemberDTO updated = memberMapper.findById(1L);
//        log.info("====================================");
//        log.info("수정된 이름 : {}", updated.getMemberName());
//        log.info("수정된 닉네임 : {}", updated.getMemberNickname());
//        log.info("====================================");
//
//        assertThat(updated.getMemberName()).isEqualTo("수정된이름");
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("회원 삭제 테스트")
//    void deleteById() {
//        // given
//        Long id = 1L;
//
//        // when - oauth 먼저 삭제 후 회원 삭제
//        memberMapper.deleteOauthByMemberId(id);
//        memberMapper.deleteById(id);
//
//        // then
//        MemberDTO deleted = memberMapper.findById(id);
//        log.info("====================================");
//        log.info("삭제 결과 : {}", deleted);
//        log.info("====================================");
//
//        assertThat(deleted).isNull();
//    }
//}
