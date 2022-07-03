<%@ page import="com.itheima.service.ProductService" %>
<%@ page import="com.itheima.bean.ProductCount" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.itheima.bean.Product" %><%--
  Created by IntelliJ IDEA.
  User: lmw23
  Date: 2022/5/21
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>cart</title>
</head>
<body>
<%
    //1.获取请求参数【没有】
    //2.调用业务处理
    //解决请求响应中文乱码
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");

    double sum = 0.00;

    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies){
        if(cookie.getName().equals("count")){
            String a=cookie.getValue();
            String[] b=new String[6];
            b=a.split("-");
            int[] c=new int[6];
            for(int i=0;i<6;i++){
                c[i]=Integer.parseInt(b[i]);
                if(c[i]!=0){
                    if(i==0){
                        sum=(double)(sum+13999.99*c[i]);
                    }else if(i==1){
                        sum=(double)(sum+16789.88*c[i]);
                    }else if(i==2){
                        sum=(double)(sum+25899.88*c[i]);
                    }else if(i==3){
                        sum=(double)(sum+29999.99*c[i]);
                    }else if(i==4){
                        sum=(double)(sum+3100*c[i]);
                    }else{
                        sum=(double)(sum+338*c[i]);
                    }
                }
            }
        }
    }
    System.out.println("sum = " + sum);
    request.setAttribute("sum",sum);
%>

    <table border="1" cellspacing="0" cellpadding="0" width="100%" align="center">
        <tr>
            <th colspan="3" align="center">购物车</th>
        </tr>
        <tr>
            <th>名称</th>
            <th>价格</th>
            <th>数量</th>
        </tr>
        <%--获取request域对象中存储的品牌列表数据  使用JSTL的forEach标签遍历展示--%>
        <%--每遍历一次  就输出一条品牌信息记录到页面--%>
        <c:forEach items="${list}" var="productCount">
            <tr align="center">
                <td>${productCount.NAME}</td>
                <td>${productCount.price}</td>
                <td>${productCount.count}</td>
                    <%--<td><a href="selectById?id=${brand.id}">修改</a> <a onclick="deleteById(${brand.id})">删除</a></td>--%>
            </tr>
        </c:forEach>
        <tr>
            <th colspan="3" align="right">合计:${sum}</th>
        </tr>
    </table>
</body>
</html>
