
package com.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/yanghui")
public class YanghuiTriangleServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 设置响应内容类型为HTML
        response.setContentType("text/html;charset=UTF-8");

        // 获取用户输入的阶数
        String orderStr = request.getParameter("order");
        int n = 10; // 默认10阶

        try {
            if (orderStr != null && !orderStr.isEmpty()) {
                n = Integer.parseInt(orderStr);
                if (n < 1) n = 1;
                if (n > 20) n = 20; // 限制最大20阶防止页面过大
            }
        } catch (NumberFormatException e) {
            n = 10;
        }

        // 生成杨辉三角
        int[][] triangle = generateYanghuiTriangle(n);

        // 输出HTML页面
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>杨辉三角 - 结果</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 30px; }");
        out.println("table { border-collapse: collapse; margin: 20px 0; }");
        out.println("td { padding: 5px 10px; text-align: center; border: 1px solid #ccc; }");
        out.println("h1 { color: #333; }");
        out.println(".back-btn { margin-top: 20px; padding: 8px 15px; ");
        out.println("background-color: #007bff; color: white; ");
        out.println("text-decoration: none; border-radius: 5px; display: inline-block; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>杨辉三角 (" + n + "阶)</h1>");

        // 用表格展示杨辉三角
        out.println("<table>");
        for (int i = 0; i < n; i++) {
            out.println("<tr>");
            // 添加缩进（用空白单元格）
            for (int j = 0; j < n - i - 1; j++) {
                out.println("<td style='border: none;'></td>");
            }
            // 输出数字
            for (int j = 0; j <= i; j++) {
                out.println("<td>" + triangle[i][j] + "</td>");
            }
            out.println("</tr>");
        }
        out.println("</table>");

        out.println("<a href='index.html' class='back-btn'>返回输入</a>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * 生成杨辉三角
     */
    private int[][] generateYanghuiTriangle(int n) {
        int[][] triangle = new int[n][n];
        for (int i = 0; i < n; i++) {
            triangle[i][0] = 1;
            triangle[i][i] = 1;
            for (int j = 1; j < i; j++) {
                triangle[i][j] = triangle[i-1][j-1] + triangle[i-1][j];
            }
        }
        return triangle;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}