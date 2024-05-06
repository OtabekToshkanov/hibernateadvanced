package uz.smartup.group2.AcademyProject.dao;

import org.springframework.stereotype.Repository;
import uz.smartup.group2.AcademyProject.dto.CourseDTO;
import uz.smartup.group2.AcademyProject.entity.Course;

import java.util.List;


public interface CourseDAO {
    List<Course> getAllCourcesByInstructorId(int id);

    void deleteCourseById(int id);

    Course findCourseById(int id);
    void updateCourse(int courseId, String courseName, String description, String duration, int courseFee, String courseFormat, String category);
}