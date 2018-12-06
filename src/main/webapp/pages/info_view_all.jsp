<%--
  Created by IntelliJ IDEA.
  User: joger
  Date: 18-11-1
  Time: 下午7:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>Title</title>
    <script src="../scripts/echarts/echarts.js"></script>
    <link href="../../css/mystyle.css" rel="stylesheet" type="text/css" />
    <link href="../../css/buttons.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div>男女比例分布图</div>
 <div id="main" style="width: 600px;height:400px;"></div>
 <script type="text/javascript">
     var myChart = echarts.init(document.getElementById('main'));
     var option = {
         series : [
             {
                 name: '访问来源',
                 type: 'pie',
                 radius: '55%',
                 data:[
                     {value:${echarts_data.boy_count}, name:'男生'},
                     {value:${echarts_data.girl_count}, name:'女生'}
                 ]
             }
         ]
     };
     // 基于准备好的dom，初始化echarts实例
     myChart.setOption(option);
 </script>
</body>
</html>
