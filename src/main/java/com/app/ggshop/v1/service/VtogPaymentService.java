package com.app.ggshop.v1.service;

import com.app.ggshop.v1.common.pagination.Criteria;
import com.app.ggshop.v1.dto.VtogPaymentDTO;
import com.app.ggshop.v1.repository.VtogPaymentDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VtogPaymentService {

    private final VtogPaymentDao vtogPaymentDao;

    public List<VtogPaymentDTO> findAll(String tabType, String searchDate, int page, Criteria criteria) {
        String[] dates = getDateRange(tabType, searchDate);
        return vtogPaymentDao.findAll(dates[0], dates[1], criteria);
    }

    public Criteria getCriteria(String tabType, String searchDate, int page) {
        String[] dates = getDateRange(tabType, searchDate);
        int total = vtogPaymentDao.countAll(dates[0], dates[1]);
        return new Criteria(page, total);
    }

    private String[] getDateRange(String tabType, String searchDate) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String startDate;
        String endDate;

        switch (tabType) {
            case "daily":
                startDate = today.format(fmt) + " 00:00:00";
                endDate   = today.format(fmt) + " 23:59:59";
                break;
            case "weekly":
                LocalDate monday = today.minusDays(today.getDayOfWeek().getValue() - 1);
                startDate = monday.format(fmt) + " 00:00:00";
                endDate   = today.format(fmt)  + " 23:59:59";
                break;
            case "monthly":
                LocalDate firstDay = today.withDayOfMonth(1);
                startDate = firstDay.format(fmt) + " 00:00:00";
                endDate   = today.format(fmt)    + " 23:59:59";
                break;
            case "search":
                // 달력에서 선택한 날짜 하루 조회
                startDate = searchDate + " 00:00:00";
                endDate   = searchDate + " 23:59:59";
                break;
            default: // all - 전체 조회
                startDate = "2000-01-01 00:00:00";
                endDate   = today.format(fmt) + " 23:59:59";
                break;
        }
        return new String[]{startDate, endDate};
    }
}