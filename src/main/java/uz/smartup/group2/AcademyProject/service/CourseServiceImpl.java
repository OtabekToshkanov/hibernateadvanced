package uz.smartup.group2.AcademyProject.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import uz.smartup.group2.AcademyProject.dao.CourseDAO;
import uz.smartup.group2.AcademyProject.dto.CourseDTO;
import uz.smartup.group2.AcademyProject.dto.CourseDTOUtil;
import uz.smartup.group2.AcademyProject.dto.InstructorDTO;
import uz.smartup.group2.AcademyProject.entity.Course;
import uz.smartup.group2.AcademyProject.entity.Instructor;
import uz.smartup.group2.AcademyProject.repository.CourseRepository;
import uz.smartup.group2.AcademyProject.repository.InstructorRepository;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CourseDAO courseDAO;
    CourseDTOUtil courseDTOUtil = new CourseDTOUtil();

    public CourseServiceImpl(CourseRepository courseRepository, CourseDAO courseDAO) {
        this.courseRepository = courseRepository;
        this.courseDAO = courseDAO;
    }

    @Override
    public void save(CourseDTO courseDTO) {
        courseRepository.save(courseDTOUtil.toEntity(courseDTO));
    }

    @Override
    public Course getById(int id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void update(int courseId, String courseName, String description, String duration, int courseFee, String courseFormat, String category) {
        courseDAO.updateCourse(courseId, courseName, description, duration, courseFee, courseFormat, category);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
       courseDAO.deleteCourseById(id);
    }
}
