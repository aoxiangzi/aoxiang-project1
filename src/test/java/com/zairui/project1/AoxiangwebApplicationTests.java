package com.zairui.project1;

import com.zairui.project1.mapper.InsurancesMapper;
import com.zairui.project1.pojo.Insurances;
import com.zairui.project1.service.InsurancesService;
import com.zairui.project1.tools.ExcelTool;
import com.zairui.project1.tools.NamesTool;
import com.zairui.project1.tools.PhonesTool;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.formula.functions.Na;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@MapperScan("com.example.project1.mapper")
class AoxiangwebApplicationTests {
    @Autowired
    public InsurancesMapper insurancesMapper;
    @Autowired
    public InsurancesService insurancesImpl;

    @Test
    void test(){
        Insurances insurance = new Insurances("","","","","","","","");
        List<Insurances> insurancesList = insurancesImpl.findInsuranceListLimit(1,10);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\"code\": 0,\"count\": ").append(insurancesList.size()).append(",\"data\": [");
        for(int i=0;i<insurancesList.size();i++){
            stringBuilder.append("{\"index\":\"").append(i + 1).append("\",").append("\"batch\":\"").append(insurancesList.get(i).getBatch()).append("\",").append("\"id\":\"").append(insurancesList.get(i).getId()).append("\",").append("\"name\":\"").append(insurancesList.get(i).getName()).append("\",").append("\"phone\":\"").append(insurancesList.get(i).getPhone()).append("\",").append("\"status\":\"").append(insurancesList.get(i).getStatus()).append("\",").append("\"hit\":\"").append(insurancesList.get(i).getHit()).append("\",").append("\"time\":\"").append(insurancesList.get(i).getTime()).append("\",").append("\"operate\":\"").append(insurancesList.get(i).getOperate()).append("\"},");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1).append("]}");
        try{
            File f = ResourceUtils.getFile("classpath:templates/insurances.json");
            FileUtils.writeStringToFile(f,stringBuilder.toString(),"UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
