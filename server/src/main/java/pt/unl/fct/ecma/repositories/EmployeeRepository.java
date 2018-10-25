package pt.unl.fct.ecma.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pt.unl.fct.ecma.models.Bid;
import pt.unl.fct.ecma.models.Employee;
import pt.unl.fct.ecma.models.Proposal;

public interface EmployeeRepository extends CrudRepository<Employee,Long> {

    Page<Employee> findAll(Pageable pageable);

    Page<Employee> findByName(String name, Pageable pageable);

    @Query("SELECT b FROM Bid b WHERE b.status LIKE CONCAT('%',:name,'%') AND  b.employee.id = :employeeid ")
    Page<Bid> findBidsByStatus(@Param(value = "name") String name, @Param(value = "employeeid") Long id, Pageable pageable);

    @Query("SELECT b FROM Bid b where b.employee.id = :employeeid ")
    Page<Bid> findAllBids(Pageable pageable, @Param(value = "employeeid") Long id);

    @Query("SELECT r.proposal FROM ProposalRole r WHERE r.employee.id = :employeeid AND r.role LIKE CONCAT('%','PARTNER','%')")
    Page<Proposal> findProposalPartner(Pageable pageable,@Param(value = "employeeid") Long id);

    @Query("SELECT r.proposal FROM ProposalRole r WHERE r.employee.id = :employeeid AND r.role LIKE CONCAT('%','STAFF','%')")
    Page<Proposal> findProposalStaff(Pageable pageable, Long id);

    //TODO Add findProposalApprover
}
