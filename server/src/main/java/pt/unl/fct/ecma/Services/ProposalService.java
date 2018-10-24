package pt.unl.fct.ecma.Services;

import pt.unl.fct.ecma.repositories.ProposalRepository;

public class ProposalService {
    private ProposalRepository proposalRepository;

    public ProposalService(ProposalRepository proposalRepository){
        this.proposalRepository = proposalRepository;
    }
}
