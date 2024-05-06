package smartup.academy.serves;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import smartup.academy.dao.AppDAO;
import smartup.academy.dto.*;
import smartup.academy.entity.Course;
import smartup.academy.entity.Review;
import smartup.academy.entity.Student;

import java.util.List;
@Service
public class StudentServiseImpl implements StudentServise{
    AppDAO appDAO;
    StudentDTOUtil studentDTOUtil;
    CourseDTOUtil courseDTOUtil;
    ReviewDTOUtil reviewDTOUtil;
    ReviewServis reviewServis;

    public StudentServiseImpl(AppDAO appDAO, StudentDTOUtil studentDTOUtil,CourseDTOUtil courseDTOUtil,ReviewDTOUtil reviewDTOUtil,ReviewServis reviewServis) {
        this.appDAO = appDAO;
        this.studentDTOUtil = studentDTOUtil;
        this.courseDTOUtil=courseDTOUtil;
        this.reviewDTOUtil=reviewDTOUtil;
        this.reviewServis=reviewServis;
    }

    @Override
    public List<StudentDTO> studentAll() {
        return studentDTOUtil.toDTOs(appDAO.studentAll());
    }

    @Transactional
    @Override
    public void studentMerge(StudentDTO studentDTO) {
        Student student= appDAO.studentFindById(studentDTO.getId());
        appDAO.studentMerge(studentDTOUtil.toEntityMerge(student,studentDTO));
    }

    @Override
    public StudentDTO studentFindById(int id) {
      return  studentDTOUtil.toDTO(appDAO.studentFindById(id));
    }

    @Transactional
    @Override
    public void studentAddCourse(int studentId,int courseId) {
        appDAO.studentAddCourse(studentId,courseId);
    }

    @Override
    public List<CourseDTO> studentAllCourse(int id) {
        return courseDTOUtil.toDTOs(appDAO.studentAllCourse(id));
    }

    @Override
    public List<CourseDTO> filterAllCourse(int id) {
        return courseDTOUtil.toDTOs(appDAO.courseAllFilterChenning(id));
    }

    @Transactional
    @Override
    public void studentAddReview(ReviewDTO reviewDTO) {
        Review review=reviewDTOUtil.toEntity(reviewDTO);
        review.setStudent(appDAO.studentFindById(reviewDTO.getStudent_id()));
        review.setCourse(appDAO.courseFindByid(reviewDTO.getCourse_id()));
        appDAO.studentAddReview(review);
    }

    @Override
    public List<ReviewDTO> studentAllReview(int studentId, int courseId) {
        return reviewDTOUtil.toDTOS(appDAO.studentCourseAllReview(studentId,courseId));
    }

    @Transactional
    @Override
    public void studentMergeReview(ReviewDTO reviewDTO) {
        Review review=appDAO.reviewFindById(reviewDTO.getId());
        appDAO.studentMergeReview(reviewDTOUtil.toEntityMerge(review,reviewDTO));
    }

    @Transactional
    @Override
    public void deleteCourseById(int studentId,int id) {
        appDAO.studentCourseDelete(studentId,id);
    }

    @Transactional
    @Override
    public void studentRemoveById(int id) {
        appDAO.studentRemoveById(id);
    }

    @Override
    public StudentDTO studentFindByUserId(int id) {
        return studentDTOUtil.toDTO(appDAO.studentFindByUserId(id));
    }

}
