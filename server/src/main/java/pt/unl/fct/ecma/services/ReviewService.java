package pt.unl.fct.ecma.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pt.unl.fct.ecma.errors.NotFoundException;
import pt.unl.fct.ecma.models.Proposal;
import pt.unl.fct.ecma.models.Review;
import pt.unl.fct.ecma.repositories.ProposalRepository;
import pt.unl.fct.ecma.repositories.ReviewRepository;

import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository rRepo;

    public void updateReview(Review review) {
        Review realReview = findById(review.getId());
        realReview.setScore(review.getScore());
        realReview.setText(review.getText());
        rRepo.save(realReview);
    }

    public Page<Review> getProposalReviews(Proposal proposal, Pageable pageable) {
        return rRepo.findAllProposalsBy_Id(proposal.getId(), pageable);
    }

    public Review findById(Long reviewId) {
        Optional<Review> reviewOpt = rRepo.findById(reviewId);
        if (reviewOpt.isPresent()) {
            return reviewOpt.get();
        } else
            throw new NotFoundException("Review does not exist");
    }


    public void deleteReview(Review review) {
        rRepo.delete(review);
    }


    public void addReview(Review review) {
        rRepo.save(review);
    }

}
