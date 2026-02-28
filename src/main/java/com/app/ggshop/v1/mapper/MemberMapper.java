package com.app.ggshop.v1.mapper;

import com.app.ggshop.v1.domain.MemberVO;
import com.app.ggshop.v1.domain.OAuthVO;
import com.app.ggshop.v1.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface MemberMapper {
    //    아이디 검사
    public Optional<MemberDTO> selectByMemberEmail(String memberEmail);
    //    회원가입
    public void insert(MemberDTO memberDTO);
    //    oauth
    public void insertOauth(OAuthVO oAuthVO);
    //    로그인
    public Optional<MemberVO> selectMemberForLogin(MemberDTO memberDTO);

    boolean existsByEmail(@Param("memberEmail") String memberEmail);

    List<MemberDTO> findAll(Map<String, Object> params);
    int countAll(Map<String, Object> params);

    MemberDTO findById(Long id);

    void update(MemberDTO memberDTO);
    void deleteById(Long id);

    void deleteOauthByMemberId(Long memberId);


}

