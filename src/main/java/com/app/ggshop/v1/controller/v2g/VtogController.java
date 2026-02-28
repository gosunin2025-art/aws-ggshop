package com.app.ggshop.v1.controller.v2g;

import com.app.ggshop.v1.common.pagination.Criteria;
import com.app.ggshop.v1.dto.EvChargerDTO;
import com.app.ggshop.v1.dto.MemberDTO;
import com.app.ggshop.v1.dto.VtogInsertDTO;
import com.app.ggshop.v1.dto.VtogPaymentDTO;
import com.app.ggshop.v1.service.VtogPaymentService;
import com.app.ggshop.v1.service.VtogService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/vtog/transaction")
@RequiredArgsConstructor
@Slf4j
public class VtogController {

    private final VtogPaymentService vtogPaymentService;
    private final VtogService vtogService;

    /**
     * 기본 목록 → 전체 1페이지로 리다이렉트
     */
    @GetMapping("/list")
    public String transactionListDefault() {
        return "redirect:/vtog/transaction/list/all/1";
    }

    /**
     * V2G 거래 목록 (탭 + 페이징)
     * tabType : all / daily / weekly / monthly / search
     * searchDate : 달력 선택 날짜 (search 탭일 때만 사용, 예: 2026-02-24)
     */
    @GetMapping("/list/{tabType}/{page}")
    public String transactionList(@PathVariable String tabType,
                                  @PathVariable int page,
                                  @RequestParam(required = false, defaultValue = "") String searchDate,
                                  Model model) {

        Criteria criteria = vtogPaymentService.getCriteria(tabType, searchDate, page);
        List<VtogPaymentDTO> vtogList = vtogPaymentService.findAll(tabType, searchDate, page, criteria);

        log.info("====================================");
        log.info("탭 타입 : {}", tabType);
        log.info("검색 날짜 : {}", searchDate);
        log.info("현재 페이지 : {}", page);
        log.info("====================================");

        model.addAttribute("vtogList", vtogList);
        model.addAttribute("pagination", criteria);
        model.addAttribute("tabType", tabType);
        model.addAttribute("searchDate", searchDate);

        return "company-admin/company-list";
    }

    /**
     * V2G 거래 등록 페이지
     */
    @GetMapping("/create")
    public String createPage() {
        return "/company-admin/vtog-transaction-create";
    }

    /**
     * 이메일로 회원 조회 (AJAX)
     */
    @GetMapping("/member")
    @ResponseBody
    public ResponseEntity<MemberDTO> getMemberByEmail(@RequestParam String email) {
        MemberDTO member = vtogService.findMemberByEmail(email);
        if (member == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(member);
    }

    /**
     * 충전기 UID로 충전기 조회 (AJAX)
     */
    @GetMapping("/charger")
    @ResponseBody
    public ResponseEntity<EvChargerDTO> getChargerByUid(@RequestParam String evChargerUid) {
        log.info("충전기 조회 요청 - UID: {}", evChargerUid);
        EvChargerDTO charger = vtogService.findEvChargerByUid(evChargerUid);
        if (charger == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(charger);
    }

    /**
     * V2G 거래 등록 처리 (POST)
     */
    @PostMapping("/insert")
    public String insert(@ModelAttribute VtogInsertDTO dto) {
        log.info("==== V2G 거래 등록 요청 ====");
        log.info("dto : {}", dto);
        vtogService.insertVtogTransaction(dto);
        return "redirect:/vtog/transaction/list/all/1";
    }

    /**
     * ZZ1 에너지 기여하기 (AJAX POST) - 메인 화면
     * - 이메일: minpre@empas.com 고정
     * - 충전기: EV-SEOUL-001 고정
     * - 판매 전기량, 판매 금액: 요청 body에서 받음
     */
//    @PostMapping("/zzone/insert")
//    @ResponseBody
//    public ResponseEntity<Map<String, String>> zzoneInsert(@RequestBody Map<String, Long> body) {
//        log.info("==== ZZ1 에너지 기여하기 요청 ====");
//
//        Long salesKwh   = body.get("salesKwh");
//        Long salesPrice = body.get("salesPrice");
//
//        log.info("salesKwh : {}, salesPrice : {}", salesKwh, salesPrice);
//
//        // ① 회원 조회
//        MemberDTO member = vtogService.findMemberByEmail("minpre@empas.com");
//        if (member == null) {
//            return ResponseEntity.badRequest().body(Map.of("result", "fail", "message", "회원을 찾을 수 없습니다."));
//        }
//
//        // DTO 세팅
//        VtogInsertDTO dto = new VtogInsertDTO();
//        dto.setMemberId(member.getId());
//        dto.setMemberAddress(member.getMemberAddress());
//        dto.setCarPlateNumber("ZZ1-0000");       // ZZ1 거래용 임시 번호판
//        dto.setCarEnergyGauge(String.valueOf(salesKwh));
//        dto.setEvChargerUid("EV-SEOUL-001");
//        dto.setVtogPaymentSalesKwh(salesKwh);
//        dto.setVtogPaymentSalesPrice(salesPrice);
//
//        vtogService.insertVtogTransaction(dto);
//
//        log.info("==== ZZ1 에너지 기여하기 완료 ====");
//        return ResponseEntity.ok(Map.of("result", "success", "message", "에너지 기여가 완료되었습니다."));
//    }

    @PostMapping("/zzone/insert")
    @ResponseBody
    public ResponseEntity<Map<String, String>> zzoneInsert(
            @RequestBody Map<String, Long> body,
            HttpSession session) {

        log.info("==== ZZ1 에너지 기여하기 요청 ====");

        // 세션에서 로그인 회원 정보 가져오기
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        if (member == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("result", "fail", "message", "로그인이 필요합니다."));
        }

        Long salesKwh   = body.get("salesKwh");
        Long salesPrice = body.get("salesPrice");

        log.info("salesKwh : {}, salesPrice : {}", salesKwh, salesPrice);
        log.info("로그인 회원 : {}", member.getMemberEmail());

        // DTO 세팅
        VtogInsertDTO dto = new VtogInsertDTO();
        dto.setMemberId(member.getId());
        dto.setMemberAddress(member.getMemberAddress());
        dto.setCarPlateNumber("ZZ1-0000");
        dto.setCarEnergyGauge(String.valueOf(salesKwh));
        dto.setEvChargerUid("EV-SEOUL-001");
        dto.setVtogPaymentSalesKwh(salesKwh);
        dto.setVtogPaymentSalesPrice(salesPrice);

        vtogService.insertVtogTransaction(dto);

        log.info("==== ZZ1 에너지 기여하기 완료 ====");
        return ResponseEntity.ok(Map.of("result", "success", "message", "에너지 기여가 완료되었습니다."));
    }


}