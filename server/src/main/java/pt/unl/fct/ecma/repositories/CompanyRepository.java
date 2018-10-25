package pt.unl.fct.ecma.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pt.unl.fct.ecma.Entity.Company;
import pt.unl.fct.ecma.Entity.Employee;

public interface CompanyRepository extends CrudRepository<Company,Long> {

    Page<Company> findAll(Pageable pageable);

    Page<Company> findAllByName(String name,Pageable pageable);

    @Query("SELECT e FROM Employee e where e.company.id = :employeeid")
    Page<Employee> getAllEmployeers(@Param(value = "employeeid") Long id,Pageable pageable);

    @Query("SELECT e FROM Employee e where e.company.id = :employeeid AND e.isAdmin = true")
    Page<Employee> getAdminsOfCompany(@Param(value = "employeeid") Long id, Pageable pageable);

    @Query("SELECT e FROM Employee e where e.company.id = :employeeid and e.name LIKE CONCAT('%',:name,'%')")
    Page<Employee> getAllEmployeersWithName(@Param(value = "name") String search,@Param(value = "employeeid") Long id, Pageable pageable);
}
