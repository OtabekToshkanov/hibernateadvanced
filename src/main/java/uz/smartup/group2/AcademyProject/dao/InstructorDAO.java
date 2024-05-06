package uz.smartup.group2.AcademyProject.dao;

import uz.smartup.group2.AcademyProject.entity.Course;
import uz.smartup.group2.AcademyProject.entity.Instructor;
import uz.smartup.group2.AcademyProject.entity.Review;

import java.util.List;

public interface InstructorDAO {
    Instructor findInstructorById(int id);
    void deleteById(int id);
    List<Review> getAllCourseReviews(int courseId);
    Review getById(int reviewId);
}
