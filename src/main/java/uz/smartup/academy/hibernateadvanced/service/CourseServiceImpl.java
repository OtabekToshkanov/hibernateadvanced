package uz.smartup.academy.hibernateadvanced.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import uz.smartup.academy.hibernateadvanced.dto.*;
import uz.smartup.academy.hibernateadvanced.dao.AppDAO;
import uz.smartup.academy.hibernateadvanced.entity.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CourseServiceImpl implements CourseService {
    private final AppDAO dao;

    private final CourseDTOUtil courseDTOUtil;
    private final ReviewDTOUtil reviewDTOUtil;
    private final StudentDTOUtil studentDTOUtil;

    private final UserService userService;

    public CourseServiceImpl(AppDAO dao, CourseDTOUtil courseDTOUtil, ReviewDTOUtil reviewDTOUtil, StudentDTOUtil studentDTOUtil, UserService userService) {
        this.dao = dao;
        this.courseDTOUtil = courseDTOUtil;
        this.reviewDTOUtil = reviewDTOUtil;
        this.studentDTOUtil = studentDTOUtil;
        this.userService = userService;
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
    public void updateCourse(CourseDTO courseDTO) {
        System.out.println(courseDTO.getId());

        Course course = dao.findCourseById(courseDTO.getId());
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setPrice(courseDTO.getPrice());

//        Course course = courseDTOUtil.toEntity(courseDTO);
//        Instructor instructor = dao.findInstructorById(courseDTO.getInstructorId());
//        course.setInstructor(instructor);
//        course.setStudents(dao.getStudentsByCourseId(course.getId()));

        dao.updateCourse(course);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
//        Course course = dao.findCourseById(id);

        dao.deleteReviewsByCourseId(id);
//        List<Student> students = dao.getStudentsByCourseId(id);
//        students.forEach(course::removeStudent);

        dao.deleteCourseById(id);
    }

    @Override
    @Transactional
    public void addStudent(int id, StudentDTO studentDTO) {
        Student student = studentDTOUtil.toEntity(studentDTO);

        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setRole("ROLE_STUDENT");
        role.setUsername(student.getUser().getUsername());
        roles.add(role);

        student.addCourse(dao.findCourseById(id));
        userService.registerUser(student.getUser(), roles);

        dao.save(student);
    }
}