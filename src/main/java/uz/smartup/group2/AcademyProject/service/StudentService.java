package uz.smartup.group2.AcademyProject.service;

import lombok.AllArgsConstructor;
import uz.smartup.group2.AcademyProject.dto.CourseDTO;
import uz.smartup.group2.AcademyProject.dto.ReviewDTO;
import uz.smartup.group2.AcademyProject.dto.StudentDTO;
import uz.smartup.group2.AcademyProject.entity.Review;
import uz.smartup.group2.AcademyProject.entity.Student;
import uz.smartup.group2.AcademyProject.repository.StudentRepository;

import java.util.List;

public interface StudentService{
    public Student findByEmail(String email);
    public Student save(StudentDTO studentDTO);
    StudentDTO findById(int id);
    void update(StudentDTO studentDTO);
    List<CourseDTO> getStudentCourses(int id);
    void deleteById(int id);
    void addCourse(int courseId, int studentId);
    void removeCourseFromStudent(int courseId, int studentId);
    void addReviewToCourseFromStudent(ReviewDTO reviewDTO, int courseId, int studentId);
}
