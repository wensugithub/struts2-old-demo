<%@ page contentType="text/html; charset=UTF-8" language="java" 
         isErrorPage="true" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>错误页面</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        h1 { color: #d32f2f; }
        .error-details { background-color: #f9f9f9; padding: 15px; border-left: 4px solid #d32f2f; margin: 20px 0; }
        pre { white-space: pre-wrap; word-wrap: break-word; }
    </style>
</head>
<body>
    <h1>发生错误</h1>
    
    <s:if test="hasActionErrors()">
        <div class="error-details">
            <s:actionerror/>
        </div>
    </s:if>
    
    <% if (exception != null) { %>
        <div class="error-details">
            <p><strong>错误信息:</strong> <%= exception.getMessage() %></p>
            <p><strong>堆栈跟踪:</strong></p>
            <pre><% exception.printStackTrace(new java.io.PrintWriter(out)); %></pre>
        </div>
    <% } %>
    
    <a href="<s:url action='index'/>">返回首页</a>
</body>
</html>