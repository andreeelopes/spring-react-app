package pt.unl.fct.ecma.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.unl.fct.ecma.Entity.Company;

public interface CompanyRepository extends CrudRepository<Company,Long> {
}
