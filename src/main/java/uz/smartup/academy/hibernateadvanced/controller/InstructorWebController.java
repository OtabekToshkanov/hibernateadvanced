package uz.smartup.academy.hibernateadvanced.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.smartup.academy.hibernateadvanced.dto.CourseDTO;
import uz.smartup.academy.hibernateadvanced.service.InstructorService;

@Controller
@RequestMapping("/web/instructors")
public class InstructorWebController {
    private InstructorService service;

    public InstructorWebController(InstructorService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String getInstructors(Model model) {
        model.addAttribute("instructors", service.getInstructors());

        return "instructor_list";
    }
    @GetMapping("/courses/{id}")
    public String getCourseStudents(@PathVariable int id,Model model) {
//        model.addAttribute("studentDTO", service.getCourseStudentsById(id));
        return "course/course-student-form";
    }

    @GetMapping("/courses/edit/{id}")
    public String editCourseForm(@PathVariable int id, Model model) {
//        model.addAttribute("courseDTO", service.getCourseById(id));
        //System.out.println(service.getCourseById(id));
        return "course/course-edit";
    }

    @PostMapping("/courses/{id}")
    public String updateCourse(@PathVariable int id,
                               @ModelAttribute("courseDTO") CourseDTO courseDTO,
                               Model model) {

//        service.updateCourse(courseDTO,id);
        return "course/courses-form.html";
    }


    @RequestMapping("/delete/{id}")
    public String deleteInstructor(@PathVariable long id) {
//        service.deleteInstructor(id);
        System.out.printf("Instructor with id %d is deleted%n", id);
        return "redirect:/web/instructors/";
    }
}
