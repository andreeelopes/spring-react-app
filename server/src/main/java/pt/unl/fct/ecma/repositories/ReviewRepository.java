package pt.unl.fct.ecma.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import pt.unl.fct.ecma.models.Review;

import javax.transaction.Transactional;

public interface ReviewRepository extends CrudRepository<Review,Long> {
    @Transactional
    @Modifying
    void deleteByIdAndProposalId(Long reviewid,Long proposalid);
}
