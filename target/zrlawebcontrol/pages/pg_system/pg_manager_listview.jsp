<%--
  Created by IntelliJ IDEA.
  User: joger
  Date: 18-11-29
  Time: 下午7:23
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
<c:if test="${managers_currentPageIndex==null}">
    <jsp:forward page="/serv_manager?func=viewall&managers_pageIndex=1"/>
</c:if>
<div>
    <div><a href="/serv_manager?func=viewall&managers_pageIndex=1" class="button button-primary button-rounded button-small">刷新</a>
        查找：<input type="text" name="somet" class="inputbox" id="stumanager_search">
        <input type="submit" class="button button-primary button-rounded button-small" id="searchstudent"  value="搜索" onclick="search()" >
    </div>

    <script>
        function search()
        {
            var stuinfo = document.getElementById("stumanager_search").value;
            location.href="/serv_manager?func=findmanager&managers_pageIndex=1&somet="+stuinfo;
        }
    </script>
    <hr/>
    <div class="pagelistinfo">
        一共有${managersCount}条记录;
        共有${managers_pageCount}页;
        当前第${managers_currentPageIndex}页</div>
    <hr>
    <table class="table6_7" style="width: 100%">
        <tr>
            <th>管理员编号</th>
            <th>登录账号</th>
            <th>密码</th>
            <th>编辑</th>
            <th>删除</th>
        </tr>
        <div>${Hello}</div>
        <c:forEach  var="managers" items="${pagemanagers}" >
            <form method="get" action="/serv_manager_edit">
                <tr bgcolor="#ffc0cb">
                    <td>${managers.man_id}<input name="man_id" value="${managers.man_id}" hidden="hidden"/></td>
                    <td>${managers.man_un}</td>
                    <td><input type="text" value="${managers.man_pw}" class="inputbox" name="man_pw"/></td>
                    <td><input type="text" value="edit" name="func" hidden="hidden">
                    <input type="submit" class="button button-primary button-rounded button-small" value="修改"/>
                    </td>
                    <td><a href="/serv_manager_edit?func=delete&man_id=${managers.man_id}" class="button button-primary button-rounded button-small">删除</a> </td>
                </tr>
            </form>
        </c:forEach>
    </table>
    <br/>
    <a href="/serv_manager?func=viewall&managers_pageIndex=1" class="button button-primary button-rounded button-small">首页</a>
    <c:if test="${managers_currentPageIndex!=1}">
        <a href="/serv_manager?func=viewall&managers_pageIndex=${managers_currentPageIndex-1}" class="button button-primary button-rounded button-small">上一页</a>
    </c:if>
    <c:if test="${managers_currentPageIndex!=managers_pageCount}">
        <a href="/serv_manager?func=viewall&managers_pageIndex=${managers_currentPageIndex+1}" class="button button-primary button-rounded button-small" >下一页</a>
    </c:if>
    <a href="/serv_manager?func=viewall&managers_pageIndex=${managers_pageCount}" class="button button-primary button-rounded button-small">尾页</a>
</div>
<br/>
<div>
    <form method="get" action="/serv_manager_edit">
        <table class="table6_7" width="100%">
            <tr>
                <th colspan="2">
                    添加管理员
                    <input type="text" name="func" value="insert" hidden="hidden"/>
                </th>
            </tr>
            <tr>
                <th>管理员编号</th>
                <td>
                    <input type="text" class="inputbox_table" name="man_id"/>
                </td>
            </tr>
            <tr>
                <th>
                    用户名
                </th>
                <td>
                    <input type="text" class="inputbox_table" name="man_un"/>
                </td>
            </tr>
            <tr>
                <th>密码</th>
                <td>
                    <input type="password" class="inputbox_table" name="man_pw"/>
                </td>
            </tr>
            <tr>
                <th colspan="2">
                    <input type="submit" class="button button-primary button-rounded button-small" value="添加"/>
                </th>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
