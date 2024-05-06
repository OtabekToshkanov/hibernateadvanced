package smartup.academy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AcreptionHandlingController {

    @GetMapping("/accses-denied")
    public String accsesDenied(){
        System.out.println("salom");
        return "/login/accses-denied.html";
    }
}

