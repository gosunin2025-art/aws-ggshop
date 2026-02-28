package com.app.ggshop.v1.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class MemberFileVO {
    private Long id;
    private Long memberId;

}
