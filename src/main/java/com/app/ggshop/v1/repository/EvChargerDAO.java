package com.app.ggshop.v1.repository;

import com.app.ggshop.v1.common.pagination.Criteria;
import com.app.ggshop.v1.domain.EvChargerVO;
import com.app.ggshop.v1.dto.EvChargerDTO;
import com.app.ggshop.v1.mapper.EvChargerMapper;
import com.app.ggshop.v1.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EvChargerDAO {

    private final EvChargerMapper evChargerMapper;

    public void save(EvChargerVO evChargerVO) {
        evChargerMapper.insertEvCharger(evChargerVO);
    }

    public List<EvChargerVO> findAllByCompany(Long companyId) {
        return evChargerMapper.selectEvChargerList(companyId);
    }

    public List<EvChargerDTO> findAll(Criteria criteria){
        return evChargerMapper.selectAll(criteria);
    }

    //    전체 개수
    public int findTotal(){
        return evChargerMapper.selectTotalCount();
    }

    // 검색 결과 조회
    public List<EvChargerDTO> findBySearch(String searchType, String keyword, Criteria criteria) {
        return evChargerMapper.selectBySearch(searchType, keyword, criteria);
    }

    // 검색 결과 총 개수
    public int findTotalBySearch(String searchType, String keyword) {
        return evChargerMapper.selectTotalBySearch(searchType, keyword);
    }

    // ID로 조회
    public EvChargerDTO findById(Long id) {
        return evChargerMapper.selectById(id);
    }

    // 충전소 수정
    public void update(EvChargerDTO evChargerDTO) {
        evChargerMapper.updateEvCharger(evChargerDTO);
    }

    //  충전기 삭제
    public void delete(Long id) {
        evChargerMapper.deleteById(id);
    }

    public boolean isDuplicateEvChargerUid(String evChargerUid) {
        return evChargerMapper.countByEvChargerUid(evChargerUid) > 0;
    }

}