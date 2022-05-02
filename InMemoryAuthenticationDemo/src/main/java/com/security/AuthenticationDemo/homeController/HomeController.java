package com.security.AuthenticationDemo.homeController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String showHome(){
        return "HomePage";
    }

    @GetMapping("/welcome")
    public String showWelcome(){
        return "WelcomePage";
    }

    @GetMapping("/admin")
    public String showAdmin(){
        return "AdminPage";
    }

    @GetMapping("/std")
    public String showStudent(){
        return "StudentPage";
    }

    @GetMapping("/employee")
    public String showEmployee(){
        return "EmployeePage";
    }
}
