package com.example.project1.service;

import com.example.project1.pojo.Insurances;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InsurancesService {
    //批量添加保险数据
    boolean addInsuranceList(List<Insurances> insurancesList);
    //查询10条保险数据
    String findInsuranceListLimitTen();
}
