package com.app.ggshop.v1.service.member;

import com.app.ggshop.v1.common.enumeration.Provider;
import com.app.ggshop.v1.common.exception.LoginFailException;
import com.app.ggshop.v1.domain.MemberVO;
import com.app.ggshop.v1.dto.MemberDTO;
import com.app.ggshop.v1.repository.MemberDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class MemberService {
    private final MemberDAO memberDAO;

    //    이메일 검사(true: 사용가능)
    public boolean checkEmail(String memberEmail){
        return memberDAO.findByMemberEmail(memberEmail).isEmpty();
    }

    //    회원가입
    public void join(MemberDTO memberDTO){
        memberDTO.setProvider(Provider.THREETIER);
        memberDAO.save(memberDTO);
        memberDAO.saveOAuth(memberDTO.toOAuthVO());
    }

    //    회원가입
    public void kakaoJoin(MemberDTO memberDTO){
        memberDTO.setProvider(Provider.KAKAO);
        memberDAO.save(memberDTO);
        memberDAO.saveOAuth(memberDTO.toOAuthVO());
    }

    //    로그인
    public MemberDTO login(MemberDTO memberDTO){
        Optional<MemberVO> foundMember = memberDAO.findForLogin(memberDTO);
        return toDTO(foundMember.orElseThrow(LoginFailException::new));
    }

    /**
     * 로그인
     */
    public MemberDTO login(String memberEmail, String memberPassword) {
        log.info("▶ 로그인 시도 - 이메일: {}", memberEmail);

        // DTO 생성
        MemberDTO loginDTO = new MemberDTO();
        loginDTO.setMemberEmail(memberEmail);
        loginDTO.setMemberPassword(memberPassword);

        // DAO 호출
        Optional<MemberVO> memberVO = memberDAO.findForLogin(loginDTO);

        if (memberVO.isPresent()) {
            // VO → DTO 변환
            MemberDTO member = MemberDTO.fromMemberVO(memberVO.get());
            log.info("▶ 로그인 성공 - 이름: {}", member.getMemberName());
            return member;
        } else {
            log.warn("▶ 로그인 실패 - 이메일: {}", memberEmail);
            return null;
        }
    }

    public void insert(MemberDTO memberDTO) {
        memberDAO.save(memberDTO);
    }

    public MemberDTO toDTO(MemberVO memberVO){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberVO.getId());
        memberDTO.setMemberEmail(memberVO.getMemberEmail());
        memberDTO.setMemberName(memberVO.getMemberName());
        // 수정: getCreatedDate() → getCreatedDatetime()
        memberDTO.setCreatedDate(memberVO.getCreatedDate ());
        // 수정: getUpdatedDate() → getUpdatedDatetime()
        memberDTO.setUpdatedDate (memberVO.getUpdatedDate ());
        return memberDTO;
    }
    public boolean existsByEmail(String memberEmail) {
        return memberDAO.existsByEmail(memberEmail);
    }

    public List<MemberDTO> findAll(String searchType, String keyword, int offset, int pageSize) {
        return memberDAO.findAll(searchType, keyword, offset, pageSize);
    }

    public int countAll(String searchType, String keyword) {
        return memberDAO.countAll(searchType, keyword);
    }

    public MemberDTO findById(Long id) {
        return memberDAO.findById(id);
    }

    public void update(MemberDTO memberDTO) {
        memberDAO.update(memberDTO);
    }

    public void deleteById(Long id) {
        memberDAO.deleteById(id);
    }
}