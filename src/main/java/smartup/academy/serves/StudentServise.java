package smartup.academy.serves;

import org.springframework.stereotype.Service;
import smartup.academy.dto.CourseDTO;
import smartup.academy.dto.ReviewDTO;
import smartup.academy.dto.StudentDTO;
import smartup.academy.entity.Course;
import smartup.academy.entity.Student;

import java.util.List;

public interface StudentServise {

    public List<StudentDTO>  studentAll();

    void studentMerge(StudentDTO studentDTO);

    StudentDTO studentFindById(int id);

    List<CourseDTO> studentAllCourse(int id);

    void studentAddCourse(int studentId,int courseId);

    List<CourseDTO> filterAllCourse(int id);


    void studentAddReview(ReviewDTO reviewDTO);

    List<ReviewDTO> studentAllReview(int studentId, int courseId);

    void studentMergeReview(ReviewDTO reviewDTO);

    void deleteCourseById(int studentId,int id);

    void studentRemoveById(int id);

    StudentDTO studentFindByUserId(int id);

}
