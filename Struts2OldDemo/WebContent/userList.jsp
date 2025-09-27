<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户列表</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        h1 { color: #333; }
        table { border-collapse: collapse; width: 100%; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .message { color: green; margin: 10px 0; }
        .error { color: red; margin: 10px 0; }
        .search-form { margin: 20px 0; }
        .search-input { padding: 5px; width: 200px; }
        .search-button { padding: 5px 10px; }
    </style>
</head>
<body>
    <h1>用户列表</h1>
    <!-- 搜索表单 -->
    <div class="search-form">
        <s:form action="userSearch" method="post">
            <s:textfield name="searchName" label="搜索姓名" cssClass="search-input"/>
            <s:submit value="搜索" cssClass="search-button"/>
        </s:form>
    </div>
    <s:if test="hasActionMessages()">
        <div class="message">
            <s:actionmessage/>
        </div>
    </s:if>
    
    <s:if test="hasActionErrors()">
        <div class="error">
            <s:actionerror/>
        </div>
    </s:if>
    
    <s:if test="userList != null && !userList.isEmpty()">
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>姓名</th>
                    <th>邮箱</th>
                </tr>
            </thead>
            <tbody>
                <s:iterator value="userList">
                    <tr>
                        <td><s:property value="id"/></td>
                        <td><s:property value="name"/></td>
                        <td><s:property value="email"/></td>
                    </tr>
                </s:iterator>
            </tbody>
        </table>
        <p>共 <s:property value="userList.size()"/> 条记录</p>
    </s:if>
    <s:else>
        <p>没有找到用户数据。</p>
    </s:else>
    
    <a href="<s:url action='index'/>" class="back-link">返回首页</a>
</body>
</html>