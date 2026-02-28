package com.app.ggshop.v1.controller.Member;


import com.app.ggshop.v1.common.enumeration.Status;
import com.app.ggshop.v1.dto.MemberDTO;
import com.app.ggshop.v1.service.member.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    //    session: 서버에 저장(공용)
    private final HttpSession session;

    @GetMapping("check-email")
    @ResponseBody
    public boolean checkEmail(String memberEmail){
        return memberService.checkEmail(memberEmail);
    }

    @GetMapping("join")
    public String goToJoinForm(){
        return "member/join";
    }

    @GetMapping("kakao/join")
    public String goToKakaoJoinForm(){
        return "company-admin/kakao/join";
    }

    @PostMapping("join")
    public RedirectView join(MemberDTO memberDTO){
        memberService.join(memberDTO);
        return new RedirectView("/member/login");
    }

    @PostMapping("kakao/join")
    public RedirectView kakaoJoin(MemberDTO memberDTO){
        memberService.kakaoJoin(memberDTO);
        return new RedirectView("/member/login");
    }

    // 로그인 페이지 이동
    @GetMapping("login")
    public String goToLoginForm(@CookieValue(name="remember", required = false) boolean remember,
                                @CookieValue(name="remember-member-email", required = false) String rememberMemberEmail,
                                HttpServletRequest request,
                                Model model){
        model.addAttribute("remember", remember);
        model.addAttribute("rememberMemberEmail", rememberMemberEmail);
//        return "company-admin/login";
        return "/login/login_mobile_check";
    }

    // 로그인 버튼을 누르면 포스트 로그인으로
    @PostMapping("login")
    public RedirectView login(MemberDTO memberDTO, Model model, HttpServletResponse response){
        session.setAttribute("member", memberService.login(memberDTO));

        Cookie rememberMemberEmailCookie = new Cookie("remember-member-email", memberDTO.getMemberEmail());
        Cookie rememberCookie = new Cookie("remember", String.valueOf(memberDTO.isRemember()));

        rememberMemberEmailCookie.setPath("/");
        rememberCookie.setPath("/");

        if(memberDTO.isRemember()){
//            rememberMemberEmailCookie.setMaxAge(60 * 60 * 24 * 30); // 30일 유지
//            rememberCookie.setMaxAge(60 * 60 * 24 * 30); // 30일 유지

            rememberMemberEmailCookie.setMaxAge(60 * 60 ); // 30일 유지
            rememberCookie.setMaxAge(60 * 60 ); // 30일 유지

        }else{
            rememberMemberEmailCookie.setMaxAge(0); // 30일 유지
            rememberCookie.setMaxAge(0); // 30일 유지
        }

        response.addCookie(rememberMemberEmailCookie);
        response.addCookie(rememberCookie);

        return new RedirectView("/ev/main");
    }

//    세션을 통째로 날리면 로그 아웃됨
//    @GetMapping("/logout")
//    public String logout(HttpSession session) {
//        session.invalidate();
////        return "redirect:/member/login";
//        return "redirect:/ev/company/login";
//    }


    @GetMapping("/logout")
    public RedirectView logout(HttpSession session, HttpServletRequest request) {

        log.info("============================================");
        log.info("▶ 로그아웃 요청");
        log.info("▶ Referer: {}", request.getHeader("Referer"));
        log.info("▶ Request URL: {}", request.getRequestURL());
        log.info("▶ Query String: {}", request.getQueryString());

        // 세션 정보 확인
//        MemberDTO member = (MemberDTO) session.getAttribute("member");
//        if (member != null) {
//            log.info("▶ 로그아웃 사용자: {}", member.getMemberName());
//            log.info("▶ 로그인 타입: {}", member.getProvider() != null ? member.getProvider().getValue() : "일반");
//        }

        // 세션 무효화
        session.invalidate();

        log.info("▶ 세션 무효화 완료");
        log.info("▶ 로그아웃 성공");
        log.info("============================================");

        return new RedirectView("/ev/company/login");
    }

//    @GetMapping("/list/{page}")
//    public String memberList(@PathVariable int page, Model model) {
//
//        // 백엔드 완성 전 임시 처리
//        Map<String, Object> pagination = new HashMap<>();
//        pagination.put("page", page);
//        pagination.put("startPage", 1);
//        pagination.put("endPage", 1);
//        pagination.put("realEnd", 1);
//
//        model.addAttribute("memberList", List.of()); // 빈 목록
//        model.addAttribute("pagination", pagination);
//        model.addAttribute("searchType", "all");
//        model.addAttribute("keyword", "");
//
//        return "company-admin/customer-list";
//    }

    @GetMapping("/list/{page}")
    public String memberList(@PathVariable int page,
                             @RequestParam(defaultValue = "all") String searchType,
                             @RequestParam(defaultValue = "") String keyword,
                             Model model) {

        int pageSize = 10;
        int offset = (page - 1) * pageSize;

        List<MemberDTO> memberList = memberService.findAll(searchType, keyword, offset, pageSize);
        int totalCount = memberService.countAll(searchType, keyword);

        int totalPages = (int) Math.ceil((double) totalCount / pageSize);
        if (totalPages == 0) totalPages = 1;

        int startPage = Math.max(1, page - 2);
        int endPage = Math.min(totalPages, page + 2);

        Map<String, Object> pagination = new HashMap<>();
        pagination.put("page", page);
        pagination.put("startPage", startPage);
        pagination.put("endPage", endPage);
        pagination.put("realEnd", totalPages);

        log.info("====================================");
        log.info("회원 수 : {}", totalCount);
        log.info("현재 페이지 : {}", page);
        log.info("====================================");

        model.addAttribute("memberList", memberList);
        model.addAttribute("pagination", pagination);
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);

        return "company-admin/customer-list";
    }

    @GetMapping("/detail/{id}")
    public String memberDetail(@PathVariable Long id, Model model) {

        MemberDTO member = memberService.findById(id);

        log.info("====================================");
        log.info("회원 id : {}", member.getId());
        log.info("회원 이메일 : {}", member.getMemberEmail());
        log.info("회원 이름 : {}", member.getMemberName());
        log.info("====================================");

        model.addAttribute("member", member);

        // 나머지는 빈 리스트로 임시 처리
        model.addAttribute("carList", List.of());
        model.addAttribute("vtogList", List.of());
        model.addAttribute("boardSellList", List.of());
        model.addAttribute("boardPaymentList", List.of());
        model.addAttribute("boardList", List.of());
        model.addAttribute("followerList", List.of());
        model.addAttribute("followingList", List.of());

        return "company-admin/customer-detail";

    }

    // 수정 페이지 이동
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        MemberDTO member = memberService.findById(id);
        model.addAttribute("member", member);
        return "company-admin/customer-update";
    }

    // 수정 처리
    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String memberName,
                         @RequestParam String memberNickname,
                         @RequestParam(required = false) String memberBirth,
                         @RequestParam(required = false) String memberAddress,
                         @RequestParam String memberStatus) {

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(id);
        memberDTO.setMemberName(memberName);
        memberDTO.setMemberNickname(memberNickname);
        memberDTO.setMemberBirth(memberBirth);
        memberDTO.setMemberAddress(memberAddress);
        memberDTO.setMemberStatus(Status.getStatus(memberStatus)); // "active" → Status.ACTIVE

        memberService.update(memberDTO);

        return "redirect:/member/detail/" + id;
    }

    // 삭제 처리
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        memberService.deleteById(id);
        return "redirect:/member/list/1";
    }



}

