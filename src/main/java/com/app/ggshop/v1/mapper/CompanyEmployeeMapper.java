package com.app.ggshop.v1.mapper;

import com.app.ggshop.v1.common.pagination.Criteria;
import com.app.ggshop.v1.dto.CompanyEmployeeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CompanyEmployeeMapper {
    // 임직원 등록
    void insertEmployee(CompanyEmployeeDTO companyEmployeeDTO);

    // 임직원 목록 조회 (페이징)
    List<CompanyEmployeeDTO> selectAll(@Param("criteria") Criteria criteria);

    // 임직원 전체 개수
    int selectTotal();

    // 임직원 ID로 조회
    CompanyEmployeeDTO selectById(Long id);

    // 임직원 삭제
    void deleteById(Long id);

    // 임직원 수정
    void updateEmployee(CompanyEmployeeDTO companyEmployeeDTO);

    // 검색 포함 목록
    List<CompanyEmployeeDTO> selectAllWithSearch(
            @Param("criteria") Criteria criteria,
            @Param("searchType") String searchType,
            @Param("keyword") String keyword
    );

    // 검색 포함 개수
    int selectTotalWithSearch(
            @Param("searchType") String searchType,
            @Param("keyword") String keyword
    );

    int countByEmployeeNumber(String employeeNumber);


}