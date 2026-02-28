package com.app.ggshop.v1.dto;

import com.app.ggshop.v1.common.enumeration.Provider;
import com.app.ggshop.v1.domain.OAuthVO;
import lombok.*;


@Getter @Setter @ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class OAuthDTO {
    private Long id;
    private Provider provider;

    public OAuthVO toVO() {
        return OAuthVO.builder().id(id).provider(provider).build();
    }
}
