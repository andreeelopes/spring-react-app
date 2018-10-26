package pt.unl.fct.ecma.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pt.unl.fct.ecma.models.Bid;
import pt.unl.fct.ecma.models.Proposal;
import pt.unl.fct.ecma.models.ProposalRole;

import javax.transaction.Transactional;
import java.util.List;

public interface ProposalRepository extends CrudRepository<Proposal,Long> {
    @Transactional
    @Modifying
    @Query("delete from Bid b where b.proposal.id = :id and b.bidder.id = :employeeid")
    void deleteBidById(@Param(value = "id")Long id,@Param(value = "employeeid") Long employeeid);

    @Query("select b from Bid b where b.proposal.id = :id and b.bidder.id = :employeeid")
    List<Bid> existsBid(@Param(value = "id")Long id,@Param(value = "employeeid") Long employeeid);



    @Transactional
    @Modifying
    @Query("delete FROM ProposalRole r WHERE r.employee.id = :partnerid AND r.role LIKE CONCAT('%','PARTNER','%') AND r.proposal.id = :id")
    void deletePartner(@Param(value = "id")Long id, @Param(value = "partnerid") Long partnerid);

    @Query("select r FROM ProposalRole r WHERE r.employee.id = :partnerid AND r.role LIKE CONCAT('%','PARTNER','%') AND r.proposal.id = :id")
    List<ProposalRole> PartnerExists(@Param(value = "id")Long id, @Param(value = "partnerid") Long partnerid);

    @Transactional
    @Modifying
    @Query("delete FROM ProposalRole r WHERE r.employee.id = :staffid AND r.role LIKE CONCAT('%','STAFF','%') AND r.proposal.id = :id")
    void deleteStaff(@Param(value = "id")Long id, @Param(value = "staffid") Long partnerid);

    @Query("select r FROM ProposalRole r WHERE r.employee.id = :staffid AND r.role LIKE CONCAT('%','STAFF','%') AND r.proposal.id = :id")
    List<ProposalRole> staffExists(@Param(value = "id")Long id, @Param(value = "staffid") Long partnerid);
}
