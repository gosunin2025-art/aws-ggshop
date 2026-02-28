//package com.app.ggshop.v1.controller.p2p;
//
//import com.app.ggshop.v1.dto.BoardDTO;
//import com.app.ggshop.v1.dto.FileDTO;
//import com.app.ggshop.v1.dto.MemberDTO;
//import com.app.ggshop.v1.service.BoardService;
//import jakarta.servlet.http.HttpSession;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.view.RedirectView;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//@RequestMapping("/ev/p2p")
//@RequiredArgsConstructor
//@Slf4j
//public class BoardController {
//
//    private final BoardService boardService;
//    private final HttpSession session;
//
//    /**
//     * 게시글 작성 페이지
//     */
//    @GetMapping("/write")
//    public String writePage(Model model) {
//        // 로그인 체크
//        MemberDTO member = (MemberDTO) session.getAttribute("member");
//        if (member == null) {
//            return "redirect:/login";
//        }
//
//        log.info("▶ 게시글 작성 페이지 - 회원: {}", member.getMemberEmail());
//
//        return "/p2p/p2p_write_gg";
//    }
//
//    /**
//     * 게시글 등록 처리
//     */
//    @PostMapping("/write")
//    public RedirectView write(
//            @RequestParam("title") String title,
//            @RequestParam("content") String content,
//            @RequestParam(value = "summary", required = false) String summary,
//            @RequestParam(value = "images", required = false) List<MultipartFile> images,
//            @RequestParam(value = "tags", required = false) String tagsStr,
//            @RequestParam(value = "address", required = false) String address) {  //  주소 받긴 하지만 무시
//
//        // 로그인 체크
//        MemberDTO member = (MemberDTO) session.getAttribute("member");
//        if (member == null) {
//            return new RedirectView("/login");
//        }
//
//        log.info("▶ 게시글 등록 요청");
//        log.info("제목: {}", title);
//        log.info("내용 길이: {} 자", content.length());
//        log.info("요약: {}", summary);
//        log.info("주소: {} (스킵)", address);  //  로그만 찍고 사용 안 함
//        log.info("회원 ID: {}", member.getId());
//
//        // 1. 게시글 DTO 생성
//        BoardDTO boardDTO = new BoardDTO();
//        boardDTO.setTitle(title);
//        boardDTO.setContent(content);
//        boardDTO.setSummary(summary);
//        boardDTO.setBoardMemberId(member.getId());
//
//        // 2. 파일 처리 (최대 3개)
//        List<FileDTO> files = new ArrayList<>();
//        if (images != null && !images.isEmpty()) {
//            int count = 0;
//            for (MultipartFile image : images) {
//                if (count >= 3) break;  // 최대 3개
//                if (image.isEmpty()) continue;
//
//                try {
//                    FileDTO fileDTO = saveFile(image);
//                    files.add(fileDTO);
//                    count++;
//
//                    log.info("▶ 파일 업로드 성공: {} ({}bytes)",
//                            fileDTO.getFileName(), fileDTO.getFileSize());
//                } catch (IOException e) {
//                    log.error("파일 저장 실패:", e);
//                }
//            }
//        }
//
//        log.info("▶ 업로드된 파일 개수: {}", files.size());
//
//        // 3. 태그 처리 (최대 10개)
//        List<String> tags = new ArrayList<>();
//        if (tagsStr != null && !tagsStr.isEmpty()) {
//            // 쉼표 또는 공백으로 분리
//            String[] tagArray = tagsStr.split("[,\\s]+");
//
//            int count = 0;
//            for (String tag : tagArray) {
//                if (count >= 10) break;  // 최대 10개
//                tag = tag.trim();
//                if (!tag.isEmpty()) {
//                    tags.add(tag);
//                    count++;
//                }
//            }
//
//            log.info("▶ 태그 개수: {}", tags.size());
//            log.info("▶ 태그 목록: {}", tags);
//        }
//
//        // 4. Service 호출
//        boardService.registerBoard(boardDTO, files, tags);
//
//        log.info("▶ 게시글 등록 완료: ID={}", boardDTO.getId());
//
//        return new RedirectView("/ev/p2p/list");
//    }
//
//
//
//    /**
//     * 파일 저장
//     */
//    private FileDTO saveFile(MultipartFile file) throws IOException {
//        //  Paths 사용 (OS 자동 처리)
//        Path uploadPath = Paths.get(System.getProperty("user.dir"), "uploads", "board");
//
//        // 디렉토리 생성
//        if (!Files.exists(uploadPath)) {
//            Files.createDirectories(uploadPath);
//            log.info("▶ 업로드 디렉토리 생성: {}", uploadPath);
//        }
//
//        // 파일명 생성
//        String originalFilename = file.getOriginalFilename();
//        String filename = System.currentTimeMillis() + "_" + originalFilename;
//        Path filepath = uploadPath.resolve(filename);
//
//        // 파일 저장
//        Files.copy(file.getInputStream(), filepath);
//
//        log.info("▶ 파일 저장 완료: {}", filepath);
//
//        // FileDTO 생성
//        FileDTO fileDTO = new FileDTO();
//        fileDTO.setFileName(filename);
//        fileDTO.setFileSize(String.valueOf(file.getSize()));
//        fileDTO.setFilePath(filepath.toString());
//
//        return fileDTO;
//    }
//
//}