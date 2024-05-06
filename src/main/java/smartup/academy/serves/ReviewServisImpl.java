package smartup.academy.serves;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import smartup.academy.dao.AppDAO;
import smartup.academy.dto.ReviewDTO;
import smartup.academy.dto.ReviewDTOUtil;
import smartup.academy.entity.Review;

import java.util.List;

@Service
public class ReviewServisImpl implements ReviewServis{
    AppDAO appDAO;
    ReviewDTOUtil reviewDTOUtil;

    public ReviewServisImpl(AppDAO appDAO, ReviewDTOUtil reviewDTOUtil) {
        this.appDAO = appDAO;
        this.reviewDTOUtil = reviewDTOUtil;
    }

    @Override
    public List<ReviewDTO> reviewAll() {
        List<Review> reviews=appDAO.reviewAll();
        return reviewDTOUtil.toDTOS(reviews);
    }

    @Override
    public ReviewDTO reviewFindById(int id) {
        return reviewDTOUtil.toDTO(appDAO.reviewFindById(id));
    }

    @Transactional
    @Override
    public void reviewDeleteById(int id) {
        appDAO.reviewDeleteById(id);
    }
}
