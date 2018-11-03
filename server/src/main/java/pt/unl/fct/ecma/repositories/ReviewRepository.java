package pt.unl.fct.ecma.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import pt.unl.fct.ecma.models.Review;


public interface ReviewRepository extends CrudRepository<Review,Long> {

    Page<Review> findAllByProposal_Id(Long id, Pageable pageable);
}
