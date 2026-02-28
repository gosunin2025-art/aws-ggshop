package com.app.ggshop.v1.repository;

import com.app.ggshop.v1.common.pagination.Criteria;
import com.app.ggshop.v1.dto.CompanyEmployeeDTO;
import com.app.ggshop.v1.mapper.CompanyEmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CompanyEmployeeDAO {
    private final CompanyEmployeeMapper companyEmployeeMapper;

    // 임직원 등록
    public void save(CompanyEmployeeDTO companyEmployeeDTO) {
        companyEmployeeMapper.insertEmployee(companyEmployeeDTO);
    }

    // 임직원 목록 조회 (페이징)
    public List<CompanyEmployeeDTO> findAll(Criteria criteria) {
        return companyEmployeeMapper.selectAll(criteria);
    }

    // 임직원 전체 개수
    public int findTotal() {
        return companyEmployeeMapper.selectTotal();
    }


    // 임직원 ID로 조회
    public CompanyEmployeeDTO findById(Long id) {
        return companyEmployeeMapper.selectById(id);
    }

    // 임직원 삭제
    public void delete(Long id) {
        companyEmployeeMapper.deleteById(id);
    }

    // 임직원 수정
    public void update(CompanyEmployeeDTO companyEmployeeDTO) {
        companyEmployeeMapper.updateEmployee(companyEmployeeDTO);
    }

    // 검색 포함 목록
    public List<CompanyEmployeeDTO> findAllWithSearch(Criteria criteria, String searchType, String keyword) {
        return companyEmployeeMapper.selectAllWithSearch(criteria, searchType, keyword);
    }

    // 검색 포함 개수
    public int findTotalWithSearch(String searchType, String keyword) {
        return companyEmployeeMapper.selectTotalWithSearch(searchType, keyword);
    }

}
