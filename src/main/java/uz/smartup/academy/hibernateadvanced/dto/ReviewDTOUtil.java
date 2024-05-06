package uz.smartup.academy.hibernateadvanced.dto;

import org.springframework.stereotype.Component;
import uz.smartup.academy.hibernateadvanced.dao.AppDAO;
import uz.smartup.academy.hibernateadvanced.entity.Course;
import uz.smartup.academy.hibernateadvanced.entity.Review;
import uz.smartup.academy.hibernateadvanced.entity.Student;

import java.util.List;

@Component
public class ReviewDTOUtil {

    private final AppDAO dao;

    public ReviewDTOUtil(AppDAO dao) {
        this.dao = dao;
    }

    public ReviewDTO toDTO(Review review) {
        ReviewDTO dto = new ReviewDTO();
        dto.setId(review.getId());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        dto.setCourseId(review.getCourse().getId());
        dto.setStudentId(review.getStudent().getId());
        return dto;
    }

    public Review toEntity(ReviewDTO dto) {
        Review review = new Review();
        review.setId(dto.getId());
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());

        Course course = dao.findCourseById(dto.getCourseId());

        review.setCourse(course);

        Student student = dao.findStudentById(dto.getStudentId());
        
        review.setStudent(student);




        return review;
    }
    public List<ReviewDTO> toDTOs(List<Review> reviews) {
        return reviews.stream().map(this::toDTO).toList();
    }
}
