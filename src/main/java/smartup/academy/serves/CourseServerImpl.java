package smartup.academy.serves;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import smartup.academy.dao.AppDAO;
import smartup.academy.dto.*;
import smartup.academy.entity.Course;
import smartup.academy.entity.Instructor;
import smartup.academy.entity.Review;
import smartup.academy.entity.Student;

import java.util.List;
@Service
public class CourseServerImpl implements CourseServer{
    CourseDTOUtil courseDTOUtil;
    AppDAO appDAO;
    StudentDTOUtil studentDTOUtil;
    ReviewDTOUtil reviewDTOUtil;

    public CourseServerImpl(CourseDTOUtil courseDTOUtil,AppDAO appDAO,StudentDTOUtil studentDTOUtil,ReviewDTOUtil reviewDTOUtil) {
        this.courseDTOUtil = courseDTOUtil;
        this.appDAO=appDAO;
        this.studentDTOUtil=studentDTOUtil;
        this.reviewDTOUtil=reviewDTOUtil;
    }

    @Override
    public CourseDTO courseFindByid(int id) {
        return courseDTOUtil.toDTO(appDAO.courseFindByid(id));
    }

    @Override
    public List<CourseDTO> courseAll() {
        return courseDTOUtil.toDTOs(appDAO.courseAll());
    }

    @Override
    public List<ReviewDTO> courseAllReview(int id) {
        return reviewDTOUtil.toDTOS(appDAO.courseAllReview(id));
    }

    @Override
    public List<StudentDTO> courseAllStudent(int id) {
        List<Student> students=appDAO.courseAllStudent(id);
        return studentDTOUtil.toDTOs(students);
    }

    @Transactional
    @Override
    public void courseMerge(CourseDTO courseDTO) {
        Course course= appDAO.courseFindByid(courseDTO.getId());
      appDAO.courseMerge(courseDTOUtil.toEntityMerg(course,courseDTO));
    }

    @Transactional
    @Override
    public void courseDelete(int id) {
        appDAO.courseDelete(id);
    }
}
