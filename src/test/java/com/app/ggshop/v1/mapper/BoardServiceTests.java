//package com.app.ggshop.v1.mapper;
//
//import com.app.ggshop.v1.dto.BoardDTO;
//import com.app.ggshop.v1.dto.FileDTO;
//import com.app.ggshop.v1.service.BoardService;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Slf4j
//public class BoardServiceTests {
//
//    @Autowired
//    private BoardService boardService;
//
//    @Test
//    void registerBoard_total_Test() {
//        // given - 게시글 정보
//        BoardDTO boardDTO = new BoardDTO();
//        boardDTO.setTitle("[판매자] 태양광 잉여 전력 판매합니다");
//        boardDTO.setContent("용산구에서 태양광 발전 잉여 전력을 판매합니다. 월 100kWh 가능합니다.");
//        boardDTO.setSummary("용산구 태양광 전력 판매");
//        boardDTO.setBoardMemberId(1L);
//
//        // given - 파일 3개
//        FileDTO file1 = new FileDTO();
//        file1.setFileName("solar_1.jpg");
//        file1.setFileSize("1024000");
//        file1.setFilePath("/uploads/board/solar_1.jpg");
//
//        FileDTO file2 = new FileDTO();
//        file2.setFileName("solar_2.jpg");
//        file2.setFileSize("2048000");
//        file2.setFilePath("/uploads/board/solar_2.jpg");
//
//        FileDTO file3 = new FileDTO();
//        file3.setFileName("solar_3.jpg");
//        file3.setFileSize("1536000");
//        file3.setFilePath("/uploads/board/solar_3.jpg");
//
//        List<FileDTO> files = List.of(file1, file2, file3);
//
//        // given - 태그 10개
//        List<String> tags = List.of(
//                "태양광", "전력판매", "용산구", "신재생에너지", "P2P거래",
//                "전기", "친환경", "에너지", "절약", "스마트그리드"
//        );
//
//        // when
//        boardService.registerBoard(boardDTO, files, tags);
//
//        // then
//        log.info("▶ 전체 통합 테스트 완료");
//        log.info("게시글 ID: {}", boardDTO.getId());
//        log.info("제목: {}", boardDTO.getTitle());
//        log.info("필터: {}", boardDTO.getBoardFilter());
//
//        assertNotNull(boardDTO.getId());
//        assertEquals("판매", boardDTO.getBoardFilter().getValue());
//    }
//
//    @Test
//    void registerBoard_구매자_Test() {
//        // given
//        BoardDTO boardDTO = new BoardDTO();
//        boardDTO.setTitle("[구매자] 전력 구매 희망합니다");
//        boardDTO.setContent("강남구에서 월 200kWh 전력 구매 희망합니다.");
//        boardDTO.setSummary("강남구 전력 구매");
//        boardDTO.setBoardMemberId(1L);
//
//        // 파일 2개
//        FileDTO file1 = new FileDTO();
//        file1.setFileName("buy_1.jpg");
//        file1.setFileSize("512000");
//        file1.setFilePath("/uploads/board/buy_1.jpg");
//
//        FileDTO file2 = new FileDTO();
//        file2.setFileName("buy_2.jpg");
//        file2.setFileSize("768000");
//        file2.setFilePath("/uploads/board/buy_2.jpg");
//
//        List<FileDTO> files = List.of(file1, file2);
//
//        // 태그 5개
//        List<String> tags = List.of("전력구매", "강남구", "P2P", "전기", "에너지");
//
//        // when
//        boardService.registerBoard(boardDTO, files, tags);
//
//        // then
//        log.info("▶ 구매자 게시글 등록 완료");
//        log.info("게시글 ID: {}", boardDTO.getId());
//        log.info("제목: {}", boardDTO.getTitle());
//        log.info("필터: {}", boardDTO.getBoardFilter());
//
//        assertNotNull(boardDTO.getId());
//        assertEquals("구매", boardDTO.getBoardFilter().getValue());
//    }
//}