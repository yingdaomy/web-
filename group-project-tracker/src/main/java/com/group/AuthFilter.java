package com.group;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * AuthFilter
 * <p>
 * 登录验证过滤器。
 * 拦截 /dashboard 和 /dashboard.jsp 请求，
 * 检查用户是否已登录（Session中是否有username属性）。
 * 如果未登录，则重定向到登录页面。
 */
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化（无需操作）
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        // 获取Session（不创建新Session）
        HttpSession session = req.getSession(false);

        // 检查是否已登录
        boolean loggedIn = (session != null && session.getAttribute("username") != null);

        if (loggedIn) {
            // 已登录 → 放行请求
            chain.doFilter(request, response);
        } else {
            // 未登录 → 重定向到登录页面
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }

    @Override
    public void destroy() {
        // 清理（无需操作）
    }
}
