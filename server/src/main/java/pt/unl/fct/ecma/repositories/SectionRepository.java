package pt.unl.fct.ecma.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.unl.fct.ecma.models.Section;

public interface SectionRepository extends CrudRepository<Section,Long> {
}
