<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录 - 小组项目系统</title>
    <link rel="stylesheet" href="style.css">
</head>
<body class="login-body">
    <div class="login-container">
        <div class="login-card">
            <div class="login-header">
                <h1>📋 小组协作空间</h1>
                <p>课程项目 · 进展规划系统</p>
            </div>

            <!--
                表单提交到 /login (POST)
                由 LoginServlet 处理用户名密码校验
            -->
            <form action="${pageContext.request.contextPath}/login" method="post">
                <div class="input-field">
                    <label>用户名</label>
                    <input type="text" name="username" placeholder="例如: 叶溢隆" autocomplete="off" required>
                </div>
                <div class="input-field">
                    <label>密码</label>
                    <input type="password" name="password" placeholder="请输入密码" required>
                </div>

                <!-- 错误提示：由 LoginServlet 通过请求转发设置 request 属性 -->
                <%
                    String error = (String) request.getAttribute("error");
                    if (error != null) {
                %>
                    <div class="error-message"><%= error %></div>
                <%
                    }
                %>

                <button type="submit" class="btn-login">登录系统</button>
            </form>

            <div class="test-accounts">
                <strong>🔑 测试账号</strong>
                <ul>
                    <li>叶溢隆 / 123456</li>
                    <li>张华之 / 123456</li>
                    <li>冯俊阳 / 123456</li>
                </ul>
            </div>
        </div>
    </div>
</body>
</html>
