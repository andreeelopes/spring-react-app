package pt.unl.fct.ecma.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pt.unl.fct.ecma.models.Company;
import pt.unl.fct.ecma.models.Employee;

public interface CompanyRepository extends CrudRepository<Company,Long> {

    Page<Company> findAll(Pageable pageable);

    Page<Company> findByName(String name, Pageable pageable);

    @Query("SELECT e FROM Employee e where e.company.id = :companyid AND e.isAdmin = false")
    Page<Employee> getAllEmployees(@Param(value = "companyid") Long id, Pageable pageable);

    @Query("SELECT e FROM Employee e where e.company.id = :companyid AND e.isAdmin = true")
    Page<Employee> getAdminsOfCompany(@Param(value = "companyid") Long id, Pageable pageable);

    @Query("SELECT e FROM Employee e where e.company.id = :companyid and e.name LIKE CONCAT('%',:name,'%')")
    Page<Employee> getEmployeesByName(@Param(value = "name") String search,
                                      @Param(value = "companyid") Long id, Pageable pageable);
}
