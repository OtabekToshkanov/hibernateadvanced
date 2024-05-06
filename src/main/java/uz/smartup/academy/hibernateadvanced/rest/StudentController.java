package uz.smartup.academy.hibernateadvanced.rest;

import org.springframework.web.bind.annotation.*;
import uz.smartup.academy.hibernateadvanced.dto.CourseDTO;
import uz.smartup.academy.hibernateadvanced.dto.ReviewDTO;
import uz.smartup.academy.hibernateadvanced.dto.StudentDTO;
import uz.smartup.academy.hibernateadvanced.service.StudentService;
import uz.smartup.academy.hibernateadvanced.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public void createStudent(@RequestBody StudentDTO StudentDTO) {
        service.createStudent(StudentDTO);
    }

    @GetMapping
    public List<StudentDTO> getStudents() {
        return service.getStudents();
    }

    @GetMapping("/{id}")
    public StudentDTO getStudent(@PathVariable int id) {
        return service.getStudent(id);
    }

    @PutMapping
    public void updateStudent(@RequestBody StudentDTO StudentDTO) {
        service.updateStudent(StudentDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        service.deleteStudent(id);
    }

    @PostMapping("{id}/courses/{courseId}")
    public void addEnrollCourse(@PathVariable int id, @PathVariable int courseId) {
        service.enrollCourse(id, courseId);
    }

    @GetMapping("{id}/courses")
    public List<CourseDTO> getStudentCourses(@PathVariable int id) {
        return service.getStudentCourses(id);
    }

    @DeleteMapping("{id}/courses/{courseId}")
    public void excludeCourse(@PathVariable int id, @PathVariable int courseId) {
        service.excludeStudentFromCourse(id, courseId);
    }

    @PostMapping("{id}/courses/{courseId}/reviews")
    public void addReview(@PathVariable int id, @PathVariable int courseId, @RequestBody ReviewDTO review) {
        service.addReview(id, courseId, review);
    }

    @GetMapping("{id}/courses/{courseId}/reviews")
    public List<ReviewDTO> getReviews(@PathVariable int id, @PathVariable int courseId) {
        return service.getReviews(id, courseId);
    }

    @PutMapping("{id}/courses/{courseId}/reviews")
    public void updateReview(@PathVariable int id, @PathVariable int courseId, @RequestBody ReviewDTO review) {
        service.updateReview(id, courseId, review);
    }
}