package smartup.academy.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import smartup.academy.dto.UserDTO;
import smartup.academy.serves.UserServise;

@Controller
@RequestMapping("/api/profile")
public class ProflController {
    UserServise userServise;

    public ProflController(UserServise userServise) {
        this.userServise = userServise;
    }

    @GetMapping
  public String profl(Model model){
       UserDetails details= userServise.getLoggedUser();
        UserDTO userDTO=userServise.userFindByUsername(details.getUsername());
        model.addAttribute("userDTO",userDTO);
      return "/profile.html";
  }

}
