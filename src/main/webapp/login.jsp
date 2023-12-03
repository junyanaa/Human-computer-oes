<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>登录</title>
    <style>
        *{
            margin: 0;
            padding: 0;
        }/* 初始化 */
        body{
            /* 100%窗口高度 */
            height: 100vh;
            /* 弹性布局 水平+垂直居中 */
            display: flex;
            justify-content: center;
            align-items: center;
            /* 渐变背景 */
            background: linear-gradient(200deg,#B0B6A3,#B0B6A0);
        }
        .container{
            background-color: #22308E;
            width: 650px;
            height: 415px;
            border-radius: 5px;
            /* 阴影 */
            box-shadow: 5px 5px 5px rgba(0,0,0,0.1);
            /* 相对定位 */
            position: relative;
        }
        .form-box{
            /* 绝对定位 */
            position: absolute;
            top: -10%;
            left: 5%;
            background-color: #425A7E;
            width: 320px;
            height: 500px;
            border-radius: 5px;
            box-shadow: 2px 0 10px rgba(0,0,0,0.1);
            display: flex;
            justify-content: center;
            align-items: center;
            z-index: 2;
            /* 动画过渡 加速后减速 */
            transition: 0.5s ease-in-out;
        }
        .teaLogin-box,.stuLogin-box{
            /* 弹性布局 垂直排列 */
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 100%;
        }
        .hidden{
            display: none;
            transition: 0.5s;
        }
        h1{
            text-align: center;
            margin-bottom: 25px;
            /* 大写 */
            text-transform: uppercase;
            color: #BFA725;
            /* 字间距 */
            letter-spacing: 5px;
        }
        input{
            background-color: transparent;
            width: 70%;
            color: #B0B6A3;
            border: none;
            /* 下边框样式 */
            border-bottom: 1px solid rgba(255,255,255,0.4);
            padding: 10px 0;
            text-indent: 10px;
            margin: 8px 0;
            font-size: 14px;
            letter-spacing: 2px;
        }
        input::placeholder{
            color: #BFA725;
        }
        input:focus{
            color: #B0B6A3;
            outline: none;
            border-bottom: 1px solid #a262ad80;
            transition: 0.5s;
        }
        input:focus::placeholder{
            opacity: 0;
        }
        .form-box button{
            width: 70%;
            margin-top: 35px;
            background-color: #B0B6A3;
            outline: none;
            border-radius: 8px;
            padding: 13px;
            color: #416396;
            letter-spacing: 2px;
            border: none;
            cursor: pointer;
        }
        .form-box button:hover{
            background-color: #BFA725;
            color: #22308E;
            font-family: bolder;
            transition: background-color 0.5s ease;
        }
        .con-box{
            width: 50%;
            /* 弹性布局 垂直排列 居中 */
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            /* 绝对定位 居中 */
            position: absolute;
            top: 50%;
            transform: translateY(-50%);
        }
        .con-box.left{
            left: -2%;
        }
        .con-box.right{
            right: -2%;
        }
        .con-box h2{
            color: #BFA725;
            font-size: 25px;
            font-weight: bold;
            letter-spacing: 3px;
            text-align: center;
            margin-bottom: 4px;
        }
        .con-box p{
            font-size: 13px;
            letter-spacing: 2px;
            color: #BFA725;
            text-align: center;
        }
        .con-box span{
            color: #BFA725;
        }
        .con-box img{
            width: 150px;
            height: 150px;
            opacity: 0.9;
            margin: 40px 0;
           border-radius: 20px;
        }
        .con-box button{
            margin-top: 3%;
            background-color: #B0B6A3;
            color: #416396;
            border: 1px solid #B0B6A3;
            padding: 6px 10px;
            border-radius: 5px;
            letter-spacing: 1px;
            outline: none;
            cursor: pointer;
        }
        .con-box button:hover{
            background-color: #416396;
            color: #B0B6A3;
            border: 1px solid#416396;
        }
        .register{
            font-family: bolder serif;
            color: #BFA725;
            text-decoration: none;
            font-size: 15px;
        }
        .register:hover{
            font-family: bolder serif;
            font-size: 15px;
            color: #B0B6A3;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="form-box">

<%--        教师登录--%>
        <form action="teaLogin" method="post">
            <div  class="teaLogin-box hidden">
                <h1>TEACHER</h1>
                <label>
                    <input type="text" placeholder="教师工号" name="tea_ID">
                </label>
                <label>
                    <input type="password" placeholder="密码" name="tea_password">
                </label>
                <input type="submit" value="登录">
            </div>
        </form>

<%--       学生登录--%>
        <form action="stuLogin" method="post">
            <div class="stuLogin-box">
                <h1>STUDENT</h1>
                <label>
                    <input type="text" placeholder="学号" name="stu_number">
                </label>
                <label>
                    <input type="password" placeholder="密码" name="stu_password">
                </label>
                <input type="submit" value="登录">
            </div>
        </form>
    </div>


    <div class="con-box left">
        <h2>欢迎使用<span>在线考试系统</span>
        </h2><p>化作春泥更<span>护花</span></p>
        <img src="images/NEZHA.jpg" alt="">
        <a class="register" href="jsp/teaRegister.jsp">注册教师账号</a>
        <button id="stuLogin">我是学生</button>
    </div>
    <div class="con-box right">
        <h2>欢迎使用<span>在线考试系统</span></h2>
        <p>腹有<span>诗书</span>气自华</p>
        <img src="images/CAIYIFAN.jpg" alt="">
        <a class="register" href="jsp/stuRegister.jsp">注册学生账号</a>
        <button id="teaLogin">我是教师</button>
    </div>
</div>
<script>
    // 要操作到的元素
    let login=document.getElementById('stuLogin');
    let teaLogin=document.getElementById('teaLogin');
    let form_box=document.getElementsByClassName('form-box')[0];
    let register_box=document.getElementsByClassName('teaLogin-box')[0];
    let login_box=document.getElementsByClassName('stuLogin-box')[0];
    // 去注册按钮点击事件
    teaLogin.addEventListener('click',()=>{
        form_box.style.transform='translateX(80%)';
        login_box.classList.add('hidden');
        register_box.classList.remove('hidden');
    })
    // 去登录按钮点击事件
    login.addEventListener('click',()=>{
        form_box.style.transform='translateX(0%)';
        register_box.classList.add('hidden');
        login_box.classList.remove('hidden');
    })
</script>
</body>

</html>