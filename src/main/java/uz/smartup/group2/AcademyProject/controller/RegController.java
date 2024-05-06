package uz.smartup.group2.AcademyProject.controller;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.smartup.group2.AcademyProject.entity.Course;
import uz.smartup.group2.AcademyProject.entity.Instructor;
import uz.smartup.group2.AcademyProject.entity.Student;
import uz.smartup.group2.AcademyProject.service.InstructorService;
import uz.smartup.group2.AcademyProject.service.StudentService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RegController {
    private int login = 0;
    private final StudentService studentService;
    private final InstructorService instructorService;

    public RegController(StudentService studentService, InstructorService instructorService) {
        this.studentService = studentService;
        this.instructorService = instructorService;
    }

    @RequestMapping({"", "/", "/home"})
    public String home(Model model) {
        return "home";
    }

    @RequestMapping("/v1/signup")
    public String signUp() {
        return "signUp";
    }

    @RequestMapping("/v1/student/login")
    public String login() {
        return "studentLogin";
    }

    @RequestMapping("/student/temp")
    public String temp() {
        return "temp";
    }

    @PostMapping("/v1/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        Student student = studentService.findByEmail(email);
        if (student != null && BCrypt.checkpw(password, student.getPassword())) {
            login = student.getId();
            return "redirect:/v1/student/" + login;
        }
        Instructor instructor = instructorService.findByEmail(email);
        if (instructor != null && BCrypt.checkpw(password, instructor.getPassword())) {
            login = instructor.getId();
            return "redirect:/v1/instructor/" + login;
        }
        return "temp";
    }
}
