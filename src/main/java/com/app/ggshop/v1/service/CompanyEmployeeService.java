package com.app.ggshop.v1.service;

import com.app.ggshop.v1.common.pagination.Criteria;
import com.app.ggshop.v1.dto.CompanyEmployeeDTO;
import com.app.ggshop.v1.dto.PostWithPagingDTO;
import com.app.ggshop.v1.repository.CompanyEmployeeDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CompanyEmployeeService {
    private final CompanyEmployeeDAO companyEmployeeDAO;

    // 임직원 등록
    public void registerEmployee(CompanyEmployeeDTO companyEmployeeDTO) {
        companyEmployeeDAO.save(companyEmployeeDTO);
    }


    // 임직원 목록 조회 (페이징)
//    public PostWithPagingDTO list(int page) {
//        PostWithPagingDTO postWithPagingDTO = new PostWithPagingDTO();
//
//        int total = companyEmployeeDAO.findTotal();
//        Criteria criteria = new Criteria(page, total);
//
//        List<CompanyEmployeeDTO> list = companyEmployeeDAO.findAll(criteria);
//
//        criteria.setHasMore(list.size() > criteria.getRowCount());
//        if (criteria.isHasMore()) {
//            list.remove(list.size() - 1);
//        }
//
//        postWithPagingDTO.setEmployeeList(list);
//        postWithPagingDTO.setCriteria(criteria);
//
//        return postWithPagingDTO;
//    }

    /**
     * 임직원 목록 (기존 - 전체 목록)
     */
    public PostWithPagingDTO list(int page) {
        PostWithPagingDTO postWithPagingDTO = new PostWithPagingDTO();

        int total = companyEmployeeDAO.findTotal();
        Criteria criteria = new Criteria(page, total);

        List<CompanyEmployeeDTO> list = companyEmployeeDAO.findAll(criteria);

        criteria.setHasMore(list.size() > criteria.getRowCount());
        if (criteria.isHasMore()) {
            list.remove(list.size() - 1);
        }
        postWithPagingDTO.setEmployeeList(list);
        postWithPagingDTO.setCriteria(criteria);

        return postWithPagingDTO;
    }

    /**
     * 임직원 목록 (검색 포함)
     */
    public PostWithPagingDTO list(int page, String searchType, String keyword) {
        PostWithPagingDTO postWithPagingDTO = new PostWithPagingDTO();

        int total;
        List<CompanyEmployeeDTO> list;

        // 검색 여부 확인
        if (keyword != null && !keyword.isEmpty()) {
            System.out.println("▶▶▶ 검색! searchType=" + searchType + ", keyword=" + keyword);
            total = companyEmployeeDAO.findTotalWithSearch(searchType, keyword);
            Criteria criteria = new Criteria(page, total);

            list = companyEmployeeDAO.findAllWithSearch(criteria, searchType, keyword);

            criteria.setHasMore(list.size() > criteria.getRowCount());
            if (criteria.isHasMore()) {
                list.remove(list.size() - 1);
            }

            postWithPagingDTO.setEmployeeList(list);
            postWithPagingDTO.setCriteria(criteria);

            System.out.println("▶▶▶ 검색 결과: " + list.size() + "개");
        } else {
            // 검색 없으면 기존 메서드 호출
            return list(page);
        }
        return postWithPagingDTO;
    }

    // 임직원 상세 조회
    public CompanyEmployeeDTO getDetail(Long id) {
        return companyEmployeeDAO.findById(id);
    }

    // 임직원 수정
    public void modify(CompanyEmployeeDTO companyEmployeeDTO) {
        companyEmployeeDAO.update(companyEmployeeDTO);
    }

    // 임직원 삭제
    public void remove(Long id) {
        companyEmployeeDAO.delete(id);
    }

}