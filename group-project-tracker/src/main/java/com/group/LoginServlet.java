package com.group;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * LoginServlet
 * <p>
 * 实验要求：
 * 1. 请求转发与重定向
 * 2. Servlet处理Session
 * <p>
 * GET  - 显示登录页面（转发到 login.jsp）
 * POST - 校验用户名密码，创建Session（成功→重定向到/dashboard，失败→转发回login.jsp并提示错误）
 */
public class LoginServlet extends HttpServlet {

    // 模拟用户数据库（用户名 → 密码）
    private static final Map<String, String> USER_DB = new ConcurrentHashMap<>();

    @Override
    public void init() throws ServletException {
        // 初始化测试用户
        USER_DB.put("叶溢隆", "123456");
        USER_DB.put("张华之", "123456");
        USER_DB.put("冯俊阳", "123456");
    }

    /**
     * 判断用户是否已登录（用于 /login GET 时自动跳转）
     */
    public static boolean isLoggedIn(HttpSession session) {
        return session != null && session.getAttribute("username") != null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 如果已经登录，直接重定向到 dashboard
        HttpSession session = req.getSession(false);
        if (isLoggedIn(session)) {
            resp.sendRedirect(req.getContextPath() + "/dashboard");
            return;
        }
        // 否则，请求转发到登录页面
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 1. 获取表单参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 2. 参数校验
        if (username == null || password == null ||
            username.trim().isEmpty() || password.isEmpty()) {
            req.setAttribute("error", "用户名和密码不能为空！");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }

        username = username.trim();

        // 3. 校验用户名密码
        String expectedPassword = USER_DB.get(username);
        if (expectedPassword == null || !expectedPassword.equals(password)) {
            // 校验失败 → 请求转发回登录页，附带错误信息（请求转发）
            req.setAttribute("error", "⚠️ 用户名或密码错误，请重试。");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }

        // 4. 校验成功 → 创建/获取Session，保存用户信息
        HttpSession session = req.getSession(true);
        session.setAttribute("username", username);
        session.setAttribute("loginTime", new java.util.Date().toString());

        // 5. 重定向到 Dashboard（重定向）
        resp.sendRedirect(req.getContextPath() + "/dashboard");
    }
}
