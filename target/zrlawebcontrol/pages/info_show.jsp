<%--
  Created by IntelliJ IDEA.
  User: joger
  Date: 18-11-8
  Time: 下午7:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>Title</title>
    <link href="../../css/mystyle.css" rel="stylesheet" type="text/css" />
    <link href="../../css/buttons.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <div class="middle_infopage">
        <div class="middle_infopage_title">${result}</div>
        <div><a href="${gonexturl}" class="button button-primary button-rounded button-small">返回</a></div>
    </div>

</body>
</html>
