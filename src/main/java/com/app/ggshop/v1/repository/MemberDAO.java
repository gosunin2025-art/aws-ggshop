package com.app.ggshop.v1.repository;

import com.app.ggshop.v1.domain.MemberVO;
import com.app.ggshop.v1.domain.OAuthVO;
import com.app.ggshop.v1.dto.MemberDTO;
import com.app.ggshop.v1.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class MemberDAO {
    private final MemberMapper memberMapper;
    //    이메일 검사
    public Optional<MemberDTO> findByMemberEmail(String memberEmail){
        return memberMapper.selectByMemberEmail(memberEmail);
    }

    //    회원가입
    public void save(MemberDTO memberDTO){
        memberMapper.insert(memberDTO);
    }
    //    oauth
    public void saveOAuth(OAuthVO oAuthVO){
        memberMapper.insertOauth(oAuthVO);
    }
    //    로그인
    public Optional<MemberVO> findForLogin(MemberDTO memberDTO){
        return memberMapper.selectMemberForLogin(memberDTO);
    }

    /* 이메일 존재 여부 확인 */
    public boolean existsByEmail(String memberEmail) {
        return memberMapper.existsByEmail(memberEmail);
    }

    public List<MemberDTO> findAll(String searchType, String keyword, int offset, int pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("searchType", searchType);
        params.put("keyword", keyword);
        params.put("offset", offset);
        params.put("pageSize", pageSize);
        return memberMapper.findAll(params);
    }

    public int countAll(String searchType, String keyword) {
        Map<String, Object> params = new HashMap<>();
        params.put("searchType", searchType);
        params.put("keyword", keyword);
        return memberMapper.countAll(params);
    }

    public MemberDTO findById(Long id) {
        return memberMapper.findById(id);
    }

    public void update(MemberDTO memberDTO) {
        memberMapper.update(memberDTO);
    }

    public void deleteById(Long id) {
        memberMapper.deleteOauthByMemberId(id);
//        memberMapper.deleteFollowByMemberId(id);
//        memberMapper.deleteBoardPaymentByMemberId(id);
//        memberMapper.deleteBoardByMemberId(id);
//        memberMapper.deleteVtogByMemberId(id);
//        memberMapper.deleteCarByMemberId(id);
        memberMapper.deleteById(id);
    }

}
