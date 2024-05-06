package uz.smartup.group2.AcademyProject.dto;


import org.springframework.stereotype.Component;
import uz.smartup.group2.AcademyProject.entity.Course;
import uz.smartup.group2.AcademyProject.entity.Review;

import java.util.List;

@Component
public class ReviewDTOUtil {
    public Review toEntity(ReviewDTO reviewDTO){
        Review review = new Review();
        review.setId(reviewDTO.getId());
        review.setRating(reviewDTO.getRating());
        review.setComment(reviewDTO.getComment());
        review.setStudentId(reviewDTO.getStudentId());
        review.setCourseId(reviewDTO.getCourseId());
        return review;
    }
    public ReviewDTO toDTO(Review review){
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setRating(review.getRating());
        reviewDTO.setComment(review.getComment());
        reviewDTO.setStudentId(review.getStudentId());
        reviewDTO.setCourseId(review.getCourseId());
        return reviewDTO;
    }
    public List<ReviewDTO> toDTOList(List<Review> reviews) {
        return reviews.stream().map(this::toDTO).toList();
    }
}
