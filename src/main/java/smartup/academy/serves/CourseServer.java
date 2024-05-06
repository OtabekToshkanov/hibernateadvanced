package smartup.academy.serves;

import org.springframework.web.bind.annotation.GetMapping;
import smartup.academy.dto.CourseDTO;
import smartup.academy.dto.ReviewDTO;
import smartup.academy.dto.StudentDTO;
import smartup.academy.entity.Review;
import smartup.academy.entity.Student;

import java.util.List;

/*
3. /api/courses endpoint yarating
  GET    - /api/courses: Hamma kurslarni olish
  GET    - /api/courses/{courseId}: kursni id orqali olish
  GET    - /api/courses/{courseId}/reviews: kursning barcha sharhlarini olish
  GET    - /api/courses/{courseId}/students: kursning barcha talabalarini olish
  PUT    - /api/courses: kursni yangilash
  DELETE - /api/courses/{courseId}: kursni o'chirish*/
public interface CourseServer {

    public CourseDTO courseFindByid(int id);
    public List<CourseDTO> courseAll();
    public List<ReviewDTO> courseAllReview(int id);
    public List<StudentDTO> courseAllStudent(int id);
    public void courseMerge(CourseDTO courseDTO);
    public void courseDelete(int id);

}
