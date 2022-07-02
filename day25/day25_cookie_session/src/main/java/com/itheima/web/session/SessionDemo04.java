package com.itheima.web.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/*
  Session进阶
   Seesion持久化：
        服务器正常运行，浏览器关闭了, session使用不了, 是session销毁了吗?
            session没有销毁,只是浏览器端的含有sessionId的cookie没有了.
            session基于cookie, sessionId保存到cookie里面的, 默认情况下cookie是会话级别,浏览器关闭了cookie就是消失了,也就是说sessionId消失了, 从而找不到对应的session对象了, 就不能使用了.
        分析:
            服务器响应的sessionId,是使用的默认级别的Cookie,关闭浏览器,cookie销毁了,也就是sessionId没有了,但session对象还在服务器中
            如何解决服务器不关闭，浏览器关闭重新打开可以继续使用服务器中的session？
       手动完成session持久化
            让服务器响应的sessionId是持久级别的Cookie,关闭浏览器,cookie还在,也就是sessionId还在,并且session对象也还在服务器中, 可以获取上一次会话保存在session中的数据
            实现: 自己获得sessionId, 自己写给浏览器 设置Cookie的有效时长, 这个Cookie的key必须: JSESSIONID
   Session钝化活化：
        浏览器正常开启，服务器正常关机重启，请问 浏览器还可以继续获取到session中的数据吗？
            eg：

 */
@WebServlet("/sessionDemo04")
public class SessionDemo04 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("SessionDemo04...");

        //Session持久化：服务器正常运行 浏览器关闭之后重新打开依然可以使用原来的session对象  需要对sessionId进行cookie持久化
        //1.获取session对象
        HttpSession session = request.getSession();

        //2.获取sessionId
        String id = session.getId();

        //3.手动完成将sessionId存入cookie写给浏览器 并设置有效时间
        Cookie cookie = new Cookie("JSESSIONID", id);
        cookie.setMaxAge(60*30);
        response.addCookie(cookie);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}