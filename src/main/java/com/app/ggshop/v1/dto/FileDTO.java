package com.app.ggshop.v1.dto;

import com.app.ggshop.v1.common.enumeration.Status;
import com.app.ggshop.v1.domain.FileVO;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class FileDTO {
    private Long id;
    private String fileName;
    private String fileSize;
    private String filePath;
    private Status fileStatus;

    public FileVO toVO() {
        return FileVO.builder()
                .id(id)
                .fileName(fileName)
                .fileSize(fileSize)
                .filePath(filePath)
                .fileStatus(fileStatus)
                .build();
    }
}