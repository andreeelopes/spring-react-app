package pt.unl.fct.ecma.Services;

import org.springframework.stereotype.Service;
import pt.unl.fct.ecma.repositories.ProposalRepository;
@Service
public class ProposalService {
    private ProposalRepository proposalRepository;

    public ProposalService(ProposalRepository proposalRepository){
        this.proposalRepository = proposalRepository;
    }
}
