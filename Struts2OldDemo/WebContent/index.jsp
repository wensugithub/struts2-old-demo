<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Struts 2.3 示例 - 首页</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        h1 { color: #333; }
        ul { list-style-type: none; padding: 0; }
        li { margin: 10px 0; }
        a { text-decoration: none; color: #0066cc; }
        a:hover { text-decoration: underline; }
    </style>
</head>
<body>
    <h1>Struts 2.3 + Java 1.6 + Tomcat 6.0 示例</h1>
    <p>这是一个简单的 Struts 2.3 示例应用程序，运行在 Java 1.6 和 Tomcat 6.0 上。</p>
    
    <h2>功能演示</h2>
    <ul>
        <li><a href="<s:url action='userList'/>">查看用户列表</a></li>
    </ul>
    
    <h2>技术栈</h2>
    <ul>
        <li>Java 1.6.0_3</li>
        <li>Struts 2.3.37</li>
        <li>Tomcat 6.0.24</li>
        <li>Log4j 1.2.17</li>
    </ul>
</body>
</html>