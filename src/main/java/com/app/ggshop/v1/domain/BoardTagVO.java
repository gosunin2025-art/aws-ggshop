package com.app.ggshop.v1.domain;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class BoardTagVO {
    private Long id;
    private String tagName;
    private Long tagBoardId;
}