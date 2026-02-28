//package com.app.ggshop.v1.mapper;
//
//import com.app.ggshop.v1.common.pagination.Criteria;
//import com.app.ggshop.v1.dto.VtogPaymentDTO;
//import com.app.ggshop.v1.repository.VtogPaymentDao;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@Transactional
//class VtogPaymentDaoTest {
//
//    @Autowired
//    private VtogPaymentDao vtogPaymentDao;
//
//    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//    // ================================================================
//    // 일간 테스트
//    // ================================================================
//    @Test
//    @DisplayName("일간 - countAll 테스트")
//    void countAll_daily() {
//        String startDate = LocalDate.now().format(fmt) + " 00:00:00";
//        String endDate   = LocalDate.now().format(fmt) + " 23:59:59";
//
//        int count = vtogPaymentDao.countAll(startDate, endDate);
//
//        System.out.println("일간 count : " + count);
//        assertThat(count).isGreaterThanOrEqualTo(0);
//    }
//
//    @Test
//    @DisplayName("일간 - findAll 페이징 테스트")
//    void findAll_daily() {
//        String startDate = LocalDate.now().format(fmt) + " 00:00:00";
//        String endDate   = LocalDate.now().format(fmt) + " 23:59:59";
//
//        int total = vtogPaymentDao.countAll(startDate, endDate);
//        Criteria criteria = new Criteria(1, total);
//        List<VtogPaymentDTO> list = vtogPaymentDao.findAll(startDate, endDate, criteria);
//
//        System.out.println("일간 total : " + total);
//        System.out.println("일간 list size : " + list.size());
//        list.forEach(v -> System.out.println("   → " + v.getCreatedDate() + " | " + v.getVtogTransactionNumber()));
//
//        assertThat(list).isNotNull();
//    }
//
//    // ================================================================
//    // 주간 테스트
//    // ================================================================
//    @Test
//    @DisplayName("주간 - countAll 테스트")
//    void countAll_weekly() {
//        LocalDate today  = LocalDate.now();
//        LocalDate monday = today.minusDays(today.getDayOfWeek().getValue() - 1);
//        String startDate = monday.format(fmt) + " 00:00:00";
//        String endDate   = today.format(fmt)  + " 23:59:59";
//
//        int count = vtogPaymentDao.countAll(startDate, endDate);
//
//        System.out.println("주간 startDate : " + startDate);
//        System.out.println("주간 endDate   : " + endDate);
//        System.out.println("주간 count     : " + count);
//        assertThat(count).isGreaterThanOrEqualTo(0);
//    }
//
//    @Test
//    @DisplayName("주간 - findAll 페이징 테스트")
//    void findAll_weekly() {
//        LocalDate today  = LocalDate.now();
//        LocalDate monday = today.minusDays(today.getDayOfWeek().getValue() - 1);
//        String startDate = monday.format(fmt) + " 00:00:00";
//        String endDate   = today.format(fmt)  + " 23:59:59";
//
//        int total = vtogPaymentDao.countAll(startDate, endDate);
//        Criteria criteria = new Criteria(1, total);
//        List<VtogPaymentDTO> list = vtogPaymentDao.findAll(startDate, endDate, criteria);
//
//        System.out.println("주간 total : " + total);
//        System.out.println("주간 list size : " + list.size());
//        list.forEach(v -> System.out.println("   → " + v.getCreatedDate() + " | " + v.getVtogTransactionNumber()));
//
//        assertThat(list).isNotNull();
//    }
//
//    // ================================================================
//    // 월간 테스트
//    // ================================================================
//    @Test
//    @DisplayName("월간 - countAll 테스트")
//    void countAll_monthly() {
//        LocalDate today    = LocalDate.now();
//        LocalDate firstDay = today.withDayOfMonth(1);
//        String startDate   = firstDay.format(fmt) + " 00:00:00";
//        String endDate     = today.format(fmt)    + " 23:59:59";
//
//        int count = vtogPaymentDao.countAll(startDate, endDate);
//
//        System.out.println("월간 startDate : " + startDate);
//        System.out.println("월간 endDate   : " + endDate);
//        System.out.println("월간 count     : " + count);
//        assertThat(count).isGreaterThanOrEqualTo(0);
//    }
//
//    @Test
//    @DisplayName("월간 - findAll 페이징 테스트")
//    void findAll_monthly() {
//        LocalDate today    = LocalDate.now();
//        LocalDate firstDay = today.withDayOfMonth(1);
//        String startDate   = firstDay.format(fmt) + " 00:00:00";
//        String endDate     = today.format(fmt)    + " 23:59:59";
//
//        int total = vtogPaymentDao.countAll(startDate, endDate);
//        Criteria criteria = new Criteria(1, total);
//        List<VtogPaymentDTO> list = vtogPaymentDao.findAll(startDate, endDate, criteria);
//
//        System.out.println("월간 total : " + total);
//        System.out.println("월간 list size : " + list.size());
//        list.forEach(v -> System.out.println("   → " + v.getCreatedDate() + " | " + v.getVtogTransactionNumber()));
//
//        assertThat(list).isNotNull();
//    }
//
//}