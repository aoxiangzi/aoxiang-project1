package com.example.project1.mapper;

import com.example.project1.pojo.Insurances;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
public interface InsurancesMapper{
    int deleteByPrimaryKey(String id);

    int insert(Insurances record);

    int insertSelective(Insurances record);

    //批量插入保险数据
    boolean insertInsurancesBatch(List<Insurances> insurancesList);
    //查询10条保险数据
    List<Insurances> selectInsuranceListLimitTen();

    Insurances selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Insurances record);

    int updateByPrimaryKey(Insurances record);
}