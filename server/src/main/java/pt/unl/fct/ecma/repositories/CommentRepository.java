package pt.unl.fct.ecma.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import pt.unl.fct.ecma.models.Comment;

public interface CommentRepository extends CrudRepository<Comment,Long> {
    Page<Comment> findAllByProposal_Id(Long id, Pageable pageable);

}
