package com.example.project1;

import com.example.project1.mapper.InsurancesMapper;
import com.example.project1.service.InsurancesService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan("com.example.project1.mapper")
class AoxiangwebApplicationTests {
    @Autowired
    public InsurancesMapper insurancesMapper;
    @Autowired
    public InsurancesService insurancesImpl;

    @Test
    void test(){
        try {
            System.out.println(insurancesImpl.findInsuranceListLimitTen());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
