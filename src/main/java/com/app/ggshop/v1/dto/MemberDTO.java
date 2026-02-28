package com.app.ggshop.v1.dto;


import com.app.ggshop.v1.common.enumeration.Provider;
import com.app.ggshop.v1.common.enumeration.Status;
import com.app.ggshop.v1.domain.MemberVO;
import com.app.ggshop.v1.domain.OAuthVO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter @Setter @ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class MemberDTO {

    private Long id;
    private String memberEmail;
    @JsonIgnore
    private String memberPassword;
    private String memberName;
    private Status memberStatus;

    private String memberNickname;
    private String memberBirth;
    private String memberAddress;

    private String createdDate;
    private String updatedDate;
    private Provider provider;
    private boolean remember;
    private String profileImageUrl;

    /**
     * VO → DTO 변환
     */
    public static MemberDTO fromMemberVO(MemberVO vo) {
        MemberDTO dto = new MemberDTO();
        dto.setId(vo.getId());
        dto.setMemberEmail(vo.getMemberEmail());
        dto.setMemberPassword(vo.getMemberPassword());
        dto.setMemberName(vo.getMemberName());
        dto.setMemberStatus(vo.getMemberStatus());

        dto.setMemberNickname(vo.getMemberNickname());
        dto.setMemberBirth(vo.getMemberBirth());
        dto.setMemberAddress(vo.getMemberAddress());

        dto.setCreatedDate(vo.getCreatedDate());
        dto.setUpdatedDate(vo.getUpdatedDate());
        //  provider는 제외 (MemberVO에 없음)
        // dto.setProvider(vo.getProvider());
        return dto;
    }


    public MemberVO toMemberVO() {
        return MemberVO.builder()
                .id(id)
                .memberEmail(memberEmail)
                .memberPassword(memberPassword)
                .memberName(memberName)
                .memberStatus(memberStatus)
                .memberNickname(memberNickname)
                .memberBirth(memberBirth)
                .memberAddress(memberAddress)
                .createdDate (createdDate)
                .updatedDate(updatedDate)
                .build();
    }

    public OAuthVO toOAuthVO() {
        return OAuthVO.builder().id(id).provider(provider).build();
    }
}




