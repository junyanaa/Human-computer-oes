package com.oes.controller;

import com.oes.bean.Teacher;
import com.oes.service.TeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class TeaController {

    @Autowired
    private TeaService teaService;

    //教师登录
    @RequestMapping(value = "/teaLogin", method = RequestMethod.POST)
    public String TeaLogin(HttpServletRequest request) {
        //获取登录页面发送请求的数据
        String tea_ID = request.getParameter("tea_ID");
        String tea_password = request.getParameter("tea_password");

        //在数据库中查找符合条件的教师用户
        Teacher teacher = teaService.teaLogin(tea_ID, tea_password);
        System.out.println(teacher);

        if (teacher != null) {
            //将查到的用户存入Session
            request.getSession().setAttribute("tea_session", teacher);
            return "redirect:/jsp/teaMain.jsp";             //重定向到学生主页
        } else {
            request.setAttribute("msg", "用户名或密码错误");
            return "forward:login.jsp";                     //返回到登录页面
        }
    }

    @RequestMapping(value = "/teaLogout")
    public String teaLogout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "forward:login.jsp";
    }

    @RequestMapping(value = "/infoCheck")
    public void infoCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String password = request.getParameter("passwordCheck");
        Teacher teacher = (Teacher) request.getSession().getAttribute("tea_session");
        System.out.println(password);
        System.out.println(teacher.getTea_password());
        if (password.equals(teacher.getTea_password())) {
            response.sendRedirect("jsp/teaInfo.jsp");
        } else {
            request.setAttribute("message", "密码错误！");
        }
    }

    @RequestMapping(value = "/changeInfo1")
    @ResponseBody
    public void changeInfo1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String flag = request.getParameter("nameSub");
        Teacher teacher = (Teacher) request.getSession().getAttribute("tea_session");
        int id = teacher.getTea_ID();
        teaService.changeName(flag, String.valueOf(id));
        teacher = teaService.findTeacher(id);
        request.getSession().setAttribute("tea_session", teacher);
        response.sendRedirect("jsp/teaInfo.jsp");
    }

    @RequestMapping(value = "/changeInfo2")
    @ResponseBody
    public void changeInfo2(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
        String tea_birthDate = request.getParameter("birthSub");
        tea_birthDate = tea_birthDate.replaceAll("T", " ");
        Teacher teacher = (Teacher) request.getSession().getAttribute("tea_session");
        int id = teacher.getTea_ID();
        teaService.changeBirthdate(tea_birthDate, String.valueOf(id));
        teacher = teaService.findTeacher(id);
        request.getSession().setAttribute("tea_session", teacher);
        response.sendRedirect("jsp/teaInfo.jsp");
    }

    @RequestMapping(value = "/changeInfo3")
    @ResponseBody
    public void changeInfo3(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String flag = request.getParameter("phoneSub");
        Teacher teacher = (Teacher) request.getSession().getAttribute("tea_session");
        int id = teacher.getTea_ID();
        teaService.changePhone(flag, String.valueOf(id));
        teacher = teaService.findTeacher(id);
        request.getSession().setAttribute("tea_session", teacher);
        response.sendRedirect("jsp/teaInfo.jsp");
    }

    @RequestMapping(value = "/changeInfo4")
    @ResponseBody
    public void changeInfo4(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String flag = request.getParameter("courseSub");
        Teacher teacher = (Teacher) request.getSession().getAttribute("tea_session");
        int id = teacher.getTea_ID();
        teaService.changeCourse(flag, String.valueOf(id));
        teacher = teaService.findTeacher(id);
        request.getSession().setAttribute("tea_session", teacher);
        response.sendRedirect("jsp/teaInfo.jsp");
    }

    @RequestMapping(value = "/changeInfo5")
    @ResponseBody
    public void changeInfo5(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String flag = request.getParameter("passwordSub");
        Teacher teacher = (Teacher) request.getSession().getAttribute("tea_session");
        int id = teacher.getTea_ID();
        teaService.changePassword(flag, String.valueOf(id));
        teacher = teaService.findTeacher(id);
        request.getSession().setAttribute("tea_session", teacher);
        response.sendRedirect("jsp/teaInfo.jsp");
    }
}

