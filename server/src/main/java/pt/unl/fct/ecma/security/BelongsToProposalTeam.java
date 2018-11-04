package pt.unl.fct.ecma.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@PreAuthorize(BelongsToProposalTeam.Condition)
public @interface BelongsToProposalTeam {
    String Condition = "@mySecurityService.belongsToTeamProposal(principal, #proposalId)";
}