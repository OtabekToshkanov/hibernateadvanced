package uz.smartup.academy.hibernateadvanced.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import uz.smartup.academy.hibernateadvanced.dao.AppDAO;
import uz.smartup.academy.hibernateadvanced.dto.ReviewDTO;
import uz.smartup.academy.hibernateadvanced.dto.ReviewDTOUtil;
import uz.smartup.academy.hibernateadvanced.entity.Review;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{
    private final AppDAO dao;
    private final ReviewDTOUtil reviewDTOUtil;

    public ReviewServiceImpl(AppDAO dao, ReviewDTOUtil reviewDTOUtil) {
        this.dao = dao;
        this.reviewDTOUtil = reviewDTOUtil;
    }

    @Override
    public List<ReviewDTO> getAllRewiews() {
        List<Review> reviews = dao.getAllReviews();
        return reviewDTOUtil.toDTOs(reviews);
    }

    @Override
    public ReviewDTO getReviewsById(int id) {
        Review review = dao.findReviewsById(id);
        return reviewDTOUtil.toDTO(review);
    }

    @Override
    @Transactional
    public void deleteReviewById(int id) {
        dao.deleteReviewById(id);
    }
}
