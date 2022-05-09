package com.trialThree.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class LoginController {

    //returns login.jsp page
    @RequestMapping("/")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    //returns dashboard.jsp page (restricted for user only)
    @RequestMapping("/dashboard")
    public ModelAndView userDashboard() {
        return new ModelAndView("dashboard");
    }

    //returns adminPanel.jsp page (restricted for admin only)
    @RequestMapping("/adminPanel")
    public ModelAndView adminDashboard() {
        return new ModelAndView("adminPanel");
    }
}
