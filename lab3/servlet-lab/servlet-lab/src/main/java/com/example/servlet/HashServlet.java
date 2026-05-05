package com.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hash")
public class HashServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 设置响应内容类型为HTML
        response.setContentType("text/html;charset=UTF-8");

        // 获取用户输入的文本
        String inputText = request.getParameter("inputText");
        if (inputText == null) {
            inputText = "";
        }

        // 计算MD5散列值
        String md5Hash = calculateMD5(inputText);

        // 输出HTML页面
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>MD5散列值 - 结果</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 30px; }");
        out.println(".container { max-width: 800px; margin: 0 auto; }");
        out.println(".result-box { background-color: #f5f5f5; padding: 15px; ");
        out.println("border-radius: 5px; border-left: 4px solid #007bff; margin: 20px 0; }");
        out.println(".hash-value { font-family: monospace; font-size: 18px; ");
        out.println("word-break: break-all; background: #fff; padding: 10px; ");
        out.println("border: 1px solid #ddd; border-radius: 3px; }");
        out.println(".original-text { background-color: #e9ecef; padding: 10px; ");
        out.println("border-radius: 5px; margin: 10px 0; }");
        out.println("h1, h2 { color: #333; }");
        out.println(".back-btn { margin-top: 20px; padding: 8px 15px; ");
        out.println("background-color: #007bff; color: white; ");
        out.println("text-decoration: none; border-radius: 5px; display: inline-block; }");
        out.println(".note { color: #666; font-style: italic; margin-top: 15px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h1>MD5 散列值计算</h1>");

        out.println("<div class='result-box'>");
        out.println("<h2>📝 原始文本</h2>");
        out.println("<div class='original-text'>" + escapeHtml(inputText) + "</div>");

        out.println("<h2>🔐 MD5 散列值</h2>");
        out.println("<div class='hash-value'>" + md5Hash + "</div>");
        out.println("</div>");

        out.println("<div class='note'>");
        out.println("💡 提示：MD5是128位散列值，通常表示为32个十六进制字符。<br>");
        out.println("即使输入只改动一个字符，散列值也会完全不同！");
        out.println("</div>");

        out.println("<a href='index.html' class='back-btn'>← 返回首页</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * 计算字符串的MD5散列值
     */
    private String calculateMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes());

            // 将字节数组转换为十六进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "MD5算法不可用";
        }
    }

    /**
     * 转义HTML特殊字符防止XSS攻击
     */
    private String escapeHtml(String text) {
        if (text == null) return "";
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;")
                .replace("\n", "<br>");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}