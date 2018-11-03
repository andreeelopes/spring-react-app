package pt.unl.fct.ecma.controllers;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.ecma.api.ReviewsApi;
import pt.unl.fct.ecma.models.Review;

import javax.validation.Valid;

@RestController
public class ReviewController implements ReviewsApi {


    @Override
    public void updateReview(@Valid Review review, Long reviewid, Long id) {

    }

    @Override
    public Page<Review> getProposalReviews(Pageable pageable, Long id) {
        return null;
    }


    @Override
    public void deleteReview(@PathVariable("id") Long id, @PathVariable("reviewid") Long reviewid) {
        proposalService.deleteReview(id, reviewid);
    }

    @Override
    public void addReview(@PathVariable Long id, @Valid @RequestBody Review review) {
        proposalService.addReview(id, review);
    }

}
