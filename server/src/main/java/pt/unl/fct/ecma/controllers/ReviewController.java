package pt.unl.fct.ecma.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.ecma.api.ReviewsApi;
import pt.unl.fct.ecma.brokers.ReviewBroker;
import pt.unl.fct.ecma.errors.BadRequestException;
import pt.unl.fct.ecma.models.Review;

import javax.validation.Valid;

@RestController
public class ReviewController implements ReviewsApi {

    @Autowired
    private ReviewBroker rbroker;

    //eu
    @Override
    public void updateReview(@Valid @RequestBody Review review, @PathVariable("reviewId") Long reviewId,
                             @PathVariable("proposalId") Long proposalId) {

        if (!review.getProposal().getId().equals(proposalId))
            throw new BadRequestException("Ids of proposal do not match");
        if (!review.getId().equals(reviewId))
            throw new BadRequestException("Ids of review do not match");

        rbroker.updateReview(review);
    }

    //pertence à team da proposal
    @Override
    public Page<Review> getProposalReviews(Pageable pageable, @PathVariable("id") Long proposalId) {
        return rbroker.getProposalReviews(proposalId, pageable);
    }

    //eu
    @Override
    public void deleteReview(@PathVariable("proposalId") Long proposalId, @PathVariable("reviewid") Long reviewId) {
        rbroker.deleteReview(proposalId, reviewId);
    }

    //pertence à team daquela proposal e bid tem de estar approved
    @Override
    public void addReview(@PathVariable("id") Long proposalId, @Valid @RequestBody Review review) {

        if(review.getId() != null)
            throw new BadRequestException("Cannot submit id of review");
        if (!review.getProposal().getId().equals(proposalId))
            throw new BadRequestException("Ids of proposal do not match");

        rbroker.addReview(review);
    }

}
