package uz.smartup.academy.hibernateadvanced.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import uz.smartup.academy.hibernateadvanced.dao.AppDAO;
import uz.smartup.academy.hibernateadvanced.dto.*;
import uz.smartup.academy.hibernateadvanced.entity.Course;
import uz.smartup.academy.hibernateadvanced.entity.Instructor;
import uz.smartup.academy.hibernateadvanced.entity.Student;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{
    private final AppDAO dao;
    private final CourseDTOUtil courseDTOUtil;
    private final ReviewDTOUtil reviewDTOUtil;
    private final StudentDTOUtil studentDTOUtil;

    public CourseServiceImpl(AppDAO dao, CourseDTOUtil courseDTOUtil, ReviewDTOUtil reviewDTOUtil, StudentDTOUtil studentDTOUtil) {
        this.dao = dao;
        this.courseDTOUtil = courseDTOUtil;
        this.reviewDTOUtil = reviewDTOUtil;
        this.studentDTOUtil = studentDTOUtil;
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        return courseDTOUtil.toDTOs(dao.getAllCourses());
    }

    @Override
    public CourseDTO getCourseById(int id) {
        return courseDTOUtil.toDTO(dao.findCourseById(id));
    }

    @Override
    public List<ReviewDTO> getCourseReviewsById(int id) {
        return reviewDTOUtil.toDTOs(dao.getReviewsByCourseId(id));
    }

    @Override
    public List<StudentDTO> getCourseStudentsById(int id) {
        return studentDTOUtil.toDTOs(dao.getStudentsByCourseId(id));
    }

    @Override
    @Transactional
    public void updateCourse(CourseDTO courseDTO, int id) {
        Course course = courseDTOUtil.toEntity(courseDTO);
        Instructor instructor = dao.instructorFindBuId(courseDTO.getInstructorId());
        List<Student> student = dao.getStudentsByCourseId(courseDTO.getId());
        course.setStudents(student);
        course.setInstructor(instructor);
        instructor.addCourse(course);
        dao.updateCourse(course);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
            dao.deleteCourseById(id);
    }
}
