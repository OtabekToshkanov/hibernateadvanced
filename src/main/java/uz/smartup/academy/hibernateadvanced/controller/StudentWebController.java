package uz.smartup.academy.hibernateadvanced.controller;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uz.smartup.academy.hibernateadvanced.dto.CourseDTO;
import uz.smartup.academy.hibernateadvanced.dto.InstructorDTO;
import uz.smartup.academy.hibernateadvanced.dto.ReviewDTO;
import uz.smartup.academy.hibernateadvanced.dto.StudentDTO;
import uz.smartup.academy.hibernateadvanced.service.StudentService;

@Controller
@RequestMapping("/web/students")
public class StudentWebController {
    private StudentService service;

    public StudentWebController(StudentService service) {
        this.service = service;
    }
    @GetMapping
    public String getStudents(Model model){
        model.addAttribute("students",service.getStudents());
        return "students-list";
    }
    @GetMapping("/studentForm")
    public String saveStudent(Model model){
        model.addAttribute("student",new StudentDTO());
        return "save-student";
    }
    @PostMapping("saveStudent")
    public String createStudent(@ModelAttribute("student") StudentDTO studentDTO){
        service.createStudent(studentDTO);
        return "redirect:/web/students";
    }
    @RequestMapping("/{id}/delete")
    public String deleteStudent(@PathVariable("id") int id) {
        service.deleteStudent(id);
        return "redirect:/web/students";
    }
    @RequestMapping("{id}/updateStudents")
    public String updateStudents(@ModelAttribute("student") StudentDTO studentDTO) {
        service.updateStudent(studentDTO);
        return "redirect:/web/students";
    }
    @RequestMapping("/{id}/updateStudentForm")
    public String update(@PathVariable("id") int id, Model model) {
        model.addAttribute("student", service.getStudent(id));
        return "update-students";
    }
    @GetMapping("/{id}")
    public String moreInfo(@PathVariable("id") int id ,Model model){
        model.addAttribute("student",service.getStudent(id));
        model.addAttribute("courses",service.getStudentCourses(id));
        return "students-courses";
    }
    @GetMapping("/{studentId}/{courseId}/reviewForm")
    public String reviewForm(Model model){
        model.addAttribute("review",new ReviewDTO());
        return "save-review";
    }
    @PostMapping("/{studentId}/{courseId}/saveReview")
    public String createReview(@ModelAttribute("review") ReviewDTO reviewDTO, @PathVariable("studentId") int id,@PathVariable("courseId") int courseId, RedirectAttributes attributes) {
        service.addReview(id,courseId,reviewDTO);

//        System.out.println(reviewDTO);
        attributes.addAttribute("courseId",courseId);

//        service.getStudentCourses(id);

        return "redirect:/web/students/{courseId}";
    }
    @GetMapping("/{studentId}/anotherCourses")
    public String anotherCourseInfo(@PathVariable("studentId") int studentId ,Model model){
        model.addAttribute("anotherCourses",service.anotherCourses(studentId));
        model.addAttribute("student",service.getStudent(studentId));
        return "students-another-courses";
    }

    @RequestMapping("/{studentId}/anotherCourses/{courseId}")
    public String addAnotherCourses(@PathVariable("studentId") int studentId,@PathVariable("courseId") int courseId,RedirectAttributes attributes,Model model){
        service.enrollCourse(studentId,courseId);
        attributes.addAttribute("studentId",studentId);
        return "redirect:/web/students/{studentId}";
    }
    @GetMapping("/{studentId}/{courseId}")
    public String info(@PathVariable("studentId") int studentId,@PathVariable("courseId") int courseId, Model model) {

        model.addAttribute("courses", service.getStudentCourses(courseId));
        model.addAttribute("student", service.getStudent(studentId));
        model.addAttribute("reviews",service.getReviews(studentId,courseId));
        return "students-courses-reviews";
    }


}
