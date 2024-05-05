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
import uz.smartup.academy.hibernateadvanced.entity.Role;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class InstructorServiceImpl implements InstructorService {
    private final AppDAO dao;
    private final InstructorDTOUtil dtoUtil;
    private final CourseDTOUtil courseDTOUtil;

    private final UserService userService;

    public InstructorServiceImpl(AppDAO dao, InstructorDTOUtil dtoUtil, CourseDTOUtil courseDTOUtil, UserService userService) {
        this.dao = dao;
        this.dtoUtil = dtoUtil;
        this.courseDTOUtil = courseDTOUtil;
        this.userService = userService;
    }

    @Override
    @Transactional
    public void createInstructor(InstructorDTO instructorDTO) {
        Instructor instructor = dtoUtil.toEntity(instructorDTO);

        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setRole("ROLE_INSTRUCTOR");
//        role.setUsername(instructor.getUser().getUsername());
        roles.add(role);

        userService.registerUser(instructor.getUser(), roles);

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
        Instructor instructor = dao.findInstructorById(instructorDTO.getId());

        instructor.getUser().setFirstName(instructorDTO.getFirstName());
        instructor.getUser().setLastName(instructorDTO.getLastName());
        instructor.getUser().setEmail(instructorDTO.getEmail());
        instructor.getInstructorDetail().setYoutubeChannel(instructorDTO.getYoutubeChannel());
        instructor.getInstructorDetail().setHobby(instructorDTO.getHobby());

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
//        courseDTO.setInstructorId(id);
        course.setInstructor(dao.findInstructorById(id));
        dao.save(course);
    }

    @Override
    public List<CourseDTO> getCourses(int id) {
        List<Course> courses = dao.getAllCourcesByInstructorId(id);

        return courseDTOUtil.toDTOs(courses);
    }
}
