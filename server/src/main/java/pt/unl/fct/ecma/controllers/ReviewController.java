package pt.unl.fct.ecma.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.ecma.api.ReviewsApi;
import pt.unl.fct.ecma.brokers.ReviewBroker;
import pt.unl.fct.ecma.errors.BadRequestException;
import pt.unl.fct.ecma.models.Review;
import pt.unl.fct.ecma.security.annotations.BelongsToProposalTeam;
import pt.unl.fct.ecma.security.annotations.CanAddReview;
import pt.unl.fct.ecma.security.annotations.CanModifyReview;

@RestController
public class ReviewController implements ReviewsApi {

    @Autowired
    private ReviewBroker reviewBroker;

    @CanModifyReview
    @Override
    public void updateReview(Review review, Long reviewId, Long proposalId) {

        if (!review.getProposal().getId().equals(proposalId))
            throw new BadRequestException("Ids of proposal do not match");
        if (!review.getId().equals(reviewId))
            throw new BadRequestException("Ids of review do not match");

        reviewBroker.updateReview(review);
    }

    @BelongsToProposalTeam
    @Override
    public Page<Review> getProposalReviews(Pageable pageable, Long proposalId) {
        return reviewBroker.getProposalReviews(proposalId, pageable);
    }

    @CanModifyReview
    @Override
    public void deleteReview(Long proposalId, Long reviewId) {
        reviewBroker.deleteReview(proposalId, reviewId);
    }

    @CanAddReview
    @Override
    public void addReview(Long proposalId, Review review) {

        if (review.getId() != null)
            throw new BadRequestException("Cannot submit id of review");
        if (!review.getProposal().getId().equals(proposalId))
            throw new BadRequestException("Ids of proposal do not match");

        reviewBroker.addReview(review);
    }

}
