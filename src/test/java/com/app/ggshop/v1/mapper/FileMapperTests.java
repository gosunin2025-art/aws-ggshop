//package com.app.ggshop.v1.mapper;
//
//import com.app.ggshop.v1.dto.BoardDTO;
//import com.app.ggshop.v1.dto.FileDTO;
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
//public class FileMapperTests {
//
//    @Autowired
//    private FileMapper fileMapper;
//
//    @Autowired
//    private BoardMapper boardMapper;
//
//    @Test
//    void insertFile_Test() {
//        // given
//        FileDTO file1 = new FileDTO();
//        file1.setFileName("solar_panel_1.jpg");
//        file1.setFileSize("1024000");  // 1MB
//        file1.setFilePath("/uploads/board/solar_panel_1.jpg");
//
//        FileDTO file2 = new FileDTO();
//        file2.setFileName("solar_panel_2.jpg");
//        file2.setFileSize("2048000");  // 2MB
//        file2.setFilePath("/uploads/board/solar_panel_2.jpg");
//
//        FileDTO file3 = new FileDTO();
//        file3.setFileName("solar_panel_3.jpg");
//        file3.setFileSize("1536000");  // 1.5MB
//        file3.setFilePath("/uploads/board/solar_panel_3.jpg");
//
//        // when
//        fileMapper.insertFile(file1);
//        fileMapper.insertFile(file2);
//        fileMapper.insertFile(file3);
//
//        // then
//        log.info("▶ 파일 등록 완료");
//        log.info("파일1 ID: {}, 이름: {}, 크기: {}", file1.getId(), file1.getFileName(), file1.getFileSize());
//        log.info("파일2 ID: {}, 이름: {}, 크기: {}", file2.getId(), file2.getFileName(), file2.getFileSize());
//        log.info("파일3 ID: {}, 이름: {}, 크기: {}", file3.getId(), file3.getFileName(), file3.getFileSize());
//
//        assertNotNull(file1.getId());
//        assertNotNull(file2.getId());
//        assertNotNull(file3.getId());
//    }
//
//    @Test
//    void insertBoardFile_Test() {
//        // given - 위 테스트 실행 후 파일 ID 생성됨
//        Long boardId = 1L;
//        Long fileId1 = 1L;
//        Long fileId2 = 2L;
//        Long fileId3 = 3L;
//
//        // when
//        fileMapper.insertBoardFile(fileId1, boardId);
//        fileMapper.insertBoardFile(fileId2, boardId);
//        fileMapper.insertBoardFile(fileId3, boardId);
//
//        // then
//        log.info("▶ 게시글-파일 연결 완료");
//        log.info("게시글 ID: {}", boardId);
//        log.info("연결된 파일: {}, {}, {}", fileId1, fileId2, fileId3);
//    }
//
//    @Test
//    void selectFilesByBoardId_Test() {
//        // given
//        Long boardId = 1L;
//
//        // when
//        List<FileDTO> files = fileMapper.selectFilesByBoardId(boardId);
//
//        // then
//        log.info("▶ 게시글 ID={} 파일 목록:", boardId);
//        files.forEach(file -> {
//            log.info("- ID: {}, 파일명: {}, 크기: {}, 경로: {}",
//                    file.getId(),
//                    file.getFileName(),
//                    file.getFileSize(),
//                    file.getFilePath());
//        });
//
//        assertEquals(3, files.size());
//    }
//
//    @Test
//    void given3_Test() {
//        // given - 게시글 먼저 생성
//        BoardDTO boardDTO = new BoardDTO();
//        boardDTO.setTitle("[판매자] 파일 테스트");
//        boardDTO.setContent("파일 3개 등록 테스트");
//        boardDTO.setBoardMemberId(1L);
//        boardDTO.parseTitleAndSetFilter();
//
//        boardMapper.insertBoard(boardDTO);
//        Long boardId = boardDTO.getId();
//
//        log.info("▶ 테스트용 게시글 생성: ID={}", boardId);
//
//        // 3개 파일 등록
//        for (int i = 1; i <= 3; i++) {
//            FileDTO file = new FileDTO();
//            file.setFileName("image_" + i + ".jpg");
//            file.setFileSize(String.valueOf(1024000 * i));
//            file.setFilePath("/uploads/board/image_" + i + ".jpg");
//
//            fileMapper.insertFile(file);
//            fileMapper.insertBoardFile(file.getId(), boardId);
//        }
//
//        // when
//        List<FileDTO> files = fileMapper.selectFilesByBoardId(boardId);
//
//        // then
//        log.info("▶ 게시글 ID={} 파일 개수: {}", boardId, files.size());
//        files.forEach(f -> log.info("- {}", f.getFileName()));
//
//        assertEquals(3, files.size());
//    }
//}