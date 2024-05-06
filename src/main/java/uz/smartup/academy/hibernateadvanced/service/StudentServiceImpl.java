package uz.smartup.academy.hibernateadvanced.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import uz.smartup.academy.hibernateadvanced.dao.AppDAO;
import uz.smartup.academy.hibernateadvanced.dto.*;
import uz.smartup.academy.hibernateadvanced.dto.StudentDTOUtil;
import uz.smartup.academy.hibernateadvanced.entity.Course;
import uz.smartup.academy.hibernateadvanced.entity.Review;
import uz.smartup.academy.hibernateadvanced.entity.Student;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final AppDAO dao;
    private final StudentDTOUtil dtoUtil;
    private final CourseDTOUtil courseDTOUtil;
    private final ReviewDTOUtil reviewDTOUtil;

    public StudentServiceImpl(AppDAO dao, StudentDTOUtil dtoUtil, CourseDTOUtil courseDTOUtil, ReviewDTOUtil reviewDTOUtil) {
        this.dao = dao;
        this.dtoUtil = dtoUtil;
        this.courseDTOUtil = courseDTOUtil;
        this.reviewDTOUtil = reviewDTOUtil;
    }

    @Override
    @Transactional
    public void createStudent(StudentDTO StudentDTO) {
        Student student = dtoUtil.toEntity(StudentDTO);
        dao.save(student);
    }

    @Override
    public List<StudentDTO> getStudents() {
        List<Student> students = dao.getAllStudents();
        return dtoUtil.toDTOs(students);
    }

    @Override
    public StudentDTO getStudent(int id) {
        Student Student = dao.findStudentById(id);
        return dtoUtil.toDTO(Student);
    }

    @Override
    @Transactional
    public void updateStudent(StudentDTO StudentDTO) {
        Student student = dtoUtil.toEntity(StudentDTO);
        dao.update(student);
    }

    @Override
    @Transactional
    public void deleteStudent(int id) {
        dao.deleteStudentById(id);
    }

    @Override
    @Transactional
    public void enrollCourse(int id, int courseId) {
        dao.addStudentToCourse(id, courseId);
    }

    @Override
    public List<CourseDTO> getStudentCourses(int id) {
        List<Course> courses = dao.getStudentCourses(id);
        return courseDTOUtil.toDTOs(courses);
    }
    @Override
    public List<CourseDTO> anotherCourses(int id) {
        List<Course> courses = dao.anotherCourses(id);
        return courseDTOUtil.toDTOs(courses);
    }

    @Override
    @Transactional
    public void excludeStudentFromCourse(int id, int courseId) {
        dao.deleteStudentFromCourse(id, courseId);
    }

    @Override
    @Transactional
    public void addReview(int id, int courseId, ReviewDTO review) {
        dao.addStudentReview(id, courseId, reviewDTOUtil.toEntity(review));
    }

    @Override
    public List<ReviewDTO> getReviews(int id, int courseId) {
        List<Review> reviews = dao.getStudentReviews(id, courseId);
        return reviewDTOUtil.toDTOs(reviews);
    }

    @Override
    @Transactional
    public void updateReview(int id, int courseId, ReviewDTO review) {
        dao.updateStudentReview(id, courseId, reviewDTOUtil.toEntity(review));
    }
}
