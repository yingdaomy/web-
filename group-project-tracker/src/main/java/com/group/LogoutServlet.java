package com.group;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * LogoutServlet
 * <p>
 * 处理用户注销：
 * 1. 获取现有Session
 * 2. 使Session失效（删除所有Session中的用户信息）
 * 3. 重定向到登录页面
 */
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 获取已有Session（不创建新Session）
        HttpSession session = req.getSession(false);
        if (session != null) {
            // 使Session失效 → 删除所有用户信息
            session.invalidate();
        }
        // 重定向到登录页面
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
