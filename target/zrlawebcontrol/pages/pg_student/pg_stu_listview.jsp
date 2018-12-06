

<%--
  Created by IntelliJ IDEA.
  User: joger
  Date: 18-10-26
  Time: 下午4:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>


<html>
<head>
    <link href="../../css/mystyle.css" rel="stylesheet" type="text/css" />
    <link href="../../css/buttons.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<c:if test="${currentPageIndex==null}">
    <jsp:forward page="/serv_student?func=viewall&pageIndex=1"/>
</c:if>
    <div>
            <div><a href="/serv_student?func=viewall&pageIndex=1" class="button button-primary button-rounded button-small">刷新</a>
            <a href="/serv_student_edit?func=insert" class="button button-primary button-rounded button-small">添加学生</a>

                查找：<input type="text" name="somet" class="inputbox" id="stumanager_search">
                <input type="submit" class="button button-primary button-rounded button-small" id="searchstudent"  value="搜索" onclick="search()" >
            </div>

        <script>
            function search()
            {
                var stuinfo = document.getElementById("stumanager_search").value;
                location.href="/serv_student?func=findStudents&pageIndex=1&somet="+stuinfo;
            }
        </script>
    <div class="pagelistinfo"><hr>
    一共有${studentCount}条记录;
    共有${pageCount}页;
    当前第${currentPageIndex}页</div>
    <hr>
    <table class="table6_7" style="width: 100%">
        <tr >
            <th>学号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>学院</th>
            <th>专业</th>
            <th>所属实验室</th>
            <th>密码</th>
            <th>编辑</th>
            <th>删除</th>
        </tr>
        <div>${Hello}</div>
        <c:forEach  var="student" items="${pageStudents}" >
            <tr>
                <td><a target="view_window" href="/serv_publicshow?stuid=${student.stu_id}&stu_pwd=${student.stu_pwd}" class="button button-primary button-rounded button-small" >${student.stu_id}</a> </td>
                <td>${student.stu_name}</td>
                <td>${student.stu_sex}</td>
                <td>${student.stu_college}</td>
                <td>${student.stu_major}</td>
                <c:if test="${student.stu_labid==1}">
                    <td>双足实验室</td>
                </c:if>
                <c:if test="${student.stu_labid==2}">
                    <td>空中实验室</td>
                </c:if>
                <c:if test="${student.stu_labid==3}">
                    <td>擂台实验室</td>
                </c:if>
                <c:if test="${student.stu_labid==4}">
                    <td>服务实验室</td>
                </c:if>
                <c:if test="${student.stu_labid==5}">
                    <td>仿真实验室</td>
                </c:if>
                <td>${student.stu_pwd}</td>
                <td><a href="/serv_student_edit?func=edit&stu_id=${student.stu_id}&isadmin=true" class="button button-primary button-rounded button-small">编辑</a></td>
                <td><a href="/serv_student_edit?func=delete&stu_id=${student.stu_id}&isadmin=true" class="button button-primary button-rounded button-small">删除</a> </td>
            </tr>
        </c:forEach>
    </table>
    <br/>

    <a href="/serv_student?func=viewall&pageIndex=1" class="button button-primary button-rounded button-small" >首页</a>
    <c:if test="${currentPageIndex!=1}">
        <a href="/serv_student?func=viewall&pageIndex=${currentPageIndex-1}" class="button button-primary button-rounded button-small">上一页</a>
    </c:if>
    <c:if test="${currentPageIndex!=pageCount}">
        <a href="/serv_student?func=viewall&pageIndex=${currentPageIndex+1}" class="button button-primary button-rounded button-small">下一页</a>
    </c:if>
    <a href="/serv_student?func=viewall&pageIndex=${pageCount}" class="button button-primary button-rounded button-small">尾页</a>
</div>
</body>
</html>
