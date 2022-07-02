package com.itheima.web.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*
  Session进阶
   Seesion有效期：
        1.默认情况下，无操作，30分钟自动销毁(Tomcat的web.xml查看)
            <session-config>
                <session-timeout>30</session-timeout>
            </session-config>
        2.调用 Session对象的 invalidate()方法，立即失效
        3.设置失效时间 setMaxInactiveInterval();  单位是秒，到期了就失效销毁

     invalidate()方法和removeAttribute()方法的区别：
        invalidate()表示session立即失效 session对象被销毁 因此该session中数据也被删除销毁了，找不到了
        removeAttribute()表示移除session对象中指定名称的某个数据  session对象还在 ，session对象中存的其他数据也还在

 */
@WebServlet("/sessionDemo03")
public class SessionDemo03 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("SessionDemo03...");
        HttpSession session = request.getSession();

        //Session销毁
        //1.立即失效
        //session.invalidate();

        //2.设置session有效时间  单位是s
        session.setMaxInactiveInterval(20);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}