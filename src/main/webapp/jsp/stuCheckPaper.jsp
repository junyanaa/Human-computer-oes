<%--
  Created by IntelliJ IDEA.
  User: Winnie
  Date: 2023/12/6
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="com.oes.bean.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<style>
  body {
    font-family: 'Arial', sans-serif;
    background: radial-gradient(circle, #ff6b6b, #f6f6f6, #48dbfb);
    margin: 0;
    padding: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
  }

  .clockBody{
    position: fixed;
    margin-top: -95%;
    margin-left: -73%;
    background-color: #48dbfb;
  }

  .container {
    width: 90%;
    max-width: 800px;
    background-color: #fff;
    padding: 30px;
    border-radius: 10px;
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
    opacity: 0;
    transform: translateY(-50px);
    animation: fadeInUp 0.6s ease forwards;
  }

  @keyframes fadeInUp {
    to {
      opacity: 1;
      transform: translateY(0);
    }
  }

  h1 {
    text-align: center;
    color: #333;
    margin-bottom: 20px;
    font-size: 2em;
    text-transform: uppercase;
    letter-spacing: 2px;
  }

  .question {
    margin-bottom: 30px;
    opacity: 0;
    transform: translateY(20px);
    animation: fadeInQuestion 0.5s ease forwards;
  }

  @keyframes fadeInQuestion {
    to {
      opacity: 1;
      transform: translateY(0);
    }
  }

  .question h3 {
    color: #555;
    font-size: 22px;
    margin-bottom: 15px;
  }

  .question p {
    color: #777;
    font-size: 18px;
    margin-bottom: 20px;
  }

  input[type="radio"],
  input[type="checkbox"] {
    margin-right: 10px;
    appearance: none;
    -webkit-appearance: none;
    -moz-appearance: none;
    width: 18px;
    height: 18px;
    border: 2px solid #ccc;
    border-radius: 50%;
    outline: none;
    cursor: pointer;
    vertical-align: middle;
    position: relative;
    transition: border-color 0.3s ease;
  }

  input[type="radio"]::after,
  input[type="checkbox"]::after {
    content: '';
    display: block;
    width: 10px;
    height: 10px;
    border-radius: 50%;
    background-color: #ff6b6b;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%) scale(0);
    transition: transform 0.3s ease;
  }

  input[type="radio"]:checked::after,
  input[type="checkbox"]:checked::after {
    transform: translate(-50%, -50%) scale(1);
  }

  input[type="text"] {
    padding: 12px;
    border-radius: 6px;
    border: 1px solid #ccc;
    width: calc(100% - 26px);
    margin-top: 5px;
    font-size: 18px;
    transition: border-color 0.3s ease;
  }

  input[type="text"]:focus {
    border-color: #ff6b6b;
  }

  button {
    padding: 14px 30px;
    background-color: #ff6b6b;
    color: #fff;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    font-size: 20px;
    transition: background-color 0.3s ease;
  }

  button:hover {
    background-color: #48dbfb;
  }
</style>
<head>
    <%
        //定义一个考试对象表示当前的考试对象
        Exam currentExam = (Exam) session.getAttribute("exam_session");

        // 获取当前学生当前试卷的题目
        List<Question> questions = (List<Question>) session.getAttribute("paperQuestions");
        // 获取当前学生当前试卷的学生答案
        List<Answer> answers = (List<Answer>) session.getAttribute("paperAnswers");

        // 获取当前的学生
        Student student = (Student) session.getAttribute("stu_session");
    %>
    <title><%=student.getStu_name()%> 的 <%=currentExam.getExam_name()%> 试卷</title>
</head>
<body>
    <div class="question">
        <%
            for (int i = 0 ; i < questions.size(); i++){
                if ("单选题".equals(questions.get(i).getQuestion_type())){%>
        <p>单选题</p>
        <p><%=questions.get(i).getQuestion_content()%></p>
        <fieldset>
            <input type="radio" name="单选题" value="A"><%=questions.get(i).getQuestion_optionA()%><br>
            <input type="radio" name="单选题" value="B"><%=questions.get(i).getQuestion_optionB()%><br>
            <input type="radio" name="单选题" value="C"><%=questions.get(i).getQuestion_optionC()%><br>
            <input type="radio" name="单选题" value="D"><%=questions.get(i).getQuestion_optionD()%><br>
        </fieldset>
        <p>正确答案：<%=questions.get(i).getQuestion_answer()%></p>
        <p>你的答案：<%=answers.get(i).getStu_answer()%></p>
        <br><br><br>

        <%}else if ("多选题".equals(questions.get(i).getQuestion_type())){%>
        <p>多选题</p>
        <p><%=questions.get(i).getQuestion_content()%></p>
        <fieldset>
            <input type="checkbox" value="A"><%=questions.get(i).getQuestion_optionA()%><br>
            <input type="checkbox" value="B"><%=questions.get(i).getQuestion_optionB()%><br>
            <input type="checkbox" value="C"><%=questions.get(i).getQuestion_optionC()%><br>
            <input type="checkbox" value="D"><%=questions.get(i).getQuestion_optionD()%><br>
        </fieldset>
        <p>正确答案：<%=questions.get(i).getQuestion_answer()%></p>
        <p>你的答案：<%=answers.get(i).getStu_answer()%></p>
        <br><br><br>

        <%}else if ("判断题".equals(questions.get(i).getQuestion_type())){%>
            <p>判断题</p>
            <p><%=questions.get(i).getQuestion_content()%></p>
            <input type="radio" name="<%=questions.get(i).getQuestion_id()%>" value="true">正确<br>
            <input type="radio" name="<%=questions.get(i).getQuestion_id()%>" value="false">错误<br>
            <p>正确答案：<%=questions.get(i).getQuestion_answer()%></p>
            <p>你的答案：<%=answers.get(i).getStu_answer()%></p>
            <br><br><br>

        <%}else if ("填空题".equals(questions.get(i).getQuestion_type())){%>
            <p>填空题</p>
            <p><%=questions.get(i).getQuestion_content()%></p>
            <input type="text" placeholder="正确答案：<%=questions.get(i).getQuestion_answer()%>"><br>
            <p>你的答案：<%=answers.get(i).getStu_answer()%></p>
            <br><br><br>
        <%}
        }%>

        <button onclick="window.location='/jsp/stuRecordQuery.jsp'">返回</button>
    </div>

</body>
</html>
