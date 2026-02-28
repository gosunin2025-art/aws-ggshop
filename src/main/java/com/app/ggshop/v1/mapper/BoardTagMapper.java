package com.app.ggshop.v1.mapper;

import com.app.ggshop.v1.domain.BoardTagVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardTagMapper {
    // 태그 등록
    void insertTag(BoardTagVO boardTagVO);

    // 게시글의 태그 목록 조회
    List<String> selectTagsByBoardId(Long boardId);
}