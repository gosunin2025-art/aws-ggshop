package com.app.ggshop.v1.domain;

import com.app.ggshop.v1.audit.dateTime.Period;
import com.app.ggshop.v1.common.enumeration.Status;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter @ToString(callSuper = true)
@EqualsAndHashCode(of="id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class MemberVO extends Period {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private Status memberStatus;

    private String memberNickname;
    private String memberBirth;
    private String memberAddress;
}


