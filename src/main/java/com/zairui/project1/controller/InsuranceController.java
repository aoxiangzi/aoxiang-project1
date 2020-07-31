package com.zairui.project1.controller;

import com.zairui.project1.pojo.Insurances;
import com.zairui.project1.service.InsurancesImpl;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/insurances")
public class InsuranceController{
    private final InsurancesImpl insurancesImpl;
    @Autowired
    public InsuranceController(InsurancesImpl insurancesImpl) {
        this.insurancesImpl = insurancesImpl;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping("/findInsurancesList/{splitPage}")
    public ModelAndView findInsurancesList(ModelAndView mav,String start, String end, Insurances insurance, @PathVariable String splitPage, HttpSession session){
        boolean isWriteSuccess;
        List<Insurances> insurancesList = (List<Insurances>) session.getAttribute("insurancesList");
        if(insurancesList == null){
            insurancesList = insurancesImpl.findInsurancesList(insurance);
            session.setAttribute("insurancesList",insurancesList);
        }
        List<Insurances> newInsurancesList = insurancesList;
        if(insurancesList.size() > 10){
            int preIndex = (Integer.parseInt(splitPage) - 1) * 10;
            int nexIndex = (Integer.parseInt(splitPage) * 10) < insurancesList.size() ? (Integer.parseInt(splitPage) * 10) : insurancesList.size();
            newInsurancesList = insurancesList.subList(preIndex,nexIndex);
        }else{
            newInsurancesList = insurancesList.subList(0,insurancesList.size());
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\"code\": 0,\"msg\": \"\",\"count\": ").append(insurancesList.size()).append(",\"data\": [");
        for(int i=0;i<newInsurancesList.size();i++){
            stringBuilder.append("{\"index\":\"").append(i + 1).append("\",").append("\"batch\":\"").append(newInsurancesList.get(i).getBatch()).append("\",").append("\"id\":\"").append(newInsurancesList.get(i).getId()).append("\",").append("\"name\":\"").append(newInsurancesList.get(i).getName()).append("\",").append("\"phone\":\"").append(newInsurancesList.get(i).getPhone()).append("\",").append("\"status\":\"").append(newInsurancesList.get(i).getStatus()).append("\",").append("\"hit\":\"").append(newInsurancesList.get(i).getHit()).append("\",").append("\"time\":\"").append(newInsurancesList.get(i).getTime()).append("\",").append("\"operate\":\"").append(newInsurancesList.get(i).getOperate()).append("\"},");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1).append("]}");
        try{
            File f = ResourceUtils.getFile("classpath:templates/insurances.json");
            FileUtils.writeStringToFile(f,stringBuilder.toString(),"UTF-8");
            isWriteSuccess = true;
        }catch (Exception e){
            isWriteSuccess = false;
        }
        if(isWriteSuccess){
            mav.setViewName("insurances-search");
        }
        return mav;
    };

    @ResponseBody
    @RequestMapping("/getInsurancesTable")
    public String getInsurancesTable(String start, String end, Insurances insurance, int page, int limit,HttpSession httpSession){
        if(start == null && end == null && insurance.getBatch() == null && insurance.getId() == null && insurance.getName() == null && insurance.getPhone() == null){
            return "{\"code\": 0,\"count\": 0,\"data\": []}";
        }else{
            List<Insurances> insurancesList = insurancesImpl.findInsurancesList(insurance);
            List<Insurances> newnsurancesList = new ArrayList<>();
            if(insurancesList.size() > 10){
                newnsurancesList = insurancesList.subList((page - 1) * limit,page * limit);
            }else{
                newnsurancesList = insurancesList;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("{\"code\": 0,\"count\": ").append(insurancesList.size()).append(",\"data\": [");
            for(int i=0;i<newnsurancesList.size();i++){
                stringBuilder.append("{\"index\":\"").append(i + 1).append("\",").append("\"batch\":\"").append(newnsurancesList.get(i).getBatch()).append("\",").append("\"id\":\"").append(newnsurancesList.get(i).getId()).append("\",").append("\"name\":\"").append(newnsurancesList.get(i).getName()).append("\",").append("\"phone\":\"").append(newnsurancesList.get(i).getPhone()).append("\",").append("\"status\":\"").append(newnsurancesList.get(i).getStatus()).append("\",").append("\"hit\":\"").append(newnsurancesList.get(i).getHit()).append("\",").append("\"time\":\"").append(newnsurancesList.get(i).getTime()).append("\",").append("\"operate\":\"").append(newnsurancesList.get(i).getOperate()).append("\"},");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1).append("]}");
            System.out.println("insurancesList.size():"+insurancesList.size()+"\nstart:"+start+",end:"+end+",Insurance:"+insurance.toString() + "page:"+page+":limit:"+limit);
            return stringBuilder.toString();
        }
    }
}
