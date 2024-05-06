package smartup.academy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import smartup.academy.dto.InstructorDTO;
import smartup.academy.dto.InstructorDTOUtil;
import smartup.academy.dto.UserDTO;
import smartup.academy.dto.UserDTOUtil;
import smartup.academy.entity.Instructor;
import smartup.academy.entity.InstructorDetail;
import smartup.academy.entity.Role;
import smartup.academy.entity.User;
import smartup.academy.serves.InstructorServis;
import smartup.academy.serves.UserServise;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/api/users")
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

    @GetMapping
    public String string(Model model){
        List<UserDTO> userDTOs=userServise.userAll();
        model.addAttribute("userDTOs",userDTOs);
        return "/user/user-form.html";
    }

    @GetMapping("/persist")
    public String userPersist(Model model){
        UserDTO userDTO=new UserDTO();
        model.addAttribute("userDTO",userDTO);
        return "/user/user-persist.html";
    }
    String[] roles;
    @PostMapping("/Persist")
    public String userPersist(@ModelAttribute UserDTO userDTO, Model model){
            userServise.userPersist(userDTO);
        if (Arrays.asList(userDTO.getRoles()).contains("ROLE_INSTRUCTOR")){
            Instructor instructor=new Instructor();
            User user=userDTOUtil.toEntity(userDTO);
            instructor.setUser(user);
            InstructorDTO instructorDTO=instructorDTOUtil.toDTOUser(instructor);
            model.addAttribute("instructorDTO",instructorDTO);
            roles=(userDTO.roles);
            return "/instructor/instructor-INPUT.html";
        }
        return "redirect:/api/users";
    }

    @PostMapping("/instructorPersist")
    public String instructorPersist(@ModelAttribute InstructorDTO instructorDTO){
        instructorDTO.setRole(roles);
         instructorServis.instructorPersist(instructorDTO);
        return "redirect:/api/users";
    }

    @GetMapping("/merge/{id}")
    public String userMerge(@PathVariable int id, Model model){
        model.addAttribute("userDTO",userServise.userFindById(id));
        return "/user/user-merge.html";
    }



    @PostMapping("/Merge")
    public String userMerge(@ModelAttribute UserDTO userDTO, Model model){
        userServise.userMerge(userDTO);
        return "redirect:/api/users";
    }

    @GetMapping("/delete/{id}")
    public String userDelete(@PathVariable int id){
        userServise.UserDeleteById(id);
        return "redirect:/api/users";
    }
}
