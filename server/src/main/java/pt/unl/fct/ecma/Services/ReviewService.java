package pt.unl.fct.ecma.Services;

import pt.unl.fct.ecma.repositories.ReviewRepository;

public class ReviewService {
    private ReviewRepository reviewRepository;

    public  ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }
}
