package com.app.ggshop.v1.domain;

import com.app.ggshop.v1.common.enumeration.Status;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FileVO {
    private Long id;
    private String fileName;
    private String fileSize;
    private String filePath;
    private Status fileStatus;
}