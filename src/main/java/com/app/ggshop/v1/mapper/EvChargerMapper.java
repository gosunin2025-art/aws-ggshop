package com.app.ggshop.v1.mapper;

import com.app.ggshop.v1.common.pagination.Criteria;
import com.app.ggshop.v1.domain.EvChargerVO;
import com.app.ggshop.v1.dto.EvChargerDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EvChargerMapper {

    void insertEvCharger(EvChargerVO evChargerVO);
    List<EvChargerVO> selectEvChargerList(Long companyId);

    // companyId 상관없이 전체 조회
    List<EvChargerVO> selectAllEvChargers();

//    public int selectTotal();

    public List<EvChargerDTO> selectAll(Criteria criteria);

//    List<EvChargerVO> selectAll(
//            @Param("count") int count,
//            @Param("offset") int offset
//    );

    int selectTotalCount();

    List<EvChargerDTO> selectBySearch(
            @Param("searchType") String searchType,
            @Param("keyword") String keyword,
            @Param("criteria") Criteria criteria
    );

    int selectTotalBySearch(
            @Param("searchType") String searchType,
            @Param("keyword") String keyword
    );

    EvChargerDTO selectById(Long id);

    void updateEvCharger(EvChargerDTO evChargerDTO);

    // 삭제
    void deleteById(Long id);


    int countByEvChargerUid(String evChargerUid);

}
