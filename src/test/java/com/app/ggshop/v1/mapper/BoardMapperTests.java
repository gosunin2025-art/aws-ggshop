//package com.app.ggshop.v1.mapper;
//
//import com.app.ggshop.v1.common.enumeration.BoardFilter;
//import com.app.ggshop.v1.dto.BoardDTO;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Slf4j
//public class BoardMapperTests {
//
//    @Autowired
//    private BoardMapper boardMapper;
//
//    @Test
//    void insertBoard_Test() {
//        // given
//        BoardDTO boardDTO = new BoardDTO();
//        boardDTO.setTitle("[구매자] 전력 구매 희망합니다");
//        boardDTO.setContent("강남구에서 월 200kWh 전력 구매 희망합니다.");
//        boardDTO.setSummary("강남구 전력 구매");
//        boardDTO.setBoardMemberId(1L);
//
//        // 제목 파싱
//        boardDTO.parseTitleAndSetFilter();
//
//        // when
//        boardMapper.insertBoard(boardDTO);
//
//        // then
//        log.info("▶ 구매자 게시글 등록 완료");
//        log.info("ID: {}", boardDTO.getId());
//        log.info("제목: {}", boardDTO.getTitle());
//        log.info("파싱된 필터: {}", boardDTO.getBoardFilter());
//        log.info("내용: {}", boardDTO.getContent());
//        log.info("요약: {}", boardDTO.getSummary());
//
//        assertNotNull(boardDTO.getId());
//        assertEquals(BoardFilter.BUY, boardDTO.getBoardFilter());
//        assertTrue(boardDTO.getTitle().startsWith("[구매자]"));
//    }
//
//    @Test
//    void insertBoard_a_Test() {
//        // given
//        BoardDTO boardDTO = new BoardDTO();
//        boardDTO.setTitle("[판매자] 잉여 전력 판매 가능합니다");
//        boardDTO.setContent("용산구에서 태양광 발전 잉여 전력 판매합니다.");
//        boardDTO.setSummary("용산구 태양광 전력 판매");
//        boardDTO.setBoardMemberId(1L);
//
//        // 제목 파싱
//        boardDTO.parseTitleAndSetFilter();
//
//        // when
//        boardMapper.insertBoard(boardDTO);
//
//        // then
//        log.info("▶ 판매자 게시글 등록 완료");
//        log.info("ID: {}", boardDTO.getId());
//        log.info("제목: {}", boardDTO.getTitle());
//        log.info("파싱된 필터: {}", boardDTO.getBoardFilter());
//
//        assertNotNull(boardDTO.getId());
//        assertEquals(BoardFilter.SELL, boardDTO.getBoardFilter());
//        assertTrue(boardDTO.getTitle().startsWith("[판매자]"));
//    }
//
//}