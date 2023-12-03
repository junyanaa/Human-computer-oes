<%@ page import="com.oes.bean.Student" %>
<%@ page import="com.oes.bean.Teacher" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
  <title>信息</title>
  <style>

    :root {
      --bgColor: white;
      --inputColor: black;
      --outlineColor: rgb(141, 228, 41);
    }

    * {
      box-sizing: border-box;
      padding: 0;
      margin: 0;
    }

    body {
      display: grid;
      place-items: center;
      background: var(--bgColor);
    }
    .form-item>*+* {
      margin-top: 0.5rem;
    }

    label,
    input,
    button,.result,.buttos {
      font-size: 1.5rem;
      font-family: monospace;
      color: var(--inputColor);
    }
    label:focus,
    input:focus,
    button:focus {
      outline: 3px solid var(--outlineColor);
      outline-offset: 2px;
    }

    label::-moz-focus-inner,
    input::-moz-focus-inner,
    button::-moz-focus-inner {
      border: none;
    }
    button {
      background-color: transparent;
      border: none;
    }
    input,.result,.buttos{
      padding: 0.75rem 4rem 0.75rem 0.75rem;
      width: 100%;
      border: 2px solid transparent;
      border-radius: 0;
      background-color: transparent;
      box-shadow: inset 0 0 0 2px black, inset 6px 6px 0 rgba(30, 144, 255, 0.2), 3px 3px 0 rgba(30, 144, 255, 0.2);
      -webkit-appearance: none;
    }
    input:focus {
      outline-offset: 1px;
    }
    .title{
      height: 50px;
      position: absolute;
      top: 20px;
      font-size: 50px;
    }
    .left{
      width: 250px;
      height: 500px;
      position: absolute;
      top: 140px;
      left: 350px;
    }
    .right{
      width: 450px;
      height: 500px;
      position: absolute;
      top: 140px;
      right: 300px;
    }
    .mid{
      width: 50px;
      height: 500px;
      position: absolute;
      top: 140px;
      left: 650px;
    }

  </style>
  <script>
    function submit1(){
      document.getElementById("nameSub").submit();
    }
    function submit2(){
      document.getElementById("birthSub").submit();
    }
    function submit3(){
      document.getElementById("phoneSub").submit();
    }
    function submit4(){
      document.getElementById("courseSub").submit();
    }
    function submit5(){
      document.getElementById("passwordSub").submit();
    }
  </script>
</head>
<body>
<h1 class="title">苍白的我</h1>
<div class="left">
  <div class="form-item">
    <form method="post" action="/changeInfo1" id="nameSub">
      <input type="text" placeholder="名      字" name="nameSub">
    </form>
  </div>
  <div class="form-item">
    <form method="post" action="/changeInfo2" id="birthSub">
      <input type="date" placeholder="出生日期" name="birthSub">
    </form>
  </div>
  <div class="form-item">
    <form method="post" action="/changeInfo3" id="phoneSub">
      <input type="tel"  placeholder="电      话" name="phoneSub">
    </form>
  </div>
  <div class="form-item">
    <form method="post" action="/changeInfo4" id="courseSub">
      <input type="text"  placeholder="教授课程" name="courseSub">
    </form>
  </div>
  <div class="form-item" >
    <form method="post" action="/changeInfo5" id="passwordSub">
     <input type="password" placeholder="密     码" name="passwordSub">
    </form>
  </div>

</div>
<div class="mid">
  <div class="buttos"><button onclick="submit1()">山</button></div>
  <div class="buttos"><button onclick="submit2()" >崖</button></div>
<div class="buttos"><button onclick="submit3()">复</button></div>
  <div class="buttos"><button onclick="submit4()">远</button></div>
<div class="buttos"><button onclick="submit5()">望</button></div>
 <div class="buttos"><button onclick="window.location.href='teaMain.jsp'">返</button></div>
</div>
<div class="right">
  <%Teacher teacher= (Teacher) session.getAttribute("tea_session");%>
 <div class="result" id="teaName"><%=teacher.getTea_name()%></div>
<div class="result" id="teaBirthdate"><%=teacher.getTea_birthdate()%></div>
 <div class="result" id="teaPhone"><%=teacher.getTea_phone()%></div>
 <div class="result" id="teaCourse"><%=teacher.getTea_course()%></div>
 <div class="result" id="teaGrade"><%=teacher.getTea_grade()%></div>
</div>
</body>
</html>
