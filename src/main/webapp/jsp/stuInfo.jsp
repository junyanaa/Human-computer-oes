<%@ page import="com.oes.bean.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人信息</title>
    <style>
        body{
            background: url("../images/iceMountain.png");
        }

        /*返回主页按钮样式*/
        button{
            background-color:dodgerblue;
            color:white;
            width: 200px;
            height: 40px;
            border:0;
            font-size: 16px;
            border-radius: 30px;
            position: absolute;
            bottom: 100px;
            left: auto;
            right: auto;
            margin-right: 15%;
            margin-top: 5%;
        }

    </style>
</head>
<body>

    <h1 style="margin-left: 45%; margin-top: 5%">个人信息</h1>
    <br><br>
    <div>
        <div style="margin-left: 20%; float: left">
            <p>学号：${sessionScope.stu_session.stu_number}</p><br><br>
            <p>姓名：${sessionScope.stu_session.stu_name}</p><br><br>
            <p>性别：${sessionScope.stu_session.stu_sex}</p><br><br>
            <p>生日：${sessionScope.stu_session.stu_birth}</p>

        </div>
        <div style="margin-left: 35%; float: left">
            <p>民族：${sessionScope.stu_session.stu_nation}</p><br><br>
            <p>年级：${sessionScope.stu_session.stu_grade}</p><br><br>
            <p>学院：${sessionScope.stu_session.stu_college}</p><br><br>
            <p>专业：${sessionScope.stu_session.stu_major}</p>
        </div>
    </div>

    <button onclick="window.location.href='stuMain.jsp'">返回主页</button>

</body>
</html>
