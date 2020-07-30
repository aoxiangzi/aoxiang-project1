package com.zairui.project1.service;

import com.zairui.project1.pojo.Insurances;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InsurancesService {
    //添加保险数据
    int addInsurance(Insurances record);
    //批量添加保险数据
    boolean addInsuranceList(List<Insurances> insurancesList);
    //查询10条保险数据
    List<Insurances> findInsuranceListLimitTen();
    //查询规定数量数据
    List<Insurances> findInsuranceListLimit(int page,int limit);
    //查询符合条件的数据
    List<Insurances> findInsurancesList(Insurances insurance);
}
