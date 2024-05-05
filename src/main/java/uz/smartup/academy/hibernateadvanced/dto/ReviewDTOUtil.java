package uz.smartup.academy.hibernateadvanced.dto;

import org.springframework.stereotype.Component;
import uz.smartup.academy.hibernateadvanced.entity.Review;

import java.util.List;

@Component
public class ReviewDTOUtil {
    public ReviewDTO toDTO(Review review) {
        ReviewDTO dto = new ReviewDTO.Builder()
                .id(review.getId())
                .comment(review.getComment())
                .rating(review.getRating())
                .build();

        return dto;
    }

    public Review toEntity(ReviewDTO dto) {
        Review review = new Review();
        review.setId(dto.getId());
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        return review;
    }

    public List<ReviewDTO> toDTOs(List<Review> reviews) {
        return reviews.stream().map(this::toDTO).toList();
    }
}
