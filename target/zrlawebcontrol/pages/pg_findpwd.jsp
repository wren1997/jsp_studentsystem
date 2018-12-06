<%--
  Created by IntelliJ IDEA.
  User: joger
  Date: 18-11-22
  Time: 下午9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>找回密码</title>
    <link href="../css/mystyle.css" rel="stylesheet" type="text/css" />
    <link href="../css/buttons.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="middle_infopage">
    <form action="/serv_email">
        <table class="table6_7" width="100%">
            <tr>
                <th colspan="2">
                    找回密码
                </th>
            </tr>
            <tr>
                <th>用户名：</th>
                <td>
                    <input type="text" name="stu_id" class="inputbox_table"/>
                </td>
            </tr>
            <tr>
                <th colspan="2"><input type="submit" class="button button-primary button-rounded button-small"/> </th>
            </tr>
        </table>
    </form>
</div>

</body>
</html>
