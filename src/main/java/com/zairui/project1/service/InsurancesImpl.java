package com.zairui.project1.service;

import com.zairui.project1.mapper.InsurancesMapper;
import com.zairui.project1.pojo.Insurances;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InsurancesImpl implements InsurancesService {
    @Autowired
    private final InsurancesMapper insurancesMapper;
    public InsurancesImpl(InsurancesMapper insurancesMapper) {
        this.insurancesMapper = insurancesMapper;
    }

    //添加保险数据
    @Override
    public int addInsurance(Insurances record) {
        return insurancesMapper.insert(record);
    }

    //批量添加保险数据
    @Override
    public boolean addInsuranceList(List<Insurances> insurancesList) {
        return insurancesMapper.insertInsurancesBatch(insurancesList);
    }

    private void adjustInsuranceList(List<Insurances> insurancesList) {
        for (Insurances anInsurancesList : insurancesList) {
            anInsurancesList.setStatus("查询完成");
            anInsurancesList.setHit("命中");
            anInsurancesList.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:dd").format(new Date()));
            anInsurancesList.setOperate("");
        }
    }

    //获取十条数据库数据
    @Override
    public List<Insurances> findInsuranceListLimitTen() {
        List<Insurances> insurancesList = insurancesMapper.selectInsuranceListLimitTen();
        assert insurancesList != null;
        adjustInsuranceList(insurancesList);
        return insurancesList;
    }

    @Override
    public List<Insurances> findInsuranceListLimit(int page, int limit) {
        Map<String,Integer> map = new HashMap<>();
        map.put("page",page-1);
        map.put("limit",limit);
        return insurancesMapper.selectInsuranceListLimit(map);

    }

    //查询符合条件的数据
    @Override
    public List<Insurances> findInsurancesList(Insurances insurance) {
        List<Insurances> insurancesList = insurancesMapper.selectInsurancesList(insurance);
        assert insurancesList != null;
        adjustInsuranceList(insurancesList);
        return insurancesList;
    }
}
