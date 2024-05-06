package uz.smartup.group2.AcademyProject.dao;

import uz.smartup.group2.AcademyProject.entity.Course;
import uz.smartup.group2.AcademyProject.entity.Review;
import uz.smartup.group2.AcademyProject.entity.Student;

import java.util.List;

public interface StudentDAO {
    List<Course> getStudentCourses(int id);
    Student findById(int id);
    void deleteById(int id);
    void addCourse(int courseId, int studentId);
    void removeCourseFromStudent(int studentId, int courseId);
    void addReviewToCourseFromStudent(Review review, int courseId, int studentId);
}
