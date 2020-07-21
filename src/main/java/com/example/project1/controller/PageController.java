package com.example.project1.controller;

import com.example.project1.service.InsurancesImpl;
import com.example.project1.service.InsurancesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class PageController{
    @Autowired
    public InsurancesService insurancesImpl;

    //初次访问网站
    @RequestMapping("/")
    public String index(){
        return "redirect:index";
    }

    @RequestMapping("/{page}")
    public ModelAndView page(@PathVariable String page, ModelAndView mav, HttpSession session) {
        //***进入首页***//
        switch (page){
            case "index":
                break;
            case "query(1)":
                mav.addObject("insuranceTenData",insurancesImpl.findInsuranceListLimitTen());
                System.out.println("#################################");
                break;
        }
        mav.setViewName(page);
        return mav;
    }


}
