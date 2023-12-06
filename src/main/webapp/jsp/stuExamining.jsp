<%@ page import="com.oes.bean.Exam" %>
<%@ page import="java.util.List" %>
<%@ page import="com.oes.bean.Question" %>
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
<script src="../js/stuExamming.js"></script>
<script>
    /*定时自动提交*/
    setTimeout(function(){
            console.log("考试时间到，将自动提交试卷")
            document.getElementById("questions").submit()}
        , 1000*60*60*2)
</script>
<head>
    <%
        int id = (int) session.getAttribute("exam_id");
        //定义一个考试对象表示当前的考试对象
        Exam currentExam = null;
        List<Exam> exams = (List<Exam>) session.getAttribute("exam_session");
        for (Exam exam:exams){
            if (id == exam.getExam_id()){
                //将当前的考试对象赋值为匹配到的考试
                currentExam = exam;
            }
        }

        //获取存在session中的考试题
        List<Question> questions = (List<Question>) session.getAttribute("questions_session");
    %>
    <title><%=currentExam.getExam_name()%> 考试中</title>
</head>
<body>
<div class="clockBody">
        <div class="clock">
            <div class="clock-item hour"><span>00</span><div>时</div></div>
            <div class="clock-item minute"><span>00</span><div>分</div></div>
            <div class="clock-item second"><span>00</span><div>秒</div></div>
        </div>
</div>

<div class="shell">
    <h1 class="h1"><%=currentExam.getExam_name()%> 考试中</h1>
    <h4 class="time1">考试时间：<%=currentExam.getExam_start_Time()%> - <%=currentExam.getExam_end_Time()%></h4>
    <br><br><br><br>

    <form action="/completeExam" method="post" id="questions">
        <div class="question">
            <h3>一、单项选择题</h3>
            <%
                for (Question question:questions){
                    if ("单选题".equals(question.getQuestion_type())){%>
            <p><%=question.getQuestion_content()%></p>
            <input type="radio" name="<%=question.getQuestion_id()%>" value="A"><%=question.getQuestion_optionA()%><br>
            <input type="radio" name="<%=question.getQuestion_id()%>" value="B"><%=question.getQuestion_optionB()%><br>
            <input type="radio" name="<%=question.getQuestion_id()%>" value="C"><%=question.getQuestion_optionC()%><br>
            <input type="radio" name="<%=question.getQuestion_id()%>" value="D"><%=question.getQuestion_optionD()%><br>
            <%}
            }
            %>
        </div>
        <br><br><br>

        <div class="question">
            <h3>二、多选题</h3>
            <%
                for (int i = 0 ;  i < questions.size(); i++){
                    if ("多选题".equals(questions.get(i).getQuestion_type())){
                        session.setAttribute("check"+i, i);
            %>
            <p><%=questions.get(i).getQuestion_content()%></p>
            <input type="checkbox" name="<%=questions.get(i).getQuestion_id() + "A"%>" value="A"><%=questions.get(i).getQuestion_optionA()%><br>
            <input type="checkbox" name="<%=questions.get(i).getQuestion_id() + "B"%>" value="B"><%=questions.get(i).getQuestion_optionB()%><br>
            <input type="checkbox" name="<%=questions.get(i).getQuestion_id() + "C"%>" value="C"><%=questions.get(i).getQuestion_optionC()%><br>
            <input type="checkbox" name="<%=questions.get(i).getQuestion_id() + "D"%>" value="D"><%=questions.get(i).getQuestion_optionD()%><br>
            <%}
            }
            %>
        </div>
        <br><br><br>

        <div class="question">
            <h3>三、判断题</h3>
            <%
                for (Question question:questions){
                    if ("判断题".equals(question.getQuestion_type())){%>
            <p><%=question.getQuestion_content()%></p>
            <input type="radio" name="<%=question.getQuestion_id()%>" value="true">正确<br>
            <input type="radio" name="<%=question.getQuestion_id()%>" value="false">错误<br>
            <%}
            }
            %>
        </div>
        <br><br><br>

        <div class="question">
            <h3>四、填空题</h3>
            <%
                for (Question question:questions){
                    if ("填空题".equals(question.getQuestion_type())){%>
            <p><%=question.getQuestion_content()%></p>
            <input type="text" name="<%=question.getQuestion_id()%>"><br>
            <%}
            }
            %>
        </div>
        <br><br><br>

        <input type="submit" name="sub" value="提交">
        <input type="reset" name="res" value="重置">
        <br><br><br><br><br><br>
    </form>
</div>
</div>
</body>
</html>