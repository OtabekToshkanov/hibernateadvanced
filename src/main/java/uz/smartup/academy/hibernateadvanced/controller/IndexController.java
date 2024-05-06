package uz.smartup.academy.hibernateadvanced.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
