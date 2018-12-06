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
    <div><a href="/serv_score?func=viewall&pageIndex=1" class="button button-primary button-rounded button-small">刷新</a>

        查找：<input type="text" name="somet" class="inputbox" id="stumanager_search">
        <input type="submit" class="button button-primary button-rounded button-small" id="searchstudent"  value="搜索" onclick="search()" >
    </div>

    <script>
        function search()
        {
            var stuinfo = document.getElementById("stumanager_search").value;
            location.href="/serv_score?func=findscores&pageIndex=1&somet="+stuinfo;
        }
    </script>
    <hr>
    <div class="pagelistinfo">
        一共有${scoreCount}条记录;
        共有${pageCount}页;
        当前第${currentPageIndex}页</div>
    <hr>
    <table class="table6_7" style="width: 100%">
        <tr >
            <th>成绩ID</th>
            <th>学生学号</th>
            <th>科目名称</th>
            <th>科目成绩</th>
            <th>编辑</th>
            <th>删除</th>
        </tr>
        <div>${Hello}</div>
        <c:forEach  var="scores" items="${pagescores}" >
            <form method="get" action="/serv_score_edit">
            <tr bgcolor="#ffc0cb">
                <td>${scores.sco_id}<input name="score_id" value="${scores.sco_id}" hidden="hidden"/></td>
                <td>${scores.sco_stu_id}</td>
                <td>${scores.sub_name}</td>
                <td><input name="score_value" class="inputbox" value="${scores.sco_value}"/></td>
                <td><input name="func" value="edit" hidden="hidden"><input type="submit" value="编辑" class="button button-primary button-rounded button-small"/></td>
                <td><a href="/serv_score_edit?func=delete&score_id=${scores.sco_id}" class="button button-primary button-rounded button-small">删除</a> </td>
            </tr>
            </form>
        </c:forEach>
    </table>
    <br/>
    <a href="/serv_score?func=viewall&pageIndex=1" class="button button-primary button-rounded button-small">首页</a>
    <c:if test="${currentPageIndex!=1}">
        <a href="/serv_score?func=viewall&pageIndex=${currentPageIndex-1}"class="button button-primary button-rounded button-small" >上一页</a>
    </c:if>
    <c:if test="${currentPageIndex!=pageCount}">
        <a href="/serv_score?func=viewall&pageIndex=${currentPageIndex+1}" class="button button-primary button-rounded button-small">下一页</a>
    </c:if>
    <a href="/serv_score?func=viewall&pageIndex=${pageCount}" class="button button-primary button-rounded button-small">尾页</a>
</div>
<div>
    <hr>
    <form method="get" action="/serv_score_edit">
        <table class="table6_7" width="100%">
            <tr>
                <th colspan="2">
                    添加成绩记录
                </th>
            </tr>
            <tr>
                <th>学生ID</th>
                <td>
                    <input type="text" name="func" value="insert" hidden="hidden"/>
                    <input type="text" name="stu_id" class="inputbox_table"/>
                </td>
            </tr>
            <tr>
                <th>
                    成绩ID
                </th>
                <td>
                    <input type="text" name="scoindex" class="inputbox_table" value="${scoreCount+1}" "/>
                </td>
            </tr>
            <tr>
                <th>
                    科目
                </th>
                <td>

                    <select name="subjectid" id="select_subject" class="inputbox_table">
                        <c:forEach var="scores" items="${subjectsitems}">
                            <option value="${scores.sub_id}">${scores.sub_id}:${scores.sub_name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <th>
                    成绩
                </th>
                <td>
                    <input type="text" class="inputbox_table" name="scorevalue"/>
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
