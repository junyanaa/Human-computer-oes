<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>学生注册</title>
    <style>
        *{
            color: #BFA725;
        }
        body{
            /* 设置背景渐变 */
            background-image: linear-gradient(to left,
            #B0B6A3,#B0B6A0);
            display: flex;
            justify-content: center;

        }
        .a{
            position:relative;
            top: 100px;
            width: 1100px;
            height: 550px;
            box-shadow: 0 5px 15px rgba(0,0,0,.8);
            display: flex;
        }
        .b{
            width: 800px;
            height: 550px;
            background-image: url("../images/NEZHADAJIA.jpg");
            /* 让图片适应大小 */
            background-size: cover;
        }
        .c{
            width: 300px;
            height: 550px;
            background-color: #22308E;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .d{
            width: 250px;
            height: 500px;
            background-color: #22308E;
        }
        .d h1{
            font: 900 30px '';
        }
        .e{
            width: 230px;
            margin: 5px 0;
            outline: none;
            border: 0;
            padding: 10px;
            border-bottom: 3px solid #425A7E;
            font: 900 16px '';
        }
        .f{
            position: absolute;
            margin-left: 240px;
            margin-bottom: 20px;
            bottom: 40px;
            display: block;
            width: 200px;
            height: 60px;
            font: 900 30px '';
            text-decoration: none;
            line-height: 50px;
            border-radius: 30px;
            background-image: linear-gradient(to left,
            #425A7E,#416396);
            text-align: center;
        }
        .i{
            position: absolute;
            margin-left: 460px;
            margin-bottom: 20px;
            bottom: 40px;
            display: block;
            width: 200px;
            height: 60px;
            font: 900 30px '';
            text-decoration: none;
            line-height: 50px;
            border-radius: 30px;
            background-image: linear-gradient(to left,
            #425A7E,#416396);
            text-align: center;
        }
        .g{
            position: absolute;
            margin: 20px;
            bottom: 40px;
            display: block;
            width: 200px;
            height: 60px;
            font: 900 30px '';
            text-decoration: none;
            line-height: 50px;
            border-radius: 30px;
            background-image: linear-gradient(to left,
            #425A7E,#416396);
            text-align: center;
        }
        input::placeholder{
            color: #BFA725;
        }
        input{
            background-color: #22308E;
        }
        .h{
            width: 20px;
            margin: 5px 0;
            outline: none;
            border: 0;
            padding: 10px;
            border-bottom: 3px solid rgb(80,80,170);
            font: 900 16px '';
        }
    </style>
</head>
<body>
<div class="a">
    <div class="b"></div>
    <div class="c">
        <div class="d">
            <h1>学生注册</h1>
            <label>
                <input type="text" class="e" name="id" placeholder="学生 I D">
            </label>
            <label>
                <input type="text"  class="e" name="name" placeholder="名      字">
            </label>
            <label class="box">
                <input type="radio" class="h" name="switch" id="boy">
                <label  class="h" for="boy">男</label>
                <input type="radio" class="h" name="switch" id="girl">
                <label  class="h" for="girl">女</label>
            </label>
            <label>
                <input type="date" class="e" name="birthdate" placeholder="出生日期">
            </label>
            <label>
                <input type="tel"  class="e" name="phone" placeholder="电      话">
            </label>
            <label>
                <input type="text" class="e" name="idCard" placeholder="身份证号">
            </label>
            <label>
                <input type="password" class="e" name="password" placeholder="密     码">
            </label>
            <label>
                <input type="password" class="e" name="ensure" placeholder="重复密码">
            </label>
            <form method="get" >
                <%//输出注册结果
                %>
            </form>
        </div>
    </div>
    <button  type="submit" class="g">立即注册</button>
    <a href="../login.jsp" class="f">返回登录</a>
    <button type="reset" class="i">重置</button>
</div>
</body>
</html>