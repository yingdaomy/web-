<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.group.TeamData" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>814小组 · 项目驾驶舱</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="app-wrapper">
        <!-- 头部：从 Session 获取用户名 + 注销按钮 -->
        <header class="app-header">
            <div class="logo">
                <h2>🚀 814小组</h2>
                <span class="subtitle">高校社团管理平台</span>
            </div>
            <div class="user-panel">
                <span class="username-badge">👤 ${username}</span>
                <a href="${pageContext.request.contextPath}/logout" class="logout-btn-link">
                    <button class="logout-btn">注销</button>
                </a>
            </div>
        </header>

        <!-- Tab 导航栏 -->
        <nav class="tab-bar">
            <button class="tab-btn active" onclick="switchTab('overview')">📋 小组概况</button>
            <button class="tab-btn" onclick="switchTab('members')">👥 成员简介</button>
            <button class="tab-btn" onclick="switchTab('project')">📅 项目进展与规划</button>
        </nav>

        <!-- 动态内容区域 -->
        <main id="mainContent" class="main-content">
            <div class="loading-placeholder">加载中...</div>
        </main>

        <footer class="app-footer">
            © 2026 814小组 · 课程设计展示 | 基于Servlet + Session 安全认证
        </footer>
    </div>

    <!-- 将 Java 后端数据序列化为 JavaScript 对象 -->
    <script>
        const TEAM_DATA = <%= TeamData.toJson() %>;
        const PROJECT_DATA = <%= TeamData.projectToJson() %>;
    </script>
    <script src="script.js"></script>
</body>
</html>
