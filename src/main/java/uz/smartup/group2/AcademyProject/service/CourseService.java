package uz.smartup.group2.AcademyProject.service;

import uz.smartup.group2.AcademyProject.dto.CourseDTO;
import uz.smartup.group2.AcademyProject.entity.Course;

import java.util.List;

public interface CourseService {
    void save(CourseDTO courseDTO);
    Course getById(int id);
    void update(int courseId, String courseName, String description, String duration, int courseFee, String courseFormat, String category);
    void deleteCourseById(int id);
}
