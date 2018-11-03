package pt.unl.fct.ecma.services;

import org.springframework.stereotype.Service;
import pt.unl.fct.ecma.repositories.ReviewRepository;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;

    public  ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }
}
