package pt.unl.fct.ecma.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pt.unl.fct.ecma.Entity.Bid;
import pt.unl.fct.ecma.Entity.Employee;
import pt.unl.fct.ecma.Entity.Proposal;

public interface EmployeeRepository extends CrudRepository<Employee,Long> {

    Page<Employee> findAll(Pageable pageable);

    Page<Employee> findAllByName(String name,Pageable pageable);

    @Query("SELECT b FROM Bid b WHERE b.status LIKE CONCAT('%',:name,'%') AND  b.employee.id = :employeeid ")
    Page<Bid> findBidByStatus(@Param(value = "name") String name, @Param(value = "employeeid") Long id,Pageable pageable);

    @Query("SELECT b FROM Bid b where b.employee.id = :employeeid ")
    Page<Bid> findAllBid(Pageable pageable,@Param(value = "employeeid") Long id);

    @Query("SELECT r.proposal FROM ProposalRole r WHERE r.employee.id = :employeeid AND r.role LIKE CONCAT('%','PARTNER','%')")
    Page<Proposal> findProposalPartner(Pageable pageable,@Param(value = "employeeid") Long id);

    @Query("SELECT r.proposal FROM ProposalRole r WHERE r.employee.id = :employeeid AND r.role LIKE CONCAT('%','STAFF','%')")
    Page<Proposal> findProposalStaff(Pageable pageable, Long id);
}
