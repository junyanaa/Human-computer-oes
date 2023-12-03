<%@ page import="com.oes.bean.Exam" %>
<%@ page import="java.util.List" %>
<%@ page import="com.oes.bean.Question" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<style>
    body{
        background-color: #c6e070;
    }

    .shell{
        width: 1100px;
        margin-top: -23px;
        margin-left: 10%;
        padding-left: 5%;
        background: rgb(242, 242, 242);
    }

    .h1{
        margin-left: 32%;
    }

    .time1{
        float: left;
    }

    form{
        clear: right;
    }
    .clockBody{
        position: fixed;
        top: 30px;
        right: 30px;
    }
    .container {
        display: flex;
        flex-direction: column;
        align-items: center;
        width: 300px;
        height: 150px;
        background-color: transparent; }

    .container .clock {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 200px;
        height: 100px;
        background-color: #5f546e;
        box-shadow: 0 0 40px rgba(0, 0, 0, 0.6); }

    .container .clock .clock-item {
        position: relative;
        display: flex;
        flex-direction: column;
        align-items: flex-end;
        width: 30px;
        margin: 0 20px; }

    .container .clock .clock-item div {
        font-size: 12px;
        color: #ccc; }

    .container .clock .clock-item span {
        font-size: 20px;
        font-weight: 100;
        color: #fff;
        letter-spacing: 5px; }

    .container .clock .clock-item:not(:last-child)::after {
        content: ":";
        position: absolute;
        top: 35%;
        right: -22px;
        font-size: 20px;
        color: #fff; }

    .toast {
        position: absolute;
        top: calc(50% - 25px);
        left: calc(50% - 100px);
        width: 200px;
        height: 50px;
        line-height: 50px;
        text-align: center;
        color: #fff;
        background-color: rgba(0, 0, 0, 0.6);
        opacity: 0;
        pointer-events: none; }

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
    <div class="container">
        <!-- <div class="menu">
            <div class="running"><span>开启</span></div>
            <div class="paused"><span>暂停</span></div>
        </div> -->
        <div class="clock">
            <div class="clock-item hour"><div>时</div><span>00</span></div>
            <div class="clock-item minute"><div>分</div><span>00</span></div>
            <div class="clock-item second"><div>秒</div><span>00</span></div>
        </div>
    </div>
    <div class="toast">我是提示框</div>
</div>
    <div class="shell">
        <h1 class="h1"><%=currentExam.getExam_name()%> 考试中</h1>
        <h4 class="time1">考试时间：<%=currentExam.getExam_start_Time()%> - <%=currentExam.getExam_end_Time()%></h4>
        <br><br><br><br>

        <form action="/completeExam" method="post" id="questions">
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
            <br><br><br>

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
            <br><br><br>

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
            <br><br><br>

            <h3>四、填空题</h3>
            <%
                for (Question question:questions){
                    if ("填空题".equals(question.getQuestion_type())){%>
            <p><%=question.getQuestion_content()%></p>
            <input type="text" name="<%=question.getQuestion_id()%>"><br>
            <%}
            }
            %>

            <br><br><br>
            <input type="submit" name="sub" value="提交">
            <input type="reset" name="res" value="重置">
            <br><br><br><br><br><br>
        </form>
    </div>
</body>
</html>

