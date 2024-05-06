package uz.smartup.academy.hibernateadvanced.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import uz.smartup.academy.hibernateadvanced.dao.AppDAO;
import uz.smartup.academy.hibernateadvanced.dto.CourseDTOUtil;
import uz.smartup.academy.hibernateadvanced.dto.InstructorDTO;
import uz.smartup.academy.hibernateadvanced.dto.CourseDTO;
import uz.smartup.academy.hibernateadvanced.dto.InstructorDTOUtil;
import uz.smartup.academy.hibernateadvanced.entity.Course;
import uz.smartup.academy.hibernateadvanced.entity.Instructor;

import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {
    private final AppDAO dao;
    private final InstructorDTOUtil dtoUtil;
    private final CourseDTOUtil courseDTOUtil;

    public InstructorServiceImpl(AppDAO dao, InstructorDTOUtil dtoUtil, CourseDTOUtil courseDTOUtil) {
        this.dao = dao;
        this.dtoUtil = dtoUtil;
        this.courseDTOUtil = courseDTOUtil;
    }

    @Override
    @Transactional
    public void createInstructor(InstructorDTO instructorDTO) {
        Instructor instructor = dtoUtil.toEntity(instructorDTO);
        dao.save(instructor);
    }

    @Override
    public List<InstructorDTO> getInstructors() {
        List<Instructor> instructors = dao.getAllInstructors();
        return dtoUtil.toEntities(instructors);
    }

    @Override
    public InstructorDTO getInstructor(int id) {
        Instructor instructor = dao.findInstructorById(id);
        return dtoUtil.toDTO(instructor);
    }

    @Override
    @Transactional
    public void updateInstructor(InstructorDTO instructorDTO) {
        Instructor instructor = dtoUtil.toEntity(instructorDTO);
        dao.update(instructor);
    }

    @Override
    @Transactional
    public void deleteInstructor(int id) {
        dao.deleteInstructorById(id);
    }

    @Override
    @Transactional
    public void addCourse(int id, CourseDTO courseDTO) {
        Course course = courseDTOUtil.toEntity(courseDTO);
        course.setInstructor(dao.findInstructorById(id));

        dao.save(course);
    }

    @Override
    public List<CourseDTO> getCourses(int id) {
        List<Course> courses = dao.getAllCourcesByInstructorId(id);

        System.out.println("X " + courses.get(0).getInstructor());

        return courseDTOUtil.toDTOs(courses);
    }
}
