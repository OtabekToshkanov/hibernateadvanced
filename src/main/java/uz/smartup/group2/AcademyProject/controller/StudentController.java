package uz.smartup.group2.AcademyProject.controller;

import com.sun.source.tree.StringTemplateTree;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import uz.smartup.group2.AcademyProject.dto.CourseDTO;
import uz.smartup.group2.AcademyProject.dto.ReviewDTO;
import uz.smartup.group2.AcademyProject.dto.StudentDTO;
import uz.smartup.group2.AcademyProject.dto.StudentDTOUtil;
import uz.smartup.group2.AcademyProject.entity.Course;
import uz.smartup.group2.AcademyProject.entity.Review;
import uz.smartup.group2.AcademyProject.entity.Student;
import uz.smartup.group2.AcademyProject.service.CourseService;
import uz.smartup.group2.AcademyProject.service.StudentService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.*;

@Controller
public class StudentController {
    private int balance = 300;
    private final StudentService studentService;
    private final CourseService courseService;

    public StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping("/v1/student/signup")
    public String studentRegister(Model model, StudentDTO studentDTO) {
        model.addAttribute("student", studentDTO);
        return "studentRegister";
    }

    @PostMapping("/v1/student/signup")
    public String register(@Valid @ModelAttribute("student") StudentDTO studentDTO,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "studentRegister";
        }
        String encodedPassword = BCrypt.hashpw(studentDTO.getPassword(), BCrypt.gensalt(12));
        studentDTO.setPassword(encodedPassword);
        studentDTO.setLocalDateTime(LocalDateTime.now());
        studentDTO.setRole("STUDENT");
        studentService.save(studentDTO);
        Student student = studentService.findByEmail(studentDTO.getEmail());
        return "redirect:/v1/student/" + student.getId();
    }

    @RequestMapping("/v1/student/{id}")
    public String studentForm(@PathVariable("id") int id, Model model) {
        StudentDTO studentDTO = studentService.findById(id);
        model.addAttribute("profileId", studentDTO.getId());
        model.addAttribute("firstName", studentDTO.getFirstName());
        model.addAttribute("lastName", studentDTO.getLastName());
        model.addAttribute("email", studentDTO.getEmail());
        model.addAttribute("localTime", studentDTO.getLocalDateTime());
        jdbc(model);
        return "studentProfile";
    }

    @GetMapping("/v1/student/settings/{id}")
    public String settingForm(@PathVariable("id") int id,
                              Model model) {
        model.addAttribute("updateId", id);
        return "studentProfileSettings";
    }

    @RequestMapping(value = "/v1/student/update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") int id, Model model) {
        StudentDTO studentDTO = studentService.findById(id);
        model.addAttribute("userId", id);
        model.addAttribute("updateStudentFirstName", studentDTO.getFirstName());
        model.addAttribute("updateStudentLastName", studentDTO.getLastName());
        model.addAttribute("updateStudentEmail", studentDTO.getEmail());
        return "studentUpdateProfile";
    }

    @PostMapping("/v1/student/update/{id}")
    public String updateForm( @ModelAttribute("userId") StudentDTO studentDTO,
                             @PathVariable("id") int id) {
        studentService.update(studentDTO);
        return "redirect:/v1/student/" + id;
    }
    @GetMapping("/v1/student/balance")
        public String StudentPayment(Model model){
        model.addAttribute("paymentId", 456);
        model.addAttribute("defaultBalance", balance);
        return "studentBalance";
        }
    @GetMapping("/v1/student/{id}/courses")
    public String getStudentCourses(@PathVariable int id, Model model) {
        model.addAttribute("studentCourses", studentService.getStudentCourses(id));
        model.addAttribute("studentCourseId", id);
        return "studentCourses";
    }
    @GetMapping("/v1/student/{id}/logout")
    public String logOut(@PathVariable("id") int id){
        studentService.deleteById(id);
        return "redirect:/v1/student/login";
    }
    public void jdbc(Model model) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/academyuz", "root", "root");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from course");

            List<Course> courseList = new ArrayList<>();
            while (resultSet.next()) {
                Course course = new Course();
                course.setId(resultSet.getInt("id"));
                course.setCourseName(resultSet.getString("course_name"));
                course.setDecription(resultSet.getString("description"));
                course.setDuration(resultSet.getInt("duration"));
                course.setCourseFee(resultSet.getInt("course_fee"));
                course.setCourseFormat(resultSet.getString("course_format"));
                course.setCategory(resultSet.getString("category"));
                courseList.add(course);
            }
            model.addAttribute("courseList", courseList);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @PostMapping("/v1/student/{studentId}/addCourse/{courseId}")
    public String addCourse(@PathVariable("studentId") int studentId, @PathVariable("courseId") int courseId) {
        studentService.addCourse(courseId, studentId);
        return "redirect:/v1/student/" + studentId;
    }
    @PostMapping("/v1/student/{studentId}/remove/{courseId}")
    public String removeCourse(@PathVariable("studentId") int studentId, @PathVariable("courseId") int courseId){
        studentService.removeCourseFromStudent(courseId, studentId);
        return "redirect:/v1/student/" + studentId+"/courses";
    }
    @GetMapping("/v1/student/{studentId}/addReview/{courseId}")
    public String addReviewForm(@PathVariable("studentId") int studentId, @PathVariable("courseId") int courseId, Model model){
        model.addAttribute("studentId", studentId);
        model.addAttribute("courseId", courseId);
        return "studentAddReview";
    }

    @PostMapping("/v1/student/{studentId}/addReview/{courseId}")
    public String addReview(@PathVariable("studentId") int studentId, @PathVariable("courseId") int courseId,
                            @RequestParam("rating") int rating,
                            @RequestParam("comment") String comment){
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setComment(comment);
        reviewDTO.setRating(rating);
        studentService.addReviewToCourseFromStudent(reviewDTO, courseId, studentId);
        return "redirect:/v1/student/"+studentId+"/courses";
    }
}
