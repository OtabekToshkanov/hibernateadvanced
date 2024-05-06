package smartup.academy.serves;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import smartup.academy.dao.AppDAO;
import smartup.academy.dto.*;
import smartup.academy.entity.Course;
import smartup.academy.entity.Instructor;

import java.util.List;

/*
1. /api/instructors endpoint yarating
POST   - /api/instructors: Yangi instructor yaratish
GET    - /api/instructors: Hamma instructor'larni olish
GET    - /api/instructors/{instructorId}: instructor'ni id orqali olish
PUT    - /api/instructors: instructor'ni yangilash
DELETE - /api/instructors/{instructorId}: instructor'ni o'chirish
POST   - /api/instructors/{instructorId}/courses: Yangi course yaratish
GET    - /api/instructors/{instructorId}/courses: instructor'ning barcha kurslarini olish
*/
@Service
public class InstructorServisImpl implements InstructorServis{

   InstructorDTOUtil instructorDTOUtil;
   AppDAO appDAO;
   CourseDTOUtil courseDTOUtil;
    public InstructorServisImpl(InstructorDTOUtil instructorDTOUtil, AppDAO appDAO, CourseDTOUtil courseDTOUtil) {
        this.instructorDTOUtil = instructorDTOUtil;
        this.appDAO = appDAO;
        this.courseDTOUtil=courseDTOUtil;
    }

    public InstructorDTO instructorFindById(int id){
        InstructorDTO instructorDTO=instructorDTOUtil.toDTO(appDAO.instructorFindBuId(id));
        if(instructorDTO==null)
            return null;
        return instructorDTO;
    }

    @Override
    public List<InstructorDTO> instructorAll() {
       List<Instructor> instructors=appDAO.instructorAll();
        return instructorDTOUtil.toDTOS(instructors);
    }

    @Transactional
    @Override
    public void instructorMerge(InstructorDTO instructorDTO) {
        Instructor instructor=appDAO.instructorFindBuId(instructorDTO.getId());
        appDAO.instructorMerge(instructorDTOUtil.toEntity(instructor,instructorDTO));

    }

    @Transactional
    @Override
    public void instructorPersist(InstructorDTO instructorDTO) {
        appDAO.instructorPersist(instructorDTOUtil.toEntity(instructorDTO));
    }

    @Transactional
    @Override
    public void instructorDeleteById(int id) {
        appDAO.instructorDeleteById(id);
    }

    @Override
    public void instructorAddCourse(CourseDTO courseDTO) {
//        Course course=
//        appDAO.instructorAddCourse();
    }

    @Override
    public List<CourseDTO> instructorAllCourse(int id) {
        List<Course> courses=appDAO.instructorAllCourse(id);
        return courseDTOUtil.toDTOs(courses);
    }

    @Transactional
    @Override
    public void instructorAddCourseSave(CourseDTO courseDTO) {
        Course course=courseDTOUtil.toEntity(courseDTO);
        Instructor instructor=appDAO.instructorFindBuId(courseDTO.getInstructorId());
        course.setInstructor(instructor);
        appDAO.instructorAddCourseSave(course);
    }

    @Override
    public List<InstructorDTO> instructorSearch(String a) {
        return instructorDTOUtil.toDTOS(appDAO.instructorSearch(a));
    }

    @Override
    public InstructorDTO instructorFindByUserId(int id) {
        return instructorDTOUtil.toDTO(appDAO.instructorFindByUserId(id));
    }
}
