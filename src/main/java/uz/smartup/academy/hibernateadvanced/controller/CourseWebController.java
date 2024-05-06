package uz.smartup.academy.hibernateadvanced.controller;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.smartup.academy.hibernateadvanced.dto.CourseDTO;
import uz.smartup.academy.hibernateadvanced.dto.InstructorDTO;
import uz.smartup.academy.hibernateadvanced.service.CourseService;

import java.util.List;

@Controller
@RequestMapping("/web/courses")
public class CourseWebController {
    private CourseService service;

    public CourseWebController(CourseService service) {
        this.service = service;
    }
    @GetMapping
    public String getAllCourses(Model model) {
        model.addAttribute("courses",service.getAllCourses());
        return "courses-list";
    }
    @RequestMapping("/{id}/delete")
    public String deleteCourse(@PathVariable("id")int id){
        service.deleteCourseById(id);
        return "redirect:/web/courses";
    }
    @RequestMapping("{id}/updateCourses")
    public String updateCourses(@ModelAttribute("course") CourseDTO courseDTO) {
        service.updateCourse(courseDTO);

        return "redirect:/web/courses";
    }

    @RequestMapping("/{id}/updateCourseForm")
    public String update(@PathVariable("id") int id, Model model) {
       // model.addAttribute("course", service.getInstructor(id));
        model.addAttribute("course",service.getCourseById(id));
//        System.out.println(service.getCourseById(id));
        return "update-courses";
    }

}
