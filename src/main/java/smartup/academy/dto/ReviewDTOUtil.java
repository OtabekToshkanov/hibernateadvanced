package smartup.academy.dto;

import org.springframework.stereotype.Component;
import smartup.academy.entity.Review;

import java.util.List;

@Component
public class ReviewDTOUtil {
    public Review toEntity(ReviewDTO reviewDTO){
        Review review=new Review();
        review.setId(reviewDTO.getId());
        review.setComment(reviewDTO.getComment());
        review.setRating(reviewDTO.getRating());
        return  review;
    }

    public ReviewDTO toDTO(Review review){
        ReviewDTO reviewDTO=new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setUserName(review.getStudent().getUser().getUserName());
        reviewDTO.setComment(review.getComment());
        reviewDTO.setRating(review.getRating());
        reviewDTO.setCourse_id(review.getCourse().getId());
        reviewDTO.setStudent_id(review.getStudent().getId());
        return reviewDTO;
    }

    public List<ReviewDTO> toDTOS(List<Review> reviews){
        return reviews.stream()
                .map(this::toDTO)
                .toList();
    }

    public Review toEntityMerge(Review review, ReviewDTO reviewDTO) {
        review.setId(reviewDTO.getId());
        review.setComment(reviewDTO.getComment());
        review.setRating(reviewDTO.getRating());
        return  review;
    }
}
