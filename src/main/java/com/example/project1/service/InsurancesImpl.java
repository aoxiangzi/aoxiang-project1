package com.example.project1.service;

import com.example.project1.mapper.InsurancesMapper;
import com.example.project1.pojo.Insurances;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class InsurancesImpl implements InsurancesService {
    private final InsurancesMapper insurancesMapper;
    @Autowired
    public InsurancesImpl(InsurancesMapper insurancesMapper) {
        this.insurancesMapper = insurancesMapper;
    }

    //批量添加保险数据
    @Override
    public boolean addInsuranceList(List<Insurances> insurancesList) {
        return insurancesMapper.insertInsurancesBatch(insurancesList);
    }

    //获取十条数据库数据
    @Override
    public String findInsuranceListLimitTen() {
        List<Insurances> insurancesList = insurancesMapper.selectInsuranceListLimitTen();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        assert insurancesList != null;
        for (int i=0;i<insurancesList.size();i++) {
            insurancesList.get(i).setStatus("查询完成");
            insurancesList.get(i).setHit("命中");
            insurancesList.get(i).setTime(new SimpleDateFormat("yy-MM-dd HH:mm:dd").format(new Date()));
            insurancesList.get(i).setOperate("");
            sb.append("{" + "index:").append(i).append(",").append("batch:").append(insurancesList.get(i).getBatch()).append(",").append("id:").append(insurancesList.get(i).getId()).append(",").append("name:").append(insurancesList.get(i).getName()).append(",").append("phone:").append(insurancesList.get(i).getPhone()).append(",").append("status:").append(insurancesList.get(i).getStatus()).append(",").append("hit:").append(insurancesList.get(i).getHit()).append(",").append("time:").append(insurancesList.get(i).getTime()).append(",").append("operate:").append(insurancesList.get(i).getOperate()).append("},");
        }
        sb.append("\b]");
        return sb.toString();
    }
}
