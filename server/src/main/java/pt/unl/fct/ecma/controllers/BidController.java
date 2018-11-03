package pt.unl.fct.ecma.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.ecma.api.BidsApi;
import pt.unl.fct.ecma.models.Bid;
import pt.unl.fct.ecma.services.BidService;

import javax.validation.Valid;

@RestController
public class BidController implements BidsApi {

    private BidService bidService;
    public BidController(BidService bidService){
        this.bidService=bidService;
    }

    @Override
    public void updateBid(@Valid @RequestBody Bid bid,  @PathVariable("employeeid") Long employeeid,@PathVariable("id")  Long id) {
        bidService.updateBid(bid,employeeid,id);
    }

    @Override
    public void addBidToProposal(@PathVariable Long id, @Valid @RequestBody Bid bid) {
        bidService.addBidToProposal(id, bid);
    }

    @Override
    public void deleteBid(@PathVariable("id") Long id, @PathVariable("employeeid") Long employeeid) {
        bidService.deleteBid(id, employeeid);
    }

    @Override
    public Page<Bid> getBids(Pageable pageable,@PathVariable  Long id) {
        return bidService.getBids(pageable,id);
    }


}
