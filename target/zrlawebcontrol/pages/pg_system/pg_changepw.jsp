<%--
  Created by IntelliJ IDEA.
  User: joger
  Date: 18-11-29
  Time: 下午7:20
  To change this template use File | Settings | File Templates.
--%><%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>Title</title>
    <link href="../../css/mystyle.css" rel="stylesheet" type="text/css" />
    <link href="../../css/buttons.css" rel="stylesheet" type="text/css"/>
</head>
<body>


<form method="get" action="/serv_manager_edit">
    <div>您好，您的用户名是：${username}</div>

        <div>
            <input type="text" value="${username}" hidden="hidden" name="man_un"/>
            输入新密码：<input type="password" name="man_pw"/>
            <input type="text" name="func" value="changepwd" hidden="hidden"/>

        </div>
        <div>
            <input type="submit" value="提交"/>
        </div>
</form>

</body>
</html>
