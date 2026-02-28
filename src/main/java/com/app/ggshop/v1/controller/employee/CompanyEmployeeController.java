package com.app.ggshop.v1.controller.employee;

import com.app.ggshop.v1.dto.CompanyEmployeeDTO;
import com.app.ggshop.v1.dto.MemberDTO;
import com.app.ggshop.v1.dto.PostWithPagingDTO;
import com.app.ggshop.v1.service.CompanyEmployeeService;
import com.app.ggshop.v1.service.member.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/ev/company")
@RequiredArgsConstructor
@Slf4j
public class CompanyEmployeeController {

    private final CompanyEmployeeService companyEmployeeService;
    private final MemberService memberService;
    private final HttpSession session;

    @PostMapping("/signup")
    public String signup(MemberDTO memberDTO) {
        log.info("====================================");
        log.info("회원가입 email : {}", memberDTO.getMemberEmail());
        log.info("회원가입 name : {}", memberDTO.getMemberName());
        log.info("회원가입 address : {}", memberDTO.getMemberAddress());
        log.info("====================================");

        memberService.insert(memberDTO);
        return "redirect:/ev/main";
    }

    // 임직원 등록 페이지
    @GetMapping("/write")
    public String employeeWritePage() {
        return "/company-admin/company-employee-create";
    }

//    // 임직원 등록 처리
    @PostMapping("/write")
    public RedirectView registerEmployee(CompanyEmployeeDTO employeeDTO) {
        log.info("▶ 임직원 등록 요청: {}", employeeDTO);

        // 세션에서 로그인한 회사 ID 가져오기 (추후 구현)
        // MemberDTO loginMember = (MemberDTO) session.getAttribute("member");
        // employeeDTO.setEmployeeCompanyId(loginMember.getCompanyId());

        // 임시로 하드코딩 (테스트용)
        employeeDTO.setEmployeeCompanyId(1L);

        companyEmployeeService.registerEmployee(employeeDTO);

        log.info("▶ 임직원 등록 완료: ID={}", employeeDTO.getId());

        return new RedirectView("/ev/company/emp/list");
    }

    // 임직원 목록 (페이징)
//    @GetMapping("/emp/list/{page}")
//    public String employeeList(@PathVariable int page, Model model) {
//        PostWithPagingDTO result = companyEmployeeService.list(page);
//        model.addAttribute("employeeList", result.getEmployeeList());
//        model.addAttribute("pagination", result.getCriteria());
//
//        log.info("▶ 임직원 목록 - 페이지: {}, 개수: {}", page, result.getEmployeeList().size());
//
//        return "/company-admin/company-employee-list";
//    }
//
//    // 임직원 목록 (리다이렉트)
//    @GetMapping("/emp/list")
//    public String redirectToFirstPage() {
//        return "redirect:/ev/company/emp/list/1";
//    }


    // 임직원 목록 (페이징 + 검색)
    @GetMapping("/emp/list/{page}")
    public String employeeList(
            @PathVariable int page,
            @RequestParam(required = false) String searchType,
            @RequestParam(required = false) String keyword,
            Model model) {

        PostWithPagingDTO result = companyEmployeeService.list(page, searchType, keyword);

        model.addAttribute("employeeList", result.getEmployeeList());
        model.addAttribute("pagination", result.getCriteria());
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);

        log.info("▶ 임직원 목록 - 페이지: {}, 개수: {}", page, result.getEmployeeList().size());

        return "/company-admin/company-employee-list";
    }

    // 임직원 목록 (리다이렉트)
    @GetMapping("/emp/list")
    public String redirectToFirstPage() {
        return "redirect:/ev/company/emp/list/1";
    }

    // 임직원 상세 페이지
    @GetMapping("/emp/detail/{id}")
    public String employeeDetail(@PathVariable Long id, Model model) {
        CompanyEmployeeDTO employee = companyEmployeeService.getDetail(id);  //  수정!
        model.addAttribute("employee", employee);

        log.info("▶ 임직원 상세 - ID: {}, 이름: {}", id, employee.getEmployeeName());

        return "/company-admin/company-employee-detail";
    }

    // 임직원 삭제
    @PostMapping("/emp/delete/{id}")
    public RedirectView deleteEmployee(@PathVariable Long id) {
        log.info("▶ 임직원 삭제 요청 - ID: {}", id);
        companyEmployeeService.remove(id);  //  수정!
        log.info("▶ 임직원 삭제 완료 - ID: {}", id);

        return new RedirectView("/ev/company/emp/list/1");
    }

    // 임직원 수정 페이지
    @GetMapping("/emp/update/{id}")
    public String employeeUpdatePage(@PathVariable Long id, Model model) {
        CompanyEmployeeDTO employee = companyEmployeeService.getDetail(id);  //  수정!
        model.addAttribute("employee", employee);

        log.info("▶ 임직원 수정 페이지 - ID: {}, 이름: {}", id, employee.getEmployeeName());

        return "/company-admin/company-employee-update";
    }

    // 임직원 수정 처리
    @PostMapping("/emp/update/{id}")
    public RedirectView updateEmployee(@PathVariable Long id, CompanyEmployeeDTO employeeDTO) {
        employeeDTO.setId(id);

        log.info("▶ 임직원 수정 요청 - ID: {}, 이름: {}", id, employeeDTO.getEmployeeName());

        companyEmployeeService.modify(employeeDTO);  //  수정!

        log.info("▶ 임직원 수정 완료 - ID: {}", id);

        return new RedirectView("/ev/company/emp/detail/" + id);
    }
}