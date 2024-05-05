package uz.smartup.academy.hibernateadvanced.mvc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uz.smartup.academy.hibernateadvanced.dto.ReviewDTO;
import uz.smartup.academy.hibernateadvanced.dto.StudentDTO;
import uz.smartup.academy.hibernateadvanced.service.CourseService;
import uz.smartup.academy.hibernateadvanced.service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/web/students")
public class StudentWebController {
    private final StudentService service;

    private final CourseService courseService;


    @Value("${ratings}")
    private List<Integer> ratings;

    public StudentWebController(StudentService service, CourseService courseService) {
        this.service = service;
        this.courseService = courseService;
    }

    @GetMapping
    public String getAllStudents(Model model) {
        model.addAttribute("students", service.getStudents());

        return "getStudents";
    }

    @RequestMapping("/{id}/delete")
    public String deleteStudent(@PathVariable int id) {
        service.deleteStudent(id);

        return "redirect:/web/students";
    }

    @GetMapping("/{id}/updateForm")
    public String updateStudent(@PathVariable("id") int id, Model model) {
        model.addAttribute("student", service.getStudent(id));

        return "studentUpdateForm";
    }

    @RequestMapping("/{id}/update")
    public String update(@ModelAttribute("student") StudentDTO student, @PathVariable("id") int id) {
        service.updateStudent(student);

        return "redirect:/web/students";
    }

    @GetMapping("/{id}")
    public String getStudent(@PathVariable("id") int id, Model model) {
        model.addAttribute("student", service.getStudent(id));
        model.addAttribute("courses", service.getStudentCourses(id));

        return "getStudent";
    }

    @GetMapping("/{id}/courses")
    public String getCoursesToAdd(Model model, @PathVariable("id") int id) {
        model.addAttribute("courses", service.anotherCourses(id));

        return "anotherCourses";
    }

    @RequestMapping("/{id}/enrollStudent/{courseId}")
    public String enrollStudentToCourse(@PathVariable("id") int id, @PathVariable("courseId") int courseId, RedirectAttributes attributes) {
        service.enrollCourse(id, courseId);

        attributes.addAttribute("id", id);

        return "redirect:/web/students/{id}";
    }

    @GetMapping("/{id}/courses/{courseId}/reviews")
    public String  getReviews(@PathVariable int id, @PathVariable int courseId, Model model) {
        model.addAttribute("reviews", service.getReviews(id, courseId));

        return "studentReviews";
    }

    @GetMapping("/{id}/courses/{courseId}/reviews/form")
    public String  addReviewForm(Model model) {
        model.addAttribute("review", new ReviewDTO());
        model.addAttribute("ratings", ratings);

        return "studentReviewsForm";
    }

    @PostMapping("/{id}/courses/{courseId}/reviews/add")
    public String addReview(@ModelAttribute("review") ReviewDTO review, @PathVariable("id") int id, @PathVariable("courseId") int courseID,
    RedirectAttributes attributes) {
        service.addReview(id, courseID, review);

        attributes.addAttribute("id", id);

        return "redirect:/web/students/{id}";
    }

    @GetMapping("/studentForm")
    public String studentForm(Model model) {
        model.addAttribute("student", new StudentDTO());

        return "studentForm";
    }

    @PostMapping("/save")
    public String studentSave(@ModelAttribute("student") StudentDTO student) {
        service.save(student);

        return "redirect:/web/students";
    }
}
