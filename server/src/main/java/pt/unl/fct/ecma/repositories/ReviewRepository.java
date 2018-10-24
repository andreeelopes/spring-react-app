package pt.unl.fct.ecma.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.unl.fct.ecma.Entity.Review;

public interface ReviewRepository extends CrudRepository<Review,Long> {
}
