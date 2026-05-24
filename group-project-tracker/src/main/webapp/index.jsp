<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    // 检查Session中是否有用户信息
    // 如果有 → 重定向到 dashboard
    // 如果没有 → 重定向到 login
    String username = (session != null) ? (String) session.getAttribute("username") : null;
    if (username != null) {
        response.sendRedirect(request.getContextPath() + "/dashboard");
    } else {
        response.sendRedirect(request.getContextPath() + "/login");
    }
%>
