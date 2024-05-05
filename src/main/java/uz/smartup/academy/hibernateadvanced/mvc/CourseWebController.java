package uz.smartup.academy.hibernateadvanced.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uz.smartup.academy.hibernateadvanced.dto.CourseDTO;
import uz.smartup.academy.hibernateadvanced.dto.ReviewDTO;
import uz.smartup.academy.hibernateadvanced.dto.StudentDTO;
import uz.smartup.academy.hibernateadvanced.service.CourseService;

import java.util.List;

@Controller
@RequestMapping("web/courses")
public class CourseWebController {
    private final CourseService service;

    public CourseWebController(CourseService service) {
        this.service = service;
    }

    @GetMapping
    public String getCourses(Model model) {
        model.addAttribute("courses", service.getAllCourses());

        return "courses";
    }

    @RequestMapping("/{courseId}/delete")
    public String deleteCourse(@PathVariable("courseId") int id) {
//        System.out.println(id);
        service.deleteCourseById(id);

        return "redirect:/web/courses";
    }

    @RequestMapping("/{id}/updateForm")
    public String updateCourseForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("course", service.getCourseById(id));

        return "updateCourseForm";
    }

    @RequestMapping("{id}/updateCourse")
    public String updateCourse(@ModelAttribute("course") CourseDTO course, @PathVariable("id") int id) {
        System.out.println(course.getId());
        service.updateCourse(course);

        return "redirect:/web/courses";
    }

    @GetMapping("/{id}")
    public String getCourse(@PathVariable("id") int id, Model model) {
        model.addAttribute("course", service.getCourseById(id));
        model.addAttribute("students", service.getCourseStudentsById(id));

        return "getCourse";
    }

    @GetMapping("{id}/students")
    public String courseStudentForm(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("student", new StudentDTO());

        return "courseStudentForm";
    }

    @PostMapping("/{courseId}/students/save")
    public String saveStudent(@PathVariable("courseId") int id, @ModelAttribute("student") StudentDTO student, RedirectAttributes redirectAttributes) {
        service.addStudent(id, student);

        redirectAttributes.addAttribute("id", id);

        return "redirect:/web/courses/{id}";
    }

}
