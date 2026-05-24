// ======================= 数据来源 =======================
// TEAM_DATA 和 PROJECT_DATA 由 dashboard.jsp 通过
// <%= TeamData.toJson() %> 注入为 JavaScript 全局变量

// ======================= 主面板逻辑 (dashboard) =======================
// 注意：登录校验已由服务端 AuthFilter + Session 完成
// 无需前端检查登录状态

(function () {
    // 确保只在 dashboard 页面执行
    if (!document.getElementById('mainContent')) return;

    const contentContainer = document.getElementById('mainContent');
    const tabButtons = document.querySelectorAll('.tab-btn');

    // ======================= 渲染函数 =======================
    function renderOverview() {
        contentContainer.innerHTML = `
            <div>
                <h2 style="font-size: 1.8rem; margin-bottom: 0.5rem;">🏢 ${escapeHtml(TEAM_DATA.name)}</h2>
                <p style="color: #2c3e66; background: #f0f9ff; padding: 1rem; border-radius: 1rem; line-height:1.5;">${escapeHtml(TEAM_DATA.description)}</p>
                <div style="margin: 1rem 0; padding: 1rem; background: #f1f5f9; border-radius: 1rem;">
                    <strong>📅 成立时间：</strong> ${escapeHtml(TEAM_DATA.founded)}<br>
                    <strong>👥 团队规模：</strong> ${TEAM_DATA.members.length}人（开发+产品+设计）
                </div>
                <h3>✨ 团队文化</h3>
                <p>每周站立会议，双周迭代评审，代码规范与文档沉淀，打造高质量课程产出。</p>
            </div>
        `;
    }

    function renderMembers() {
        const membersHtml = TEAM_DATA.members.map(m => `
            <div class="member-card">
                <div class="member-name">${escapeHtml(m.name)}</div>
                <div class="member-role">${escapeHtml(m.role)}</div>
                <div class="member-bio">📌 ${escapeHtml(m.bio)}</div>
            </div>
        `).join('');
        contentContainer.innerHTML = `
            <h2 style="margin-bottom: 1rem;">👥 团队成员简介</h2>
            <div class="members-grid">
                ${membersHtml}
            </div>
            <p style="margin-top: 1rem; font-size:0.85rem;">每位成员深度协作，定期交叉review，确保项目顺利推进。</p>
        `;
    }

    function renderProject() {
        const phasesHtml = PROJECT_DATA.phases.map(phase => `
            <div class="phase-card">
                <div class="phase-title">
                    <span>📌 ${escapeHtml(phase.name)}</span>
                    <span class="status-badge">${escapeHtml(phase.status)}</span>
                </div>
                <div class="progress-bar">
                    <div class="progress-fill" style="width: ${phase.progress}%;"></div>
                </div>
                <div style="font-size:0.85rem;">${escapeHtml(phase.details)} · 进度 ${phase.progress}%</div>
            </div>
        `).join('');
        contentContainer.innerHTML = `
            <h2 style="margin-bottom: 0.5rem;">📅 ${escapeHtml(PROJECT_DATA.title)}</h2>
            <div style="background:#eef2ff; padding:0.7rem 1rem; border-radius:1rem; margin-bottom:1.5rem;">
                🎯 下一个里程碑：${escapeHtml(PROJECT_DATA.nextMilestone)}
            </div>
            <div class="phase-list">
                ${phasesHtml}
            </div>
            <div style="margin-top: 1.5rem; background:#f9fafb; padding:1rem; border-radius:1rem;">
                🚀 整体规划：第14周进入集成测试，期末进行全功能Demo展示。
            </div>
        `;
    }

    // ======================= Tab 切换（全局函数，由 onclick 调用） =======================
    window.switchTab = function (tabId) {
        if (tabId === 'overview') renderOverview();
        else if (tabId === 'members') renderMembers();
        else if (tabId === 'project') renderProject();

        // 更新按钮激活状态
        tabButtons.forEach(btn => {
            if (btn.getAttribute('onclick') && btn.getAttribute('onclick').includes(tabId)) {
                btn.classList.add('active');
            } else {
                btn.classList.remove('active');
            }
        });
    };

    // ======================= 初始化 =======================
    window.switchTab('overview');
})();

// ======================= 辅助函数 =======================
function escapeHtml(str) {
    if (!str) return '';
    return String(str)
        .replace(/&/g, '&amp;')
        .replace(/</g, '&lt;')
        .replace(/>/g, '&gt;')
        .replace(/"/g, '&quot;')
        .replace(/'/g, '&#039;');
}
