<%--
  Created by IntelliJ IDEA.
  User: joger
  Date: 18-11-1
  Time: 下午8:11
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
<body >
<div><button onclick="history.go(-1)" class="button button-primary button-rounded button-small">返回</button></div>
<hr>

<!--
<form name="uploadForm" method="post" id="upload_img_form" enctype="multipart/form-data" action="/serv_student_upload_view">
    <div>上传图片</div>
    <input type="text" name="studentvalue" value="${student}"
    <input type="text" name="userid" value="${student.stu_id}"/>
    <div>Username:<input type="text" name="username" size="30" value="Tom"/></div>
    <div>Img:<input type="file" id="upload_file_inputf" name="file1" size="30"></div>
    <div><input type="submit"  name="submit" value="upload"/> </div>
</form>
-->
<div>
    <form method="post" action="/serv_student_upload_view" enctype="multipart/form-data">
        <input name="func" value="${donext}" hidden="hidden" />
        <input name="isadmin" value="${isadmin}" hidden="hidden"/>
    <table class="table6_7">
        <tr>
            <th colspan="5">
                <c:if test="${function=='edit'}">
                    修改学生个人信息
                </c:if>
                <c:if test="${function=='insert'}">
                    添加学生个人信息
                </c:if>
            </th>

        </tr>
        <tr>
            <c:if test="${function=='edit'}">
                <th>学号</th><td><input type="text" name="student_id" value="${student.stu_id}" class="inputbox_table" readonly="readonly"/></td>
            </c:if>
            <c:if test="${function=='insert'}">
                <th>学号</th><td><input type="text" name="student_id" class="inputbox_table" value="${student.stu_id}" /></td>
            </c:if>
            <th>姓名</th><td><input type="text" name="student_name" class="inputbox_table" value="${student.stu_name}"/></td>
            <td rowspan="5">
                <img src="${student.stu_img}" height="100px" width="100px"/>
                <br>
                <input type="text" name="student_img" value="${student.stu_img}"/>
                <br>
                <input type="file" id="upload_file_input" name="file1" size="30"/>
            </td>
        </tr>
        <tr>
            <th>性别</th><td><input type="text" name="student_sex" class="inputbox_table" value="${student.stu_sex}"/></td>
            <th>民族</th><td><input type="text" name="student_nation" class="inputbox_table" value="${student.stu_nation}"/></td>
        </tr>
        <tr>
            <th>生日</th><td><input type="text" name="student_birth" class="inputbox_table" value="${student.stu_birth}"/></td>
            <th>联系电话</th><td><input type="text" name="student_phone" class="inputbox_table" value="${student.stu_phone}"/></td>
        </tr>
        <tr>
            <th>实验室编号</th>
            <td>
                <!--<input type="text" name="student_labid" value="${student.stu_labid}"/>-->
                <select name="student_labid">
                    <c:forEach var="labs" items="${labstudent}">
                        <c:if test="${labs.lab_id == student.stu_labid}">
                            <option value="${labs.lab_id}" selected="selected">${labs.lab_name}</option>
                        </c:if>
                        <c:if test="${labs.lab_id != student.stu_labid}">
                            <option value="${labs.lab_id}" >${labs.lab_name}</option>
                        </c:if>

                    </c:forEach>
                </select>

            </td>
            <th>宿舍</th><td><input type="text" name="student_room" class="inputbox_table" value="${student.stu_room}"/></td>
        </tr>
        <tr>
            <th>学院</th><td><input type="text" name="student_college" class="inputbox_table" value="${student.stu_college}"/></td>
            <th>专业</th><td><input type="text" name="student_major" class="inputbox_table" value="${student.stu_major}"/></td>
        </tr>
        <tr>
            <th>邮箱</th><td colspan="4"><input type="text" class="inputbox_table" name="student_email" value="${student.stu_email}"/></td>
        </tr>
        <tr>
            <th>密码</th><td colspan="4"><input type="text" class="inputbox_table" name="student_pwd" value="${student.stu_pwd}"/></td>
        </tr>
        <tr>
            <th colspan="5"><input type="submit" class="button button-primary button-rounded button-small" value="提 交"/></th>
        </tr>
    </table>
    </form>
</div>
</body>
</html>
