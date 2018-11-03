package pt.unl.fct.ecma.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pt.unl.fct.ecma.models.Review;


public interface ReviewRepository extends CrudRepository<Review,Long> {

    @Query("SELECT r FROM Review r where r.proposal.id = :proposalId")
    Page<Review> getAllReviewsOfProposal(@Param(value = "proposalId") Long proposalId, Pageable pageable);
}
