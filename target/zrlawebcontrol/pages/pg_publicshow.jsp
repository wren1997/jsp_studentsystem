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
    <title>机器人实验室-${stuinfo.stu_name}</title>

    <style>

        .table6_7 table {
            width:100%;
            min-width: 1000px;
            margin:15px 0;
            border: 0.2px solid #00A5FF;
        }
        .table6_7 th {
            background-color:#00A5FF;
            color:#FFFFFF
        }
        .table6_7,.table6_7 th,.table6_7 td {
            font-size:0.95em;
            text-align:center;
            padding:4px;
            border-collapse:collapse;
        }
        .table6_7 th,.table6_7 td {
            border: 1px solid #2087fe;
            border-width:1px 0 1px 0
        }
        .table6_7 tr {
            border: 1px solid #ffffff;
        }
        .table6_7 tr:nth-child(odd){
            background-color:#aae1fe;
        }
        .table6_7 tr:nth-child(even){
            background-color:#ffffff;
        }


        .inputbox{
            border:0px;
            background-color:#f3f9f1;
            height:27px;
        }

        .inputbox_table{
            border:0px;
            background-color:#f3f9f1;
            height:27px;
            width: 100%;
        }

        .pagelistinfo{
            font-size:small;
        }

        .public_table_head {
            background-color: #4472C4;
            color: white;
            text-align: center;
            font-size: large;
            min-width: 800px;
        }

        .public_table_item {
            background-color: #4472C4;
            color: white;
            text-align: center;
            font-size: medium;
            width: 120px;
        }

        .public_table_value_short {
            background-color: white;
            color: black;
            text-align: left;
            min-width: 200px;
        }

        .public_table_value_img {
            background-color: white;
            border: 0.1px solid #4472C4;
        }

        .public_table_tr{
            border: 0.1px solid #4472C4;
        }

        .public_table {
            background-color: #4472C4;
            border: 0.1px solid #4472C4;
            min-width: 800px;
        }

        .public_table_textbox {
            background-color: white;
            height: 100%;
            min-width: 60%;
            border: 0;
        }

        .middle_main {
            text-align: center; /*让div内部文字居中*/
            width: 800px;
            margin: auto;
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
        }

        .middle_infopage{
            text-align: center; /*让div内部文字居中*/
            width: 500px;
            height: 130px;
            margin: auto;
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background-color: #4472C4;
            color: white;
        }

        .middle_infopage_title{
            text-align: center;
            font-size: large;
            margin-bottom: 20px;
            margin-top: 20px;
            border-radius: 20px;
        }

        .button {
            color: #666;
            background-color: #EEE;
            border-color: #EEE;
            font-weight: 300;
            font-size: 16px;
            font-family: "Helvetica Neue Light", "Helvetica Neue", Helvetica, Arial, "Lucida Grande", sans-serif;
            text-decoration: none;
            text-align: center;
            line-height: 40px;
            height: 40px;
            padding: 0 40px;
            margin: 0;
            display: inline-block;
            appearance: none;
            cursor: pointer;
            border: none;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
            -webkit-transition-property: all;
            transition-property: all;
            -webkit-transition-duration: .3s;
            transition-duration: .3s;
            /*
            * Disabled State
            *
            * The disabled state uses the class .disabled, is-disabled,
            * and the form attribute disabled="disabled".
            * The use of !important is only added because this is a state
            * that must be applied to all buttons when in a disabled state.
            */ }
        .button:visited {
            color: #666; }
        .button:hover, .button:focus {
            background-color: #f6f6f6;
            text-decoration: none;
            outline: none; }
        .button:active, .button.active, .button.is-active {
            text-shadow: 0 1px 0 rgba(255, 255, 255, 0.3);
            text-decoration: none;
            background-color: #eeeeee;
            border-color: #cfcfcf;
            color: #d4d4d4;
            -webkit-transition-duration: 0s;
            transition-duration: 0s;
            -webkit-box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.2);
            box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.2); }
        .button.disabled, .button.is-disabled, .button:disabled {
            top: 0 !important;
            background: #EEE !important;
            border: 1px solid #DDD !important;
            text-shadow: 0 1px 1px white !important;
            color: #CCC !important;
            cursor: default !important;
            appearance: none !important;
            -webkit-box-shadow: none !important;
            box-shadow: none !important;
            opacity: .8 !important; }
        .button-primary,
        .button-primary-flat {
            background-color: #1B9AF7;
            border-color: #1B9AF7;
            color: #FFF; }
        .button-primary:visited,
        .button-primary-flat:visited {
            color: #FFF; }
        .button-primary:hover, .button-primary:focus,
        .button-primary-flat:hover,
        .button-primary-flat:focus {
            background-color: #4cb0f9;
            border-color: #4cb0f9;
            color: #FFF; }
        .button-primary:active, .button-primary.active, .button-primary.is-active,
        .button-primary-flat:active,
        .button-primary-flat.active,
        .button-primary-flat.is-active {
            background-color: #2798eb;
            border-color: #2798eb;
            color: #0880d7; }
        .button-rounded {
            border-radius: 4px; }
        .button-small {
            font-size: 12px;
            height: 30px;
            line-height: 30px;
            padding: 0 30px; }
    </style>
    <script language="javascript">
        function preview(oper)
        {
            if (oper < 10){
                bdhtml=window.document.body.innerHTML;//获取当前页的html代码
                sprnstr="<!--startprint"+oper+"-->";//设置打印开始区域
                eprnstr="<!--endprint"+oper+"-->";//设置打印结束区域
                prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18); //从开始代码向后取html
                prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));//从结束代码向前取html
                window.document.body.innerHTML=prnhtml;
                window.print();
                window.document.body.innerHTML=bdhtml;
            } else {
                window.print();
            }
        }
    </script>
</head>
<body >
<!--startprint1-->
<div class="middle_main">
    <div>
        <table class="public_table">
            <tr class="public_table_head">
                <th colspan="5" >${stuinfo.stu_name}同学(${stuinfo.stu_id})的基本信息</th>
            </tr>
            <tr>
                <th class="public_table_item">学号</th><td class="public_table_value_short">${stuinfo.stu_id}</td>
                <th class="public_table_item">姓名</th><td class="public_table_value_short">${stuinfo.stu_name}</td>
                <td rowspan="5" class="public_table_value_img">
                    <img src="${stuinfo.stu_img}" height="100px" width="100px"/>
                </td>
            </tr>
            <tr>
                <th class="public_table_item">性别</th><td class="public_table_value_short">${stuinfo.stu_sex}</td>
                <th class="public_table_item">民族</th><td class="public_table_value_short">${stuinfo.stu_nation}</td>
            </tr>
            <tr>
                <th class="public_table_item">生日</th><td class="public_table_value_short">${stuinfo.stu_birth}</td>
                <th class="public_table_item">联系电话</th><td class="public_table_value_short">${stuinfo.stu_phone}</td>
            </tr>
            <tr>
                <th class="public_table_item">所属实验室</th>
                <td class="public_table_value_short">
                    ${stuinfo.lab_name}
                </td>
                <th class="public_table_item">宿舍</th><td class="public_table_value_short">${stuinfo.stu_room}</td>
            </tr>
            <tr>
                <th class="public_table_item">学院</th><td class="public_table_value_short">${stuinfo.stu_college}</td>
                <th class="public_table_item">专业</th><td class="public_table_value_short">${stuinfo.stu_major}</td>
            </tr>
            <tr>
                <th class="public_table_item">邮箱</th><td colspan="4"  class="public_table_value_short">${stuinfo.stu_email}</td>
            </tr>
            <tr>
                <th class="public_table_item">密码</th><td colspan="4" class="public_table_value_short">${stuinfo.stu_pwd}</td>
            </tr>
        </table>
    </div>

    <div>
        <table class="public_table">
            <tr class="public_table_head">
                <th colspan="5" >${stuinfo.stu_name}同学(${stuinfo.stu_id})的成绩</th>
            </tr>
            <tr>
                <td class="public_table_item">学科编号</td>
                <td class="public_table_item">科目</td>
                <td class="public_table_item">分数</td>
            </tr>
            <c:forEach  var="score" items="${scores}" >
                <tr>
                    <td class="public_table_value_short">${score.sub_id}</td>
                    <td class="public_table_value_short">${score.sub_name}</td>
                    <td class="public_table_value_short">${score.sco_value}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <!--endprint1-->
    <div>
        <table class="public_table">
            <tr class="public_table_head">
                <th colspan="4">操作</th>
            </tr>
            <tr>
                <th class="public_table_item" style="font-size: medium">修改密码</th>
                <td class="public_table_value_short">
                    <form action="/serv_publicshow" method="post">
                        <input type="text" name="stuid" value="${stuinfo.stu_id}" hidden="hidden"/>
                        <input type="text" name="func" value="changepwd" hidden="hidden"/>
                        <input type="password" name="stu_pwd" value="${stuinfo.stu_pwd}" class="public_table_textbox" />
                        <input type="submit" class="button button-primary button-rounded button-small" value="修改"/>
                    </form>
                </td>
                <th class="public_table_item">
                    <a href="/serv_student_edit?func=edit&stu_id=${stuinfo.stu_id}&isadmin=${stuinfo.stu_pwd}" class="button button-primary button-rounded button-small" >修改信息-></a>
                </th>
                <th class="public_table_item">
                    <input type=button name='button_export' class="button button-primary button-rounded button-small" title='打印' onclick=preview(1) value=打印>
                </th>
            </tr>
        </table>
    </div>
</div>

</body>
</html>
