package com.taihua.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {
    public static Date getDateTime(String date_str){
        Date date = null;
        try {
            DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = dateFormat1.parse(date_str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date_str!=null){

        }else {
            date = new Date();
        }
        // 使用 toString() 函数显示日期时间
        System.out.println(date.toString());
        return date;
    }
    public static Date getDateTime(){
        // 获取当前日期
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        // 使用 toString() 函数显示日期时间
        System.out.println(dateFormat1.format(date).toString());
        return date;
    }
}
