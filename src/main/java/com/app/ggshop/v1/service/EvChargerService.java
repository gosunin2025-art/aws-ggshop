package com.app.ggshop.v1.service;

import com.app.ggshop.v1.common.enumeration.Status;
import com.app.ggshop.v1.common.pagination.Criteria;
import com.app.ggshop.v1.domain.EvChargerVO;
import com.app.ggshop.v1.dto.EvChargerDTO;
import com.app.ggshop.v1.dto.PostWithPagingDTO;
import com.app.ggshop.v1.mapper.EvChargerMapper;
import com.app.ggshop.v1.repository.EvChargerDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.DateUtils;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class EvChargerService {

    private final EvChargerDAO evChargerDAO;

    //    충전소 등록
    public void registerEvCharger(EvChargerDTO dto) {

        // 기본 상태값 세팅 (form에 없으므로)
        dto.setEvChargerStatus(Status.ACTIVE);
        dto.setEvChargerMod(Status.ACTIVE);

        // TODO: 로그인 사용자 회사 ID 세팅
        dto.setCompanyId(1L);

        EvChargerVO evChargerVO = dto.toVO();

        log.info("▶ 충전소 등록 VO: {}", evChargerVO);

        evChargerDAO.save(evChargerVO);

//        evChargerDAO.insertEvCharger(evChargerVO);
    }

    public List<EvChargerVO> getEvChargerList(Long companyId) {
        List<EvChargerVO> chargerList = evChargerDAO.findAllByCompany(companyId);

        // 로그 출력
        log.info("목록 리스트 확인 회사 ID: {} 충전소 리스트: {}", companyId, chargerList);

        // System.out.println("회사 ID: " + companyId + " 충전소 리스트: " + chargerList);

        return chargerList;
    }

// 목록
    public PostWithPagingDTO list(int page){
        PostWithPagingDTO postWithPagingDTO = new PostWithPagingDTO();
        int total = evChargerDAO.findTotal();

        Criteria criteria = new Criteria(page, total);

        List<EvChargerDTO> list = evChargerDAO.findAll(criteria);

        criteria.setHasMore(list.size() > criteria.getRowCount());
        postWithPagingDTO.setCriteria(criteria);

        if(criteria.isHasMore()){
            list.remove(list.size() - 1);
        }


        postWithPagingDTO.setEvChargerList(list);

        return postWithPagingDTO;
    }

    public PostWithPagingDTO searchEvChargers(String searchType, String keyword, int page) {
        PostWithPagingDTO postWithPagingDTO = new PostWithPagingDTO();

        // 검색 조건에 맞는 전체 개수 조회
        int total = evChargerDAO.findTotalBySearch(searchType, keyword);

        Criteria criteria = new Criteria(page, total);

        // 검색 결과 조회 (페이징 포함)
        List<EvChargerDTO> list = evChargerDAO.findBySearch(searchType, keyword, criteria);

        criteria.setHasMore(list.size() > criteria.getRowCount());
        postWithPagingDTO.setCriteria(criteria);

        if(criteria.isHasMore()){
            list.remove(list.size() - 1);
        }

        postWithPagingDTO.setEvChargerList(list);

        return postWithPagingDTO;
    }

    // ID로 충전소 조회
    public EvChargerDTO getEvChargerById(Long id) {
        return evChargerDAO.findById(id);
    }

    // 충전소 수정
    public void updateEvCharger(EvChargerDTO evChargerDTO) {
        evChargerDAO.update(evChargerDTO);
    }

    // 충전기 삭제

    public void remove(Long id) {
        evChargerDAO.delete(id);
    }


    public boolean isDuplicateEvChargerUid(String evChargerUid) {
        return evChargerDAO.isDuplicateEvChargerUid(evChargerUid);
    }

}
