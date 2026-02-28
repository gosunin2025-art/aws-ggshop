//package com.app.ggshop.v1.mapper;
//
//import com.app.ggshop.v1.domain.BoardTagVO;
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
//public class BoardTagMapperTests {
//
//    @Autowired
//    private BoardTagMapper boardTagMapper;
//
//    @Test
//    void insertTag_Test() {
//        // given - 게시글 ID=1에 태그 등록
//        BoardTagVO tag1 = BoardTagVO.builder()
//                .tagName("태양광")
//                .tagBoardId(1L)
//                .build();
//
//        BoardTagVO tag2 = BoardTagVO.builder()
//                .tagName("전력판매")
//                .tagBoardId(1L)
//                .build();
//
//        BoardTagVO tag3 = BoardTagVO.builder()
//                .tagName("용산구")
//                .tagBoardId(1L)
//                .build();
//
//        // when
//        boardTagMapper.insertTag(tag1);
//        boardTagMapper.insertTag(tag2);
//        boardTagMapper.insertTag(tag3);
//
//        // then
//        log.info("▶ 태그 등록 완료");
//        log.info("태그1 ID: {}, 이름: {}", tag1.getId(), tag1.getTagName());
//        log.info("태그2 ID: {}, 이름: {}", tag2.getId(), tag2.getTagName());
//        log.info("태그3 ID: {}, 이름: {}", tag3.getId(), tag3.getTagName());
//
//        assertNotNull(tag1.getId());
//        assertNotNull(tag2.getId());
//        assertNotNull(tag3.getId());
//    }
//
//    @Test
//    void selectTagsByBoardId_Test() {
//        // given - 위 테스트 실행 후
//        Long boardId = 1L;
//
//        // when
//        List<String> tags = boardTagMapper.selectTagsByBoardId(boardId);
//
//        // then
//        log.info("▶ 게시글 ID={} 태그 목록:", boardId);
//        tags.forEach(tag -> log.info("- {}", tag));
//
//        assertTrue(tags.size() > 0);
//    }
//
//    @Test
//    void insertMultipleTags_최대10개_Test() {
//        // given - 최대 10개 태그
//        Long boardId = 1L;
//        List<String> tagNames = List.of(
//                "태양광", "전력판매", "용산구", "신재생에너지",
//                "P2P거래", "전기", "친환경", "에너지", "절약", "스마트그리드"
//        );
//
//        // when
//        for (String tagName : tagNames) {
//            BoardTagVO tag = BoardTagVO.builder()
//                    .tagName(tagName)
//                    .tagBoardId(boardId)
//                    .build();
//            boardTagMapper.insertTag(tag);
//        }
//
//        // then
//        List<String> savedTags = boardTagMapper.selectTagsByBoardId(boardId);
//        log.info("▶ 저장된 태그 개수: {}", savedTags.size());
//        savedTags.forEach(tag -> log.info("- {}", tag));
//
//        assertEquals(10, savedTags.size());
//    }
//}