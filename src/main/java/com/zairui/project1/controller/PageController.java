package com.zairui.project1.controller;

import com.zairui.project1.pojo.Insurances;
import com.zairui.project1.service.InsurancesService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class PageController{
    private final InsurancesService insurancesImpl;
    @Autowired
    public PageController(InsurancesService insurancesImpl) {
        this.insurancesImpl = insurancesImpl;
    }

    //初次访问网站
    @RequestMapping("/")
    public String index(){
        return "redirect:index";
    }

    @RequestMapping("/{jumpPage}")
    public ModelAndView page(@PathVariable String jumpPage, ModelAndView mav){
        //***进入首页***//
        switch (jumpPage){
            case "index":
                break;
            case "insurances-search":
                /*StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("{\"code\": 0,\"msg\": \"\",\"count\": ").append(0).append(",\"data\" : []}");
                try{
                    File f = ResourceUtils.getFile("classpath:templates/insurances.json");
                    FileUtils.writeStringToFile(f,stringBuilder.toString(),"UTF-8");
                }catch (Exception e){
                    e.printStackTrace();
                }*/
                break;
            case "insurances-restore":
                break;
            case "insurances-census":
                break;
        }
        mav.setViewName(jumpPage);
        return mav;
    }
}
