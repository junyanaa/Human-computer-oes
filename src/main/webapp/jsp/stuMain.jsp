<%@ page contentType="text/html;charset=UTF-8" %>
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
            background-image: url("../images/starrySky.png");
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
    </style>
    <script>
        function stuExamList(){
            document.getElementById("stuExamList").submit();
        }
        function quit(){
            document.getElementById("quit").submit();
        }
        function stuCheckScore(){
            document.getElementById("stuCheckScore").submit();
        }
    </script>
</head>
<body>
<div class="shell">
    <%--进入考试列表--%>
    <div class="box" onclick="stuExamList()">
        <form id="stuExamList" method="post" action="/stuExamList">
            <div class="images">
                <img src="${pageContext.request.contextPath}/images/1.png" alt="">
            </div>
            <div class="content">
                <h2>考试</h2>
                <h2>操作</h2>
                <p>参加考试</p>
            </div>
        </form>

    </div>

    <%--查看考试成绩功能--%>
    <div class="box" onclick="stuCheckScore()">
        <form id="stuCheckScore" method="post" action="/stuCheckScore">
            <div class="images">
                <img src="${pageContext.request.contextPath}/images/3.png" alt="">
            </div>
            <div class="content">
                <h2>成绩</h2>
                <h2>记录</h2>
                <p>查看考试成绩</p>
            </div>
        </form>

    </div>

    <%--查看个人信息功能--%>
    <div class="box" onclick="window.location.href='stuInfo.jsp'">
        <div class="images">
            <img src="${pageContext.request.contextPath}/images/4.png" alt="">
        </div>
        <div class="content">
            <h2>个人</h2>
            <h2>信息</h2>
            <p>查看个人信息</p>
        </div>
    </div>

    <%--返回登录注册页面功能--%>
    <div class="box" onclick="quit()">
        <form id="quit" method="post" action="/stuLogout">
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