package uz.smartup.academy.hibernateadvanced.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import uz.smartup.academy.hibernateadvanced.dao.AppDAO;
import uz.smartup.academy.hibernateadvanced.dto.*;
import uz.smartup.academy.hibernateadvanced.entity.Course;
import uz.smartup.academy.hibernateadvanced.entity.Review;
import uz.smartup.academy.hibernateadvanced.entity.Student;

import java.util.List;
@Service
public class StudentServiseImpl implements StudentServise{
    private final AppDAO appDAO;
    private final StudentDTOUtil studentDTOUtil;
    private final ReviewDTOUtil reviewDTOUtil;

    private final CourseDTOUtil courseDTOUtil;

    public StudentServiseImpl(AppDAO appDAO, StudentDTOUtil studentDTOUtil, ReviewDTOUtil reviewDTOUtil, CourseDTOUtil courseDTOUtil) {
        this.appDAO = appDAO;
        this.studentDTOUtil = studentDTOUtil;
        this.reviewDTOUtil = reviewDTOUtil;
        this.courseDTOUtil = courseDTOUtil;
    }

    @Override
    public List<StudentDTO> studentAll() {
        return studentDTOUtil.toDTOs(appDAO.studentAll());
    }

    @Override
    public List<StudentDTO> findStudentByFirstName(String firstName) {
        List<Student> students = appDAO.findStudentByFirstName(firstName);

        return studentDTOUtil.toDTOs(students);
    }


    @Override
    @Transactional
    public void deleteStudent(int id) {
        List<Course>courses = appDAO.getStudentCourses(id);
        if(courses!=null){
            for (Course course:courses)
                appDAO.deleteStudentFromCourse(id, course.getId());
            appDAO.deleteStudentById(id);
        }


    }

    @Override
    @Transactional
    public void enrollCourse(int id, int courseId) {
        appDAO.addCourseInStudent(id, courseId);
    }

    @Override
    public List<CourseDTO> getStudentCourses(int id) {
        List<Course> courses = appDAO.getStudentCourses(id);
        return courseDTOUtil.toDTOs(courses);
    }

    @Override
    public List<CourseDTO> getCourseFilteredStudentById(int id) {
        List<Course> courses = appDAO.getCoursesFilteredByStudent(id);
        return courseDTOUtil.toDTOs(courses);
    }

    @Override
    @Transactional
    public void excludeStudentFromCourse(int id, int courseId) {
        appDAO.deleteStudentFromCourse(id, courseId);
    }

    @Override
    @Transactional
    public void addReview(int id, int courseId, ReviewDTO review) {
        appDAO.addStudentReview(id, courseId, reviewDTOUtil.toEntity(review));
    }

    @Override
    public List<ReviewDTO> getReviews(int id, int courseId) {
        List<Review> reviews = appDAO.getStudentReviews(id, courseId);
        return reviewDTOUtil.toDTOs(reviews);
    }

    @Override
    @Transactional
    public void updateReview(int id, int courseId, ReviewDTO review) {
        appDAO.updateStudentReview(id, courseId, reviewDTOUtil.toEntity(review));
    }
}
