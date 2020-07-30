package com.zairui.project1.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PhonesTool {
    public static List<String> createPhonesList(int num,boolean different){
        List<String> phoneList = new ArrayList<>();
        String[] head = new String[]{
                "130","131","132","133","134","135","136","137","138","139",
                "150","151","152","153","154","155","156","157","158","159",
                "170","171","172","173","174","175","176","177","178","179",
                "180","181","182","183","184","185","186","187","188","189"
        };
        while(num-- != 0){
            String phone;
            StringBuilder rail = new StringBuilder();
            int len = 8;
            while(len-- != 0){
                rail.append((new Random()).nextInt(10));
            }
            phone = head[(new Random()).nextInt(100) % head.length] + rail;
            if(different && phoneList.contains(phone)){
                num++;
            }else{
                phoneList.add(phone);
            }
        }
        return phoneList;
    }
}
