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
<c:if test="${currentPageIndex==null}">
   <jsp:forward page="/serv_score?func=viewall&pageIndex=1"/>
</c:if>
<div>
    <div><a href="/serv_score?func=viewall&pageIndex=1">刷新</a></div>
    <div>
        查找：<input type="text" name="somet" class="functypess" id="stumanager_search">
        <input type="submit" class="searchstudents" id="searchstudent"  value="搜索" onclick="search()" >
    </div>

    <script>
        function search()
        {
            var stuinfo = document.getElementById("stumanager_search").value;
            location.href="/serv_score?func=findscores&pageIndex=1&somet="+stuinfo;
        }
    </script>
    <div>
        一共有${scoreCount}条记录;
        共有${pageCount}页;
        当前第${currentPageIndex}页</div>
    <hr>
    <table>
        <tr bgcolor="#044599" color="#ffffff">
            <td>成绩ID</td>
            <td>学生学号</td>
            <td>科目名称</td>
            <td>科目成绩</td>
            <td>编辑</td>
            <td>删除</td>
        </tr>
        <div>${Hello}</div>
        <c:forEach  var="scores" items="${pagescores}" >
            <form method="get" action="/serv_score_edit">
            <tr bgcolor="#ffc0cb">
                <td>${scores.sco_id}<input name="score_id" value="${scores.sco_id}" hidden="hidden"/></td>
                <td>${scores.sco_stu_id}</td>
                <td>${scores.sub_name}</td>
                <td><input name="score_value" value="${scores.sco_value}"/></td>
                <td><input name="func" value="edit" hidden="hidden"><input type="submit" value="编辑"/></td>
                <td><a href="/serv_score_edit?func=delete&score_id=${scores.sco_id}">删除</a> </td>
            </tr>
            </form>
        </c:forEach>
    </table>
    <a href="/serv_score?func=viewall&pageIndex=1" >首页</a>
    <c:if test="${currentPageIndex!=1}">
        <a href="/serv_score?func=viewall&pageIndex=${currentPageIndex-1}" >上一页</a>
    </c:if>
    <c:if test="${currentPageIndex!=pageCount}">
        <a href="/serv_score?func=viewall&pageIndex=${currentPageIndex+1}" >下一页</a>
    </c:if>
    <a href="/serv_score?func=viewall&pageIndex=${pageCount}" >尾页</a>
</div>
<div>
    <form method="get" action="/serv_score_edit">
        <div>添加成绩记录：</div>
        <input type="text" name="func" value="insert" hidden="hidden"/>
        <div>学生ID：<input type="text" name="stu_id"/></div>
        <div>
            科目：
            <input type="text" name="scoindex" value="${scoreCount+1}" hidden="hidden"/>
            <select name="subjectid" id="select_subject">
            <c:forEach var="scores" items="${subjectsitems}">
                    <option value="${scores.sub_id}">${scores.sub_id}:${scores.sub_name}</option>

            </c:forEach>
        </select>
            <div>
                成绩：
                <input type="text" name="scorevalue"/>
            </div>
            <div>
                <input type="submit" value="添加"/>
            </div>
        </div>
    </form>
</div>
</body>
</html>
