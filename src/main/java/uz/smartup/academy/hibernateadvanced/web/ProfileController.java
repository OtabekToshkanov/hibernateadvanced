package uz.smartup.academy.hibernateadvanced.web;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uz.smartup.academy.hibernateadvanced.dao.AppDAO;
import uz.smartup.academy.hibernateadvanced.entity.User;

@Controller
public class ProfileController {

    private AppDAO appDAO;

    public ProfileController(AppDAO appDAO) {
        this.appDAO = appDAO;
    }

    private UserDetails getLoggetInUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails){
            return (UserDetails) principal;
        }
        return null;
    }

    @GetMapping("/web/profile/")
    public String profile(Model model){

        UserDetails details = getLoggetInUser();

        User user = appDAO.userFindByUserName(details.getUsername());

        model.addAttribute("user", user);

        return "profile.html";
    }
}
