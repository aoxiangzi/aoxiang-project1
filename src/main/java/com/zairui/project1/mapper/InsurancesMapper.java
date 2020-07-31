package com.zairui.project1.mapper;

import com.zairui.project1.pojo.Insurances;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface InsurancesMapper {
    int deleteByPrimaryKey(String id);

    int insert(Insurances record);

    int insertSelective(Insurances record);

    boolean insertInsurancesBatch(List<Insurances> insurancesList);

    List<Insurances> selectInsuranceListLimitTen();

    List<Insurances> selectInsuranceListLimit(Map map);

    List<Insurances> selectInsurancesList(Insurances insurance);

    Insurances selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Insurances record);

    int updateByPrimaryKey(Insurances record);
}