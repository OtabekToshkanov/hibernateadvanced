package uz.smartup.academy.hibernateadvanced.rest;

import org.springframework.web.bind.annotation.*;
import uz.smartup.academy.hibernateadvanced.dto.CourseDTO;
import uz.smartup.academy.hibernateadvanced.dto.InstructorDTO;
import uz.smartup.academy.hibernateadvanced.service.InstructorService;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {
    private final InstructorService service;

    public InstructorController(InstructorService service) {
        this.service = service;
    }

    @PostMapping
    public void createInstructor(@RequestBody InstructorDTO instructorDTO) {
        service.createInstructor(instructorDTO);
    }

    @GetMapping
    public List<InstructorDTO> getInstructors() {
        return service.getInstructors();
    }

    @GetMapping("/{id}")
    public InstructorDTO getInstructor(@PathVariable int id) {
        return service.getInstructor(id);
    }

    @PutMapping
    public void updateInstructor(@RequestBody InstructorDTO instructorDTO) {
        service.updateInstructor(instructorDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteInstructor(@PathVariable int id) {
        service.deleteInstructor(id);
    }

    @PostMapping("/{id}/courses")
    public void addCourse(@PathVariable int id, @RequestBody CourseDTO courseDTO) {
        service.addCourse(id, courseDTO);
    }

    @GetMapping("/{id}/courses")
    public List<CourseDTO> getCourses(@PathVariable int id) {
        return service.getCourses(id);
    }
}