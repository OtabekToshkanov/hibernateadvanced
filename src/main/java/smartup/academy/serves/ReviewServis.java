package smartup.academy.serves;

import smartup.academy.dto.ReviewDTO;
import smartup.academy.entity.Review;

import java.util.List;

/*
4. /api/reviews endpoint yarating
  GET    - /api/reviews: Hamma sharhlar olish
  GET    - /api/reviews/{reviewId}: sharhni id orqali olish
  DELETE - /api/reviews/{reviewId}: sharhni o'chirish*/
public interface ReviewServis {
    List<ReviewDTO> reviewAll();
    ReviewDTO reviewFindById(int id);
    void reviewDeleteById(int id);
}
