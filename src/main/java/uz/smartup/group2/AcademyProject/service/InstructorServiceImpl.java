package uz.smartup.group2.AcademyProject.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import uz.smartup.group2.AcademyProject.dao.CourseDAO;
import uz.smartup.group2.AcademyProject.dao.InstructorDAO;
import uz.smartup.group2.AcademyProject.dto.*;
import uz.smartup.group2.AcademyProject.entity.Course;
import uz.smartup.group2.AcademyProject.entity.Instructor;
import uz.smartup.group2.AcademyProject.entity.Review;
import uz.smartup.group2.AcademyProject.exception.NotFoundException;
import uz.smartup.group2.AcademyProject.repository.CourseRepository;
import uz.smartup.group2.AcademyProject.repository.InstructorRepository;

import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService{
    private final InstructorRepository instructorRepository;
    private final CourseDAO courseDAO;
    private final CourseDTOUtil courseDTOUtil;
    private final CourseRepository courseRepository;
    private final InstructorDTOUtil instructorDTOUtil;
    private final InstructorDAO instructorDAO;
    private final ReviewDTOUtil reviewDTOUtil;
    public InstructorServiceImpl(InstructorRepository instructorRepository,
                                 CourseDAO courseDAO, CourseDTOUtil courseDTOUtil, CourseRepository courseRepository, InstructorDTOUtil instructorDTOUtil, InstructorDAO instructorDAO, ReviewDTOUtil reviewDTOUtil) {
        this.instructorRepository = instructorRepository;
        this.courseDAO = courseDAO;
        this.courseDTOUtil = courseDTOUtil;
        this.courseRepository = courseRepository;
        this.instructorDTOUtil = instructorDTOUtil;
        this.instructorDAO = instructorDAO;
        this.reviewDTOUtil = reviewDTOUtil;
    }

    @Override
    public Instructor findByEmail(String email) {
        return instructorRepository.findByEmail(email);
    }

    @Override
    public InstructorDTO findById(int id) {
        Instructor instructor = instructorDAO.findInstructorById(id);
        return instructorDTOUtil.toDTO(instructor);
    }

    @Override
    public Instructor save(InstructorDTO instructorDTO) {
        InstructorDTOUtil dtoUtil = new InstructorDTOUtil();
        return instructorRepository.save(dtoUtil.toEntity(instructorDTO));
    }
    @Override
    public List<CourseDTO> getCourses(int id) {
        List<Course> courses = courseDAO.getAllCourcesByInstructorId(id);
        return courseDTOUtil.toDTOList(courses);
    }

    @Override
    @Transactional
    public void addCourse(int id, CourseDTO courseDTO) {
        Instructor instructor = instructorRepository.findById(id).orElse(null);
        if (instructor == null) {
            throw new NotFoundException("Instructor not found with id: " + id);
        }
        Course course = courseDTOUtil.toEntity(courseDTO);
        course.setInstructor(instructor);
        courseRepository.save(course);
    }
    @Override
    @Transactional
    public void update(InstructorDTO instructorDTO) {
            instructorRepository.findById(instructorDTO.getId())
                    .ifPresent(student -> {
                        student.setFirstName(instructorDTO.getFirstName());
                        student.setLastName(instructorDTO.getLastName());
                        student.setEmail(instructorDTO.getEmail());
                    });
    }
    @Override
    @Transactional
    public void deleteById(int id) {
        instructorDAO.deleteById(id);
    }

    @Override
    public List<ReviewDTO> getAllCourseReviews(int courseId) {
        List<Review> reviews = instructorDAO.getAllCourseReviews(courseId);
        return reviewDTOUtil.toDTOList(reviews);
    }

    @Override
    public ReviewDTO getById(int id) {
        Review review = instructorDAO.getById(id);
        return reviewDTOUtil.toDTO(review);
    }
}
