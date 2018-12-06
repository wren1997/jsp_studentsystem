<%--
  Created by IntelliJ IDEA.
  User: joger
  Date: 18-11-8
  Time: 下午7:33
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
<c:if test="${labs_currentPageIndex==null}">
   <jsp:forward page="/serv_lab?func=viewall&labs_pageIndex=1"/>
</c:if>
<div>
    <div><a href="/serv_lab?func=viewall&labs_pageIndex=1" class="button button-primary button-rounded button-small">刷新</a></div>
    <hr>
    <div class="pagelistinfo">
        一共有${labsCount}条记录;
        共有${labs_pageCount}页;
        当前第${labs_currentPageIndex}页</div>
    <hr>
    <table class="table6_7" style="width: 100%">
        <tr >
            <th>实验室ID</th>
            <th>实验室名称</th>
            <th>实验室管理员ID</th>
            <th>编辑</th>
            <th>删除</th>
        </tr>
        <div>${Hello}</div>
        <c:forEach  var="labs" items="${pagelabs}" >
            <form method="get" action="/serv_lab_edit">
            <tr >
                <td>${labs.lab_id}<input name="lab_id" value="${labs.lab_id}" hidden="hidden"/></td>
                <td>${labs.lab_name}</td>
                <td><input name="lab_manager" value="${labs.lab_manager}" class="inputbox"/></td>
                <td><input name="func" value="edit" hidden="hidden"><input type="submit" value="编辑" class="button button-primary button-rounded button-small"/></td>
                <td><a href="/serv_lab_edit?func=delete&lab_id=${labs.lab_id}" class="button button-primary button-rounded button-small">删除</a> </td>
            </tr>
            </form>
        </c:forEach>
    </table>
    <br/>
    <a href="/serv_lab?func=viewall&labs_pageIndex=1" class="button button-primary button-rounded button-small">首页</a>
    <c:if test="${labs_currentPageIndex!=1}">
        <a href="/serv_lab?func=viewall&labs_pageIndex=${labs_currentPageIndex-1}" class="button button-primary button-rounded button-small" >上一页</a>
    </c:if>
    <c:if test="${labs_currentPageIndex!=labs_pageCount}">
        <a href="/serv_lab?func=viewall&labs_pageIndex=${labs_currentPageIndex+1}" class="button button-primary button-rounded button-small">下一页</a>
    </c:if>
    <a href="/serv_lab?func=viewall&labs_pageIndex=${labs_pageCount}" class="button button-primary button-rounded button-small">尾页</a>
</div>
<hr/>
<div>
    <form method="get" action="/serv_lab_edit">
        <table class="table6_7" width="100%">
            <tr>
                <th colspan="2">添加实验室</th>
            </tr>
            <tr>
                <th>
                    实验室ID
                </th>
                <td>
                    <input type="text" name="func" value="insert" hidden="hidden"/>
                    <input type="text" class="inputbox_table" name="lab_id"/>
                </td>
            </tr>
            <tr>
                <th>
                    实验室名称
                </th>
                <td>
                    <input type="text" class="inputbox_table" name="lab_name"/>
                </td>
            </tr>
            <tr>
                <th>
                    负责人学号
                </th>
                <td>
                    <input type="text" class="inputbox_table" name="lab_manager">
                </td>
            </tr>
            <tr>
                <th colspan="2">
                    <input type="submit" class="button button-primary button-rounded button-small" value="添加"/>
                </th>
            </tr>

        </table>
    </form>
    <br/>
</div>
</body>
</html>
