package com.app.ggshop.v1.repository;

import com.app.ggshop.v1.common.pagination.Criteria;
import com.app.ggshop.v1.dto.VtogPaymentDTO;
import com.app.ggshop.v1.mapper.VtogPaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class VtogPaymentDao {

    private final VtogPaymentMapper vtogPaymentMapper;

//    public List<VtogPaymentDTO> findAll() {
//        return vtogPaymentMapper.findAll();
//    }

//    // 페이징 조회
//    public List<VtogPaymentDTO> findAll(Criteria criteria) {
//        return vtogPaymentMapper.findAll(criteria);
//    }
//
//    // 전체 카운트
//    public int countAll() {
//        return vtogPaymentMapper.countAll();
//    }

    public List<VtogPaymentDTO> findAll(String startDate, String endDate, Criteria criteria) {
        return vtogPaymentMapper.findAll(startDate, endDate, criteria.getRowCount(), criteria.getOffset());
    }

    public int countAll(String startDate, String endDate) {
        return vtogPaymentMapper.countAll(startDate, endDate);
    }
}