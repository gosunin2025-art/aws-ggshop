package com.app.ggshop.v1.mapper;

import com.app.ggshop.v1.common.pagination.Criteria;
import com.app.ggshop.v1.dto.VtogPaymentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VtogPaymentMapper {
    List<VtogPaymentDTO> findAll();

//    // 페이징 조회
//    List<VtogPaymentDTO> findAll(Criteria criteria);
//
//    // 전체 카운트
//    int countAll();


    // 페이징 + 기간 조회
    List<VtogPaymentDTO> findAll(@Param("startDate") String startDate,
                                 @Param("endDate") String endDate,
                                 @Param("rowCount") int rowCount,
                                 @Param("offset") int offset);

    // 기간 카운트
    int countAll(@Param("startDate") String startDate,
                 @Param("endDate") String endDate);
}