package pt.unl.fct.ecma.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pt.unl.fct.ecma.api.BidsApi;
import pt.unl.fct.ecma.models.Bid;

import javax.validation.Valid;

@RestController
public class BidController implements BidsApi {

    @Override
    public void updateBid(@Valid Bid review, Long bidid, Long id) {

    }

    @Override
    public void addBidToProposal(@PathVariable Long id, @Valid @RequestBody Bid bid) {
        proposalService.addBidToProposal(id, bid);
    }

    @Override
    public void deleteBid(@PathVariable("id") Long id, @PathVariable("bidid") Long bidid) {
        proposalService.deleteBid(id, bidid);
    }

    @Override
    public Page<Bid> getBids(Pageable pageable, Long id) {
        return null;
    }


}
