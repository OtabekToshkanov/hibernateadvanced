package smartup.academy.controller;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import smartup.academy.dto.UserDTO;
import smartup.academy.serves.UserServise;

@Controller
public class Login {
    UserServise userServise;

    public Login(UserServise userServise) {
        this.userServise = userServise;
    }

    @GetMapping("/login")
    public String login(){
        return "login/loginone.html";
    }

    @GetMapping
    public String save(Model model){
        UserDetails details=userServise.getLoggedUser();
        UserDTO userDTO=userServise.userFindByUsername(details.getUsername());
        model.addAttribute("details",userDTO);
        return "index.html";
    }
}
