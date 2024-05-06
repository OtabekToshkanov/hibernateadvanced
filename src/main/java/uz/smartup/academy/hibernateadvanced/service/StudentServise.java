package uz.smartup.academy.hibernateadvanced.service;



import uz.smartup.academy.hibernateadvanced.dto.CourseDTO;
import uz.smartup.academy.hibernateadvanced.dto.ReviewDTO;
import uz.smartup.academy.hibernateadvanced.dto.StudentDTO;

import java.util.List;

public interface StudentServise {

    List<StudentDTO>  studentAll();

    List<StudentDTO> findStudentByFirstName(String firstName);

    void deleteStudent(int id);

    void enrollCourse(int id, int courseId);

    List<CourseDTO> getStudentCourses(int id);

    List<CourseDTO> getCourseFilteredStudentById(int id);

    void excludeStudentFromCourse(int id, int courseId);

    void addReview(int id, int courseId, ReviewDTO review);

    List<ReviewDTO> getReviews(int id, int courseId);

    void updateReview(int id, int courseId, ReviewDTO review);

}
