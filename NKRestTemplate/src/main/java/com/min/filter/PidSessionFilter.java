package com.min.filter;


import com.min.util.PidRequestHolder;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

/**
 * @author jx
 * 拦截所有请求，缓存cookie
 */
@WebFilter(urlPatterns = "/*", filterName = "pidSessionFilter")
public class PidSessionFilter implements Filter {

    /**
     * 不需要拦截的url
     *
     */
    private static final String ALLOWED_PATHS = "/file/";

    private static  final String COOKIE_NAME = "FULONGTECH_SESSION";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
//        System.out.println(path);
        /**
         * 检查url是否为可以直接跳过
         */
//        System.out.println(allowedPath);
        if (path.contains(ALLOWED_PATHS)) {
            chain.doFilter(servletRequest, servletResponse);
            PidRequestHolder.remove();
            return;
        }

        Cookie[] cookies = request.getCookies();
        if(cookies==null){
            //没有就创建一个假的
            PidRequestHolder.add(COOKIE_NAME, UUID.randomUUID().toString());
            chain.doFilter(servletRequest, servletResponse);
            PidRequestHolder.remove();
            return;
        }


        for (Cookie cookie: cookies){
            if(cookie.getName().equals(COOKIE_NAME)){
                PidRequestHolder.add(COOKIE_NAME, cookie.getValue());
            }
        }
        chain.doFilter(servletRequest, servletResponse);
        PidRequestHolder.remove();
    }

    @Override
    public void destroy() {

    }
}
