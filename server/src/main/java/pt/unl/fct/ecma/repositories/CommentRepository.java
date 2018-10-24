package pt.unl.fct.ecma.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.unl.fct.ecma.Entity.Comment;

public interface CommentRepository extends CrudRepository<Comment,Long> {
}
