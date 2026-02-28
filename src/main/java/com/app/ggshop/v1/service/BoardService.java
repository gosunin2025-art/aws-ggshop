package com.app.ggshop.v1.service;

import com.app.ggshop.v1.domain.BoardTagVO;
import com.app.ggshop.v1.dto.BoardDTO;
import com.app.ggshop.v1.dto.FileDTO;
import com.app.ggshop.v1.mapper.BoardMapper;
import com.app.ggshop.v1.mapper.BoardTagMapper;
import com.app.ggshop.v1.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class BoardService {

    private final BoardMapper boardMapper;
    private final BoardTagMapper boardTagMapper;
    private final FileMapper fileMapper;

    /**
     * 게시글 등록 (파일 + 태그 포함)
     */
    public void registerBoard(BoardDTO boardDTO, List<FileDTO> files, List<String> tags) {

        // 1. 제목 파싱 ([구매자] 또는 [판매자])
        boardDTO.parseTitleAndSetFilter();

        // 2. 게시글 등록
        boardMapper.insertBoard(boardDTO);
        Long boardId = boardDTO.getId();

        log.info("▶ 게시글 등록 완료: ID={}, 제목={}, 필터={}",
                boardId, boardDTO.getTitle(), boardDTO.getBoardFilter());

        // 3. 파일 등록 (최대 3개)
        if (files != null && !files.isEmpty()) {
            int fileCount = Math.min(files.size(), 3);  // 최대 3개

            for (int i = 0; i < fileCount; i++) {
                FileDTO file = files.get(i);

                // 파일 등록
                fileMapper.insertFile(file);

                // 게시글-파일 연결
                fileMapper.insertBoardFile(file.getId(), boardId);

                log.info("▶ 파일 저장: ID={}, 파일명={}", file.getId(), file.getFileName());
            }
        }

        // 4. 태그 등록 (최대 10개)
        if (tags != null && !tags.isEmpty()) {
            int tagCount = Math.min(tags.size(), 10);  // 최대 10개

            for (int i = 0; i < tagCount; i++) {
                String tagName = tags.get(i);

                BoardTagVO tagVO = BoardTagVO.builder()
                        .tagName(tagName)
                        .tagBoardId(boardId)
                        .build();

                boardTagMapper.insertTag(tagVO);

                log.info("▶ 태그 저장: ID={}, 태그명={}", tagVO.getId(), tagName);
            }
        }

        log.info("▶ 게시글 등록 완료 (파일 {}개, 태그 {}개)",
                files != null ? Math.min(files.size(), 3) : 0,
                tags != null ? Math.min(tags.size(), 10) : 0);
    }
}