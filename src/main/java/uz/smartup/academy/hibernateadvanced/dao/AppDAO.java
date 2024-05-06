package uz.smartup.academy.hibernateadvanced.dao;

import uz.smartup.academy.hibernateadvanced.entity.*;

import java.util.List;

public interface AppDAO {



    Instructor instructorFindBuId(int id);

    List<Instructor> findInstructorByFirstname(String firstName);

    List<Instructor> instructorAll();

    void instructorPersist(Instructor instructor);

    void instructorMerge(Instructor instructor);

    void instructorDeleteById(int id);

    void instructorAddCourse(Course course);

    List<Course> instructorAllCourse(int instructorId);

    List<Course>findInstructorCourseBuTitle(int instructorId, String title);

    void userPersist(User user);

    void UserDeleteById(User user);

    User userFindById(int id);

    User userFindByUserName(String username);

    List<User> userAll();

    void userMerge(User user);

    List<User> userFindByFirstName(String userName);

    List<Role> userFindByRoles(String userName);

    void removeRoles(String userName);

    void removeRole(Role role);

    void instructorAddCourseSave(Course course);


    List<Student> studentAll();

    List<Student> findStudentByFirstName(String firstName);

    void studentPersist(Student student);

    void updateStudent(Student student);

    Student findStudentById(int id);

    void deleteStudentById(int id);

    void addCourseInStudent(int studentId, int courseId);

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

    void deleteReviewsByCourseId(int courseId);

    List<Review> getAllReviews();

    Review findReviewsById(int id);

    void deleteReviewById(int id);

    List<Course> getCoursesFilteredByStudent(int id);


}
