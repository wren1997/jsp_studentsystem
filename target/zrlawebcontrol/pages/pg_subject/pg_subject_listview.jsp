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
<c:if test="${subject_currentPageIndex==null}">
    <jsp:forward page="/serv_subject?func=viewall&subject_pageIndex=1"/>
</c:if>
<div>
    <div><a href="/serv_subject?func=viewall&subject_pageIndex=1" class="button button-primary button-rounded button-small">刷新</a>
        查找：<input type="text" name="somet" class="inputbox" id="stumanager_search">
        <input type="submit"  id="searchstudent" class="button button-primary button-rounded button-small"  value="搜索" onclick="search()" >
    </div>

    <script>
        function search()
        {
            var stuinfo = document.getElementById("stumanager_search").value;
            location.href="/serv_subject?func=findsubjects&subject_pageIndex=1&somet="+stuinfo;
        }
    </script>
    <hr>
    <div class="pagelistinfo">
        一共有${subjectsCount}条记录;
        共有${subject_pageCount}页;
        当前第${subject_currentPageIndex}页</div>
    <hr>
    <table class="table6_7" style="width: 100%">
        <tr>
            <th>课程编号</th>
            <th>课程名称</th>
            <th>删除</th>
        </tr>
        <div>${Hello}</div>
        <c:forEach  var="subjects" items="${pagesubjects}" >
            <form method="get" action="/serv_subject_edit">
                <tr bgcolor="#ffc0cb">
                    <td>${subjects.sub_id}<input name="sub_id" value="${subjects.sub_id}" hidden="hidden"/></td>
                    <td>${subjects.sub_name}</td>
                    <td><a href="/serv_subject_edit?func=delete&sub_id=${subjects.sub_id}" class="button button-primary button-rounded button-small">删除</a> </td>
                </tr>
            </form>
        </c:forEach>
    </table>
    <br/>
    <a href="/serv_subject?func=viewall&subject_pageIndex=1" class="button button-primary button-rounded button-small">首页</a>
    <c:if test="${subject_currentPageIndex!=1}">
        <a href="/serv_subject?func=viewall&subject_pageIndex=${subject_currentPageIndex-1}" class="button button-primary button-rounded button-small">上一页</a>
    </c:if>
    <c:if test="${subject_currentPageIndex!=subject_pageCount}">
        <a href="/serv_subject?func=viewall&subject_pageIndex=${subject_currentPageIndex+1}" class="button button-primary button-rounded button-small">下一页</a>
    </c:if>
    <a href="/serv_subject?func=viewall&subject_pageIndex=${subject_pageCount}" class="button button-primary button-rounded button-small">尾页</a>
</div>
<br/>
<div>
    <form method="get" action="/serv_subject_edit">
        <table class="table6_7" width="100%">
            <tr>
                <th colspan="2">添加课程记录</th>
            </tr>
            <tr>
                <th>课程编号</th>
                <td>
                    <input type="text" name="func" value="insert" hidden="hidden"/>
                    <input type="text" name="sub_id" class="inputbox_table"/>
                </td>
            </tr>
            <tr>
                <th>
                    课程名称
                </th>
                <td>
                    <input type="text" class="inputbox_table" name="sub_name"/>
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

