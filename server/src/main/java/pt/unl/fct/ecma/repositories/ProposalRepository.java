package pt.unl.fct.ecma.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.unl.fct.ecma.Entity.Proposal;

public interface ProposalRepository extends CrudRepository<Proposal,Long> {
}
