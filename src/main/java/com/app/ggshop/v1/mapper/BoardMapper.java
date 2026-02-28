package com.app.ggshop.v1.mapper;


import com.app.ggshop.v1.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
    // 게시글 등록
    void insertBoard(BoardDTO boardDTO);
}