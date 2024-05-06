package uz.smartup.academy.hibernateadvanced.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.smartup.academy.hibernateadvanced.dto.InstructorDTO;
import uz.smartup.academy.hibernateadvanced.dto.InstructorDTOUtil;
import uz.smartup.academy.hibernateadvanced.dto.UserDTO;
import uz.smartup.academy.hibernateadvanced.dto.UserDTOUtil;
import uz.smartup.academy.hibernateadvanced.entity.Instructor;
import uz.smartup.academy.hibernateadvanced.entity.User;
import uz.smartup.academy.hibernateadvanced.service.InstructorServis;
import uz.smartup.academy.hibernateadvanced.service.UserServise;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/web/users/")
public class UserController {

    UserServise userServise;
    InstructorDTOUtil instructorDTOUtil;
    UserDTOUtil userDTOUtil;
    InstructorServis instructorServis;

    public UserController(UserServise userServise,InstructorDTOUtil instructorDTOUtil,UserDTOUtil userDTOUtil,InstructorServis instructorServis) {
        this.userServise = userServise;
        this.instructorDTOUtil=instructorDTOUtil;
        this.userDTOUtil=userDTOUtil;
        this.instructorServis=instructorServis;
    }

    @RequestMapping("")
    public String string(Model model){
        List<UserDTO> userDTOs=userServise.userAll();
        model.addAttribute("userDTO",userDTOs);
        return "user/user-form.html";
    }

    @GetMapping("register")
    public String createUser(Model model){
        UserDTO userDTO=new UserDTO();
        model.addAttribute("userDTO",userDTO);
        return "/registr";
    }

    String[] roles;

    @PostMapping("register/save")
    public String saveUser(@ModelAttribute UserDTO userDTO, Model model){
        userServise.userPersist(userDTO);
        if (Arrays.asList(userDTO.getRoles()).contains("ROLE_INSTRUCTOR")){
            Instructor instructor=new Instructor();
            User user=userDTOUtil.toEntity(userDTO);
            instructor.setUser(user);
            InstructorDTO instructorDTO=instructorDTOUtil.toDTOUser(instructor);
            model.addAttribute("instructorDTO",instructorDTO);
            roles=(userDTO.roles);
            return "instructor/instructor-create.html";
        }
        return "redirect:/web/users/";
    }

    @PostMapping("/createInstructor")
    public String createInstructor(@ModelAttribute InstructorDTO instructorDTO){
        instructorDTO.setRole(roles);
        instructorServis.instructorPersist(instructorDTO);
        return "redirect:/web/users/";
    }

    @GetMapping("edit/{id}")
    public String userMerge(@PathVariable int id, Model model){
        model.addAttribute("userDTO",userServise.userFindById(id));
        return "user/user-edit-form.html";
    }

    @PostMapping("edit")
    public String userMerge(@ModelAttribute UserDTO userDTO, Model model){
        userServise.userMerge(userDTO);
        return "redirect:/web/users/";
    }

    @GetMapping("delete/{id}")
    public String userDelete(@PathVariable int id){
        userServise.UserDeleteById(id);
        return "redirect:/web/users/";
    }

    @GetMapping("/firstName")
    public String getUserFirstname(@RequestParam(name = "search") String firstName, Model model){
        model.addAttribute("userDTO", userServise.UserByFirstName(firstName));
        return "user/user-form.html";
    }


}
