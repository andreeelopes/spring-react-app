package pt.unl.fct.ecma.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.ecma.api.BidsApi;
import pt.unl.fct.ecma.models.Bid;
import pt.unl.fct.ecma.models.Employee;
import pt.unl.fct.ecma.security.CanAddBidToProposal;
import pt.unl.fct.ecma.security.IsApproverOfProposal;
import pt.unl.fct.ecma.security.IsBidder;
import pt.unl.fct.ecma.services.BidService;

import javax.validation.Valid;
import java.security.Principal;

@RestController
public class BidController implements BidsApi {

    private BidService bidService;

    public BidController(BidService bidService){
        this.bidService=bidService;
    }

    @IsApproverOfProposal
    //approver daquela proposal
    @Override
    public void updateBid(@Valid @RequestBody Bid bid,  @PathVariable("employeeid") Long employeeid,@PathVariable("id")  Long id) {
        bidService.updateBid(bid,employeeid,id);
    }

    @CanAddBidToProposal
    //fazer parte da team da proposale e não ser approver
    @Override
    public void addBidToProposal(@PathVariable Long id, @Valid @RequestBody Bid bid) {
        bidService.addBidToProposal(id, bid);
    }

    @IsBidder
    //autor da bid
    @Override
    public void deleteBid(@PathVariable("id") Long id, @PathVariable("employeeid") Long employeeid) {
        bidService.deleteBid(id, employeeid);
    }

    @IsApproverOfProposal
    //approver
    @Override
    public Page<Bid> getBids(Pageable pageable,@PathVariable  Long id) {
        return bidService.getBids(pageable,id);
    }


}
