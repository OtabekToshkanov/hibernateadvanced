package uz.smartup.group2.AcademyProject.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import uz.smartup.group2.AcademyProject.dao.StudentDAO;
import uz.smartup.group2.AcademyProject.dto.*;
import uz.smartup.group2.AcademyProject.entity.Course;
import uz.smartup.group2.AcademyProject.entity.Student;
import uz.smartup.group2.AcademyProject.repository.StudentRepository;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;
    StudentDTOUtil dtoUtil = new StudentDTOUtil();
    private final StudentDAO studentDAO;
    private final CourseDTOUtil courseDTOUtil;
    private final StudentDTOUtil studentDTOUtil;
    private final ReviewDTOUtil reviewDTOUtil;

    public StudentServiceImpl(StudentRepository studentRepository, StudentDAO studentDAO, CourseDTOUtil courseDTOUtil, StudentDTOUtil studentDTOUtil, ReviewDTOUtil reviewDTOUtil) {
        this.studentRepository = studentRepository;
        this.studentDAO = studentDAO;
        this.courseDTOUtil = courseDTOUtil;
        this.studentDTOUtil = studentDTOUtil;
        this.reviewDTOUtil = reviewDTOUtil;
    }
    @Override
    public Student findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }
    @Override
    public Student save(StudentDTO studentDTO) {
        return studentRepository.save(dtoUtil.toEntity(studentDTO));
    }
    @Override
    public StudentDTO findById(int id) {
        Student student = studentRepository.findById(id).orElse(null);
        return dtoUtil.toDTO(student);
    }

    @Override
    @Transactional
    public void update(StudentDTO studentDTO) {
        studentRepository.findById(studentDTO.getId())
                .ifPresent(student -> {
                    student.setFirstName(studentDTO.getFirstName());
                    student.setLastName(studentDTO.getLastName());
                    student.setEmail(studentDTO.getEmail());
                });
    }

    @Override
    public List<CourseDTO> getStudentCourses(int id) {
        List<Course> courses = studentDAO.getStudentCourses(id);
        return courseDTOUtil.toDTOList(courses);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        studentDAO.deleteById(id);
    }

    @Override
    @Transactional
    public void addCourse(int courseId, int studentId) {
        studentDAO.addCourse(courseId, studentId);
    }

    @Override
    public void removeCourseFromStudent(int courseId, int studentId) {
        studentDAO.removeCourseFromStudent(studentId, courseId);
    }

    @Override
    public void addReviewToCourseFromStudent(ReviewDTO reviewDTO, int courseId, int studentId) {
        studentDAO.addReviewToCourseFromStudent(reviewDTOUtil.toEntity(reviewDTO), courseId, studentId);
    }

}
