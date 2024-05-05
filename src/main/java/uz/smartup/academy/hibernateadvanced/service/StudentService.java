package uz.smartup.academy.hibernateadvanced.service;

import uz.smartup.academy.hibernateadvanced.dto.CourseDTO;
import uz.smartup.academy.hibernateadvanced.dto.ReviewDTO;
import uz.smartup.academy.hibernateadvanced.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    void createStudent(StudentDTO StudentDTO);

    List<StudentDTO> getStudents();

    StudentDTO getStudent(int id);

    void updateStudent(StudentDTO StudentDTO);

    void deleteStudent(int id);

    void enrollCourse(int id, int courseId);

    List<CourseDTO> getStudentCourses(int id);

    void excludeStudentFromCourse(int id, int courseId);

    void addReview(int id, int courseId, ReviewDTO review);

    List<ReviewDTO> getReviews(int id, int courseId);

    void updateReview(int id, int courseId, ReviewDTO review);

    List<CourseDTO> anotherCourses(int id);

    void save(StudentDTO studentDTO);
}
