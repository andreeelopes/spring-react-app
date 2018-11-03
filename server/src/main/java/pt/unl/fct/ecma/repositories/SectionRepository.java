package pt.unl.fct.ecma.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import pt.unl.fct.ecma.models.Section;

public interface SectionRepository extends CrudRepository<Section,Long> {

    Page<Section> findAllProposalsBy_Id(Long id, Pageable pageable);

}
