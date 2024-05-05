package uz.smartup.academy.hibernateadvanced.dao;

import uz.smartup.academy.hibernateadvanced.dto.CourseDTO;
import uz.smartup.academy.hibernateadvanced.entity.*;

import java.util.List;

public interface AppDAO {
    void save(Instructor instructor);

    List<Instructor> getAllInstructors();

    Instructor findInstructorById(int id);

    void update(Instructor instructor);

    void deleteInstructorById(int id);

    void save(Course course);

    List<Course> getAllCourcesByInstructorId(int id);

    void save(Student student);

    List<Student> getAllStudents();

    Student findStudentById(int id);

    void update(Student student);

    void deleteStudentById(int id);

    void addStudentToCourse(int studentId, int courseId);

    List<Course> getStudentCourses(int id);

    void deleteStudentFromCourse(int studentId, int courseId);

    void addStudentReview(int studentId, int courseId, Review review);

    List<Review> getStudentReviews(int studentId, int courseId);

    void updateStudentReview(int studentId, int courseId, Review review);

    List<Course> getAllCourses();

    Course findCourseById(int id);

    List<Review> getReviewsByCourseId(int id);

    List<Student> getStudentsByCourseId(int id);

    void updateCourse(Course course);

    void deleteCourseById(int id);

    List<Course> listAnotherCoursesByStudentId(int id);

    void deleteReviewsByCourseId(int id);

    void deleteReviewById(int id);

    void deleteReview(int id);
}
