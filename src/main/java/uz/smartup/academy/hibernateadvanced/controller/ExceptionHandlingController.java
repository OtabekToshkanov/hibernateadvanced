package uz.smartup.academy.hibernateadvanced.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.SecureRandom;

@Controller
public class ExceptionHandlingController {
    @GetMapping("access-denied")
    public String accessDenied(){
        return "access_denied";
    }
}
