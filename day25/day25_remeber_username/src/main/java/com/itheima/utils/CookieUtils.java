package com.itheima.utils;

import javax.servlet.http.Cookie;

/**
 * Cookie工具类
 */
public class CookieUtils {

    /**
     * 根据名称获取指定Cookie的值
     * @param cookies cookie数组
     * @param name cookie名称
     * @return cookie的值
     */
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
