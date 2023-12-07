<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<html>
<head>
    <title>主页</title>
    <style>
        @font-face {
            font-family: 'BlackLogo';
            src: url("/fonts/1.ttf");
        }
        * {
            margin: 0;
            padding: 0;
        }
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background: url(/images/3.png) center / cover;
        }
        .shell {
            position: relative;
            min-width: 1000px;
            display: flex;
            justify-content: center;
            flex-wrap: wrap;
            /* 设置3d变化 */
            transform-style: preserve-3d;
            /* 添加透视效果 */
            perspective: 900px;
        }
        .shell .box {
            position: relative;
            width: 200px;
            height: 300px;
            transition: 0.3s;
            overflow: hidden;
            margin: 30px;
            transform: rotateY(0deg);
            transition-delay: .1s;
            border-radius: 5px;
            border: #fff 5px solid;
        }
        /* 当鼠标所有卡片的父元素点shell上时，
        所有卡片像Y轴翻转20度 */
        .shell:hover .box {
            transform: rotateY(20deg);
        }
        /* 当鼠标悬浮在当前卡片上时，当前卡片的翻转角度为0，
        并放大一点二五倍，加个阴影 */
        .shell .box:hover {
            transform: rotateY(0deg) scale(1.25);
            box-shadow: 0 25px 40px rgba(0, 0, 0, 0.7);
            z-index: 1;
        }
        /* 当鼠标悬浮在卡片上时，
        当前卡片后面的所有卡片全部都反向翻转负20度 */
        .shell .box:hover~.box {
            transform: rotateY(-20deg);
        }
        .shell .box .images img {
            width: 100%;
            height: 100%;
        }
        .shell .box .content {
            position: absolute;
            top: 0;
            width: 90%;
            height: 100%;
            z-index: 999;
            padding: 15px;
        }
        .shell .box .content h2 {
            color: #ffffff;
            transition: 0.3s;
            font-family: 'BlackLogo', serif ;
            font-weight: normal;
            font-size: 50px;
        }
        .shell .box .content p {
            color: rgb(0, 0, 0);
            transition: 0.3s;
            font-size: 14px;
            transform: translateY(200px);
            background-color: rgba(255, 255, 255, 0.7);
        }
        .shell .box:hover .content p {
            transform: translateY(80px);
        }
        .overlay {
            color: #BFA725;
            position: fixed;
            top: 50px;
            left: 40%;
            width: 500px;
            height: 500px;
            display: none;
        }
        .popup {
            background-color: #416396;
            position: absolute;
            top: 50px;
            left: 50%;
            width: 300px;
            height: 150px;
            transform: translate(-50%, -50%);
            padding: 20px;
            border-radius: 5px;
        }
        .close {
            position: absolute;
            top: 10px;
            right: 10px;
            cursor: pointer;
            font-size: 10px;
        }
        .winLabel,.winButton,.winInput{
            font-size: 1.5rem;
            font-family: monospace;
            color: white;
        }
        .winLabel:focus,.winButton:focus,.winInput:focus{
            outline: 3px solid yellow;
            outline-offset: 2px;
        }
        .winInput::-moz-focus-inner,
        .winLabel::-moz-focus-inner,
        .winButton::-moz-focus-inner{
            border: none;
        }
        .winInput{
            padding: 0.75rem 4rem 0.75rem 0.75rem;
            width: 70%;
            border: 2px solid transparent;
            border-radius: 0;
            background-color: transparent;
            box-shadow: inset 0 0 0 2px black, inset 6px 6px 0 rgba(30, 144, 255, 0.2), 3px 3px 0 rgba(30, 144, 255, 0.2);
            -webkit-appearance: none;
        }
        .winButton{
            cursor: pointer;
            margin: 20px auto 0 2px;
            padding: 0.75rem 1.25rem;
            color: black;
            background-color: var(--inputColor);
            box-shadow: 4px 4px 0 rgba(30, 144, 255, 0.2);
            border: none;
        }
        .winButton:active{
            transform: translateY(1px);
        }
    </style>
    <script>
        function allStudent(){
            document.getElementById("allStudent").submit();
        }
        function teaLogout(){
            document.getElementById("teaLogout").submit();
        }
        function allExam(){
            document.getElementById("examThing").submit();
        }
        function openForm() {
            document.getElementById("overlay").style.display = "block";
        }
        function closeForm() {
            document.getElementById("overlay").style.display = "none";
        }
        function openForm1() {
            document.getElementById("overlay1").style.display = "block";
        }
        function closeForm1() {
            document.getElementById("overlay1").style.display = "none";
        }
    </script>
</head>
<body>
<div class="overlay" id="overlay1">
    <div class="popup">
        <span class="close" onclick="closeForm1()">&times;</span>
        <form action="/infoCheck" method="post">
            <label class="winLabel" for="passwordCheck">
                <input class="winInput" type="text" name="passwordCheck" id="passwordCheck" placeholder="输入密码">
            </label>
            <input class="winButton" type="submit" value="确认">
        </form>
    </div>
</div>

<%--<div class="overlay" id="overlay">--%>
<%--    <div class="popup">--%>
<%--        <span class="close" onclick="closeForm()">&times;</span>--%>
<%--        <form action="${pageContext.request.contextPath}/allExamLog" method="post">--%>
<%--            <label class="winLabel" for="examId">--%>
<%--                <input class="winInput"  type="text" name="logExamId" id="examId" placeholder="输入考试ID">--%>
<%--            </label>--%>
<%--            <input class="winButton" type="submit" value="确认">--%>
<%--        </form>--%>
<%--    </div>--%>
<%--</div>--%>
<div class="shell">
    <div class="box"  onclick="allExam()">
        <form id="examThing" action="${pageContext.request.contextPath}/allExam" method="post">
        <div class="images">
            <img src="${pageContext.request.contextPath}/images/1.png" alt="">
        </div>
        <div class="content">
            <h2>考试</h2>
            <h2>操作</h2>
            <p>添加考试、录入题目等</p>
        </div>
        </form>
    </div>

    <div class="box" onclick="allStudent()">
        <form action="${pageContext.request.contextPath}/allStudent" method="post" id="allStudent">
        <div class="images">
            <img src="${pageContext.request.contextPath}/images/2.png" alt="">
        </div>
        <div class="content">
            <h2>学生</h2>
            <h2>管理</h2>
            <p>管理教师班级学生</p>
        </div></form>
    </div>
    <div class="box" onclick="window.location='recordQuery1.jsp'">
        <div class="images">
            <img src="${pageContext.request.contextPath}/images/3.png" alt="">
        </div>
        <div class="content">
            <h2>记录</h2>
            <h2>查询</h2>
            <p>查询学生考试记录、成绩等</p>
        </div>
    </div>

    <div class="box" onclick="openForm1()">
        <div class="images">
            <img src="${pageContext.request.contextPath}/images/4.png" alt="">
        </div>
        <div class="content">
            <h2>信息</h2>
            <h2>管理</h2>
            <p>编辑个人信息</p>
        </div>
    </div>
    <div class="box" onclick="teaLogout()">
        <form method="post" action="/teaLogout" id="teaLogout">
        <div class="images">
            <img src="${pageContext.request.contextPath}/images/5.png" alt="">
        </div>
        <div class="content">
            <h2>退出</h2>
            <h2>登录</h2>
            <p>返回登录注册页面</p>
        </div>
        </form>
    </div>
</div>
</body>
</html>