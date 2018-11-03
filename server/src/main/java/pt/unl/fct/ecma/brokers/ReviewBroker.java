package pt.unl.fct.ecma.brokers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pt.unl.fct.ecma.models.Proposal;
import pt.unl.fct.ecma.models.Review;
import pt.unl.fct.ecma.services.ProposalService;
import pt.unl.fct.ecma.services.ReviewService;

@Service
public class ReviewBroker {

    private ReviewService rService;
    private ProposalService pService;


    public void updateReview(Review review) {
        rService.updateReview(review);
    }

    public Page<Review> getProposalReviews(Long proposalId, Pageable pageable) {
        Proposal proposal = pService.findById(proposalId);
        return rService.getProposalReviews(proposal, pageable);
    }

    public void deleteReview(Long proposalId, Long reviewId) {
        Proposal proposal = pService.findById(proposalId);
        Review review = rService.findById(reviewId);
        rService.deleteReview(review);
    }

    public void addReview(Review review) {
        Proposal proposal = pService.findById(review.getProposal().getId());
        rService.findById(review.getId());
        rService.addReview(review);

    }
}
