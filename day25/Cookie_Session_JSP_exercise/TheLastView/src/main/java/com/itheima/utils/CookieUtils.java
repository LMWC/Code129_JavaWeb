package com.itheima.utils;

import javax.servlet.http.Cookie;

public class CookieUtils {
    public static String getCookieValue(Cookie[] cookies,String name){
        //cookie数组不为null 并且cookie名称name不为null或者""  进行遍历查找
        if(cookies!=null && name!=null && name.length()>0){
            for (Cookie cookie : cookies) {
                if(name.equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        //找不到指定名称的Cookie
        return null;
    }
}
