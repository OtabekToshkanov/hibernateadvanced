package smartup.academy.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import smartup.academy.serves.ReviewServis;


@Controller
@RequestMapping("api/reviews")
public class ReviewController {
    ReviewServis reviewServis;

    public ReviewController(ReviewServis reviewServis) {
        this.reviewServis = reviewServis;
    }

    @GetMapping
    public String reviewController(Model model){
        model.addAttribute("reviewDTOs",reviewServis.reviewAll());
        return "review/review-form2.html";
    }
}
