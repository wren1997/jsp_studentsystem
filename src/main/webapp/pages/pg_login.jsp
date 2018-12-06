<%--
  Created by IntelliJ IDEA.
  User: joger
  Date: 18-11-1
  Time: 下午6:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录系统</title>
    <link href="../style/authority/login_css.css" rel="stylesheet" type="text/css" />
    <link href="../css/style.css" rel="stylesheet" type="text/css" media="all" />
    <script type="text/javascript" src="../scripts/jquery/jquery-1.7.1.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script language="javascript">
        function myReload() {
            document.getElementById("randomcodereselect").src = document
                    .getElementById("randomcodereselect").src
                + "?nocache=" + new Date().getTime();
        }
    </script>
</head>
<body>


<div class="message warning">
    <div class="inset">
        <div class="login-head">
            <h1>实验室管理系统</h1>
            <div class="alert-close"> </div>
        </div>
        <form id="submitForm" action="/serv_login" method="get">
            <li>
                <input type="text" class="text" value="Username" name="userEntity.userCode" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Username';}"><a href="#" class=" icon user"></a>
            </li>
            <div class="clear"> </div>
            <li>
                <input type="password" value="Password" name="userEntity.password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Password';}"> <a href="#" class="icon lock"></a>
            </li>
            <li>
                <div style="height: 40px;">
                    验证码：<input type="text" name="randomcodecheck"><img src="/serv_randomcode" id="randomcodereselect" height="20px"/>
                    <a href="" onclick="myReload()">换一个</a>
                </div>
            </li>
            <div class="clear"> </div>
            <div class="submit">
                <input type="submit" onclick="myFunction()" value="登录" >
                <h4><a href="pg_findpwd.jsp">忘记密码</a></h4>
                <div class="clear">  </div>
            </div>

        </form>
    </div>
</div>
</div>
<div class="clear"> </div>
<!--- footer --->
<div class="footer">
    <p>王政乔 20160520126 &copy; 2016.</p>
</div>

</body>
</html>
