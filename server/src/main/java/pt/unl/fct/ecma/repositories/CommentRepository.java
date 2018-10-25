package pt.unl.fct.ecma.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.unl.fct.ecma.models.Comment;

public interface CommentRepository extends CrudRepository<Comment,Long> {
}
