package com.itheima.web;

import com.itheima.utils.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/VoteServlet")
public class VoteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //解决请求响应中文乱码
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");

            Cookie[] cookies = request.getCookies();
            String count = CookieUtils.getCookieValue(cookies,"count");

            if (count==null){
                Cookie cookie = new Cookie("count","1");
                cookie.setMaxAge(60*60*24);
                response.addCookie(cookie);
                response.getWriter().print("恭喜您，投票成功！");
                response.setHeader("refresh","3;url=http://localhost:8080/");
            }else if(count.equals("3")){
                response.getWriter().print("每人每天最大投票次数为3次，你的投票次数已达上限！");
                response.setHeader("refresh","3;url=http://localhost:8080/");
            }else{
                int n =Integer.parseInt(count);
                n++;
                String m = Integer.toString(n);
                Cookie cookie = new Cookie("count",m);
                cookie.setMaxAge(60*60*24);
                response.addCookie(cookie);
                response.getWriter().print("恭喜您，投票成功！");
                response.setHeader("refresh","3;url=http://localhost:8080/");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
