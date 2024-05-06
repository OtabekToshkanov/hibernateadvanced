package uz.smartup.academy.hibernateadvanced.service;


import uz.smartup.academy.hibernateadvanced.dto.CourseDTO;
import uz.smartup.academy.hibernateadvanced.dto.ReviewDTO;
import uz.smartup.academy.hibernateadvanced.dto.StudentDTO;

import java.util.List;

public interface CourseService {
    List<CourseDTO> getAllCourses();

    CourseDTO getCourseById(int id);

    List<ReviewDTO> getCourseReviewsById(int id);

    List<StudentDTO> getCourseStudentsById(int id);

    void updateCourse(CourseDTO courseDTO, int id);

    void deleteCourseById(int id);
}
