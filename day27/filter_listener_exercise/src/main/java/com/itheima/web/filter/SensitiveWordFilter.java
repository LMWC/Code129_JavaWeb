package com.itheima.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter("/*")
public class SensitiveWordFilter implements Filter {

    private List<String> words;

    public void init(FilterConfig config) throws ServletException {
        words = new ArrayList<>();
        words.add("你妈");
        words.add("傻子");
        words.add("山寨");
        words.add("水货");
        words.add("盗版");
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        MyRequest myRequest = new MyRequest((HttpServletRequest) request,words);
        chain.doFilter(myRequest, response);
    }
}
//请求包装类
class MyRequest extends HttpServletRequestWrapper {
    private HttpServletRequest request;
    private List<String> words;
    public MyRequest(HttpServletRequest request,List<String> words) {
        super(request);
        this.request  = request;
        this.words = words;
    }

    @Override
    public String getParameter(String name) {
        String value = request.getParameter(name);
        if(value!=null&&!value.isEmpty()){
            //分别对每一个敏感字循环一次
            for (int i = 0; i <words.size() ; i++) {
                //如果在txt中发现此次循环的敏感字则将其替换为"**",没有发现则不执行替换方法
                if(value.indexOf(words.get(i))!=-1) {
                    value=value.replaceAll(words.get(i), "**");
                }
            }
        }
        return value;
    }
}

