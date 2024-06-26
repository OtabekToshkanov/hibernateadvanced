package uz.smartup.academy.hibernateadvanced.mvc;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uz.smartup.academy.hibernateadvanced.entity.Instructor;
import uz.smartup.academy.hibernateadvanced.entity.Role;
import uz.smartup.academy.hibernateadvanced.entity.Student;
import uz.smartup.academy.hibernateadvanced.entity.User;
import uz.smartup.academy.hibernateadvanced.repository.UserRepository;
import uz.smartup.academy.hibernateadvanced.service.UserService;

import java.util.Objects;

@Controller
public class IndexController {

    private final UserService service;


    public IndexController(UserService service) {
        this.service = service;
    }

    private UserDetails getLoggedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails)
            return (UserDetails) principal;

        return null;
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/web/profile")
    public String profile(RedirectAttributes attributes) {
        UserDetails details = getLoggedUser();

        assert details != null;
        if (details.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_INSTRUCTOR"))) {
            Instructor instructor = service.findInstructorByUsername(details.getUsername());

            attributes.addAttribute("id", instructor.getId());

            return "redirect:/web/instructors/{id}";
        }

        Student student = service.findStudentByUsername(details.getUsername());

        attributes.addAttribute("id", student.getId());

        return "redirect:/web/students/{id}";
    }

}
