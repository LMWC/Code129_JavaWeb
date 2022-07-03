package com.itheima.controller;

import com.itheima.bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ShowServlet", value = "/show")
public class ShowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = (List<User>) request.getAttribute("users");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>首页</title>\n" +
                "</head>\n" +
                "<body>");

        writer.write("<table align=\"center\" width=\"300\" cellspacing=\"0\" border=\"1\">\n" +
                "    <tr>\n" +
                "        <th>id</th>\n" +
                "        <th>用户名</th>\n" +
                "        <th>密码</th>\n" +
                "    </tr>");

        for (User user : users) {
            writer.write("<tr align=\"center\">\n" +
                    "        <td>" + user.getId() + "</td>\n" +
                    "        <td>" + user.getUsername() + "</td>\n" +
                    "        <td>" + user.getPassword() + "</td>\n" +
                    "    </tr>");
        }

        writer.write("</table>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

