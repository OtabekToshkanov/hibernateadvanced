package uz.smartup.academy.hibernateadvanced.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.smartup.academy.hibernateadvanced.dto.CourseDTO;
import uz.smartup.academy.hibernateadvanced.dto.ReviewDTO;
import uz.smartup.academy.hibernateadvanced.dto.StudentDTO;
import uz.smartup.academy.hibernateadvanced.service.CourseService;
import uz.smartup.academy.hibernateadvanced.service.ReviewService;
import uz.smartup.academy.hibernateadvanced.service.StudentServise;
import uz.smartup.academy.hibernateadvanced.service.UserServise;

import java.util.List;

@Controller
@RequestMapping("/web/students/")
public class StudentController {

    private final StudentServise service;
    private final CourseService courseService;
    private final ReviewService reviewService;
    private final UserServise userServise;

    public StudentController(StudentServise studentServise, CourseService courseService, ReviewService reviewService, UserServise userServise) {
        this.service = studentServise;
        this.courseService = courseService;
        this.reviewService = reviewService;
        this.userServise = userServise;
    }

    @GetMapping("")
    public String studentForm(Model model){
        List<StudentDTO> students=service.studentAll();
        //System.out.println(students);
        model.addAttribute("studentDTO",students);
        return "student/student-form.html";
    }

    int instid;
    @GetMapping("{id}/courses")
    public String getStudentCourses(@PathVariable int id, Model model) {
        model.addAttribute("courseDTO", service.getStudentCourses(id));
        instid=id;
        return "student/student-courses-form";
    }

    @GetMapping("{id}/courses/new")
    public String recordStudentCourseForm( Model model) {
        List<CourseDTO> courseDTO = service.getCourseFilteredStudentById(instid);
        model.addAttribute("studentId", instid);
        model.addAttribute("courseDTO", courseDTO);
        return "student/student-course-create.html";

    }

    @GetMapping("courses/{courseId}")
    public String saveStudentCourse(@PathVariable int courseId ,Model model) {
        service.enrollCourse(instid,courseId);

        List<CourseDTO> courseDTO1=service.getStudentCourses(courseId);

        model.addAttribute("courseDTO", courseDTO1);
        return "redirect:/web/students/";
    }

    @GetMapping("{studentId}/courses/{courseId}")
    public String deleteStudentFromCourse(@PathVariable int studentId, @PathVariable int courseId){
        service.excludeStudentFromCourse(studentId, courseId);
        return "redirect:/web/students/"+studentId+"/courses";
    }


    int cID;
    @GetMapping("courses/{courseId}/reviews")
    public String getAllReviewsForCourseByStudent(@PathVariable int courseId, Model model) {
        cID = courseId;
        CourseDTO courseDTO = courseService.getCourseById(courseId);
        //System.out.println(instid+" "+courseId);
        List<ReviewDTO> reviews = service.getReviews(instid, courseId);
        model.addAttribute("courseDTO", courseDTO);
        model.addAttribute("reviewDTO", reviews);
        return "student/student-course-review-form.html";
    }

    @GetMapping("courses/{courseId}/reviews/new")
    public String createStudentCourseReviewForm(@PathVariable int courseId, Model model){

        CourseDTO courseDTO = courseService.getCourseById(courseId);

        ReviewDTO reviewDTO = new ReviewDTO();
        model.addAttribute("reviewDTO",reviewDTO);
        model.addAttribute("courseDTO",courseDTO);
        return "review/review-create.html";
    }

    @PostMapping("courses/{courseId}/reviews")
    public String addReviewToCourse(@PathVariable int courseId, @ModelAttribute ReviewDTO reviewDTO) {

        reviewDTO.setStudentId(instid);
        reviewDTO.setCourseId(cID);
        //System.out.println(reviewDTO);
        service.addReview(instid, courseId, reviewDTO);
        return "redirect:/web/students/";
    }
    @GetMapping("{id}/delete")
    public String deleteStudentById(@PathVariable int id){

        service.deleteStudent(id);

        return "redirect:/web/students/";
    }

    @GetMapping("firstName")
    public String findStudentByFirstName(@RequestParam(name = "search") String firstName, Model model){
        model.addAttribute("studentDTO", service.findStudentByFirstName(firstName));
        return "student/student-form.html";
    }

}
