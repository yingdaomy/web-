package com.group;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * DashboardServlet
 * <p>
 * 主面板页面：从Session中获取登录用户名，转发到dashboard.jsp展示。
 * 未登录的用户会被 AuthFilter 拦截并重定向到登录页。
 */
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        // 双重校验：如果 Session 中没有用户信息，重定向到登录页
        if (session == null || session.getAttribute("username") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // 将用户名存入请求属性，供 JSP 使用
        String username = (String) session.getAttribute("username");
        req.setAttribute("username", username);

        // 请求转发到 dashboard.jsp
        req.getRequestDispatcher("/dashboard.jsp").forward(req, resp);
    }
}
