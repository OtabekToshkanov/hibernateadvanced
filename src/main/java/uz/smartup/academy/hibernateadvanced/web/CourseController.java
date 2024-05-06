package uz.smartup.academy.hibernateadvanced.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.smartup.academy.hibernateadvanced.dto.CourseDTO;
import uz.smartup.academy.hibernateadvanced.service.CourseService;
import uz.smartup.academy.hibernateadvanced.service.InstructorServis;


@Controller
@RequestMapping("/web/courses/")
public class CourseController {
    private final CourseService service;
    private final InstructorServis instructorService;

    public CourseController(CourseService service, InstructorServis instructorService) {
        this.service = service;
        this.instructorService = instructorService;
    }


    @GetMapping("")
    public String listAllCourses(Model model){
        model.addAttribute("courseDTO", service.getAllCourses());
        return "course/courses-form";
    }

    @GetMapping("{id}")
    public String getCourseStudents(@PathVariable int id, Model model) {
        model.addAttribute("studentDTO", service.getCourseStudentsById(id));
        return "course/course-student-form";
    }

    @GetMapping("edit/{courseId}")
    public String editCourseForm(@PathVariable int courseId, Model model) {
        CourseDTO courseDTO =  service.getCourseById(courseId);
        System.out.println(courseDTO);
        model.addAttribute("courseDTO",courseDTO);
        //System.out.println(service.getCourseById(id));
        return "course/course-edit";
    }

    @PostMapping("{courseId}")
    public String updateCourse(@PathVariable int courseId,
                               @ModelAttribute("courseDTO") CourseDTO courseDTO,
                               Model model) {
        service.updateCourse(courseDTO,courseId);
        return "redirect:/web/instructors/"+courseDTO.getInstructorId()+"/courses";
    }

    @GetMapping("{courseId}/delete")
    public String deleteCourse(@PathVariable int courseId) {
        service.deleteCourseById(courseId);
        return "redirect:/web/instructors/";
    }

}


