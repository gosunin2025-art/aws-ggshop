package com.app.ggshop.v1.audit.dateTime;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public abstract class Period {
    private String createdDate;
    private String updatedDate;
}