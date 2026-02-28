package com.app.ggshop.v1.mapper;

import com.app.ggshop.v1.dto.FileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FileMapper {
    // 파일 등록
    void insertFile(FileDTO fileDTO);

    // 게시글-파일 연결
    void insertBoardFile(@Param("fileId") Long fileId, @Param("boardId") Long boardId);

    // 게시글의 파일 목록 조회
    List<FileDTO> selectFilesByBoardId(Long boardId);
}