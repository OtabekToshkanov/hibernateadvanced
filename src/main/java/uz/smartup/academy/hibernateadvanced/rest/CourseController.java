package uz.smartup.academy.hibernateadvanced.rest;

import org.springframework.web.bind.annotation.*;
import uz.smartup.academy.hibernateadvanced.dto.CourseDTO;
import uz.smartup.academy.hibernateadvanced.dto.ReviewDTO;
import uz.smartup.academy.hibernateadvanced.dto.StudentDTO;
import uz.smartup.academy.hibernateadvanced.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping
    public List<CourseDTO> getAllCourses() {
        return service.getAllCourses();
    }

    @GetMapping("/{id}")
    public CourseDTO getCourseById(@PathVariable int id) {
        return service.getCourseById(id);
    }

    @GetMapping("{id}/reviews")
    public List<ReviewDTO> getCourseReviews(@PathVariable int id) {
        return service.getCourseReviewsById(id);
    }

    @GetMapping("{id}/students")
    public List<StudentDTO> getCourseStudents(@PathVariable int id) {
        return service.getCourseStudentsById(id);
    }

    @PutMapping
    public void updateCourse(@RequestBody CourseDTO courseDTO) {
        service.updateCourse(courseDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable int id) {
        service.deleteCourseById(id);
    }
}