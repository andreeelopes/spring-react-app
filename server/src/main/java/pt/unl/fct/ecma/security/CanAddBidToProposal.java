package pt.unl.fct.ecma.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@PreAuthorize(CanAddBidToProposal.Condition)
public @interface CanAddBidToProposal {
    String Condition = "@mySecurityService.belongsToTeamProposal(principal,#id) and @mySecurityService.bidHasPrincipal(principal,#bid) and not @mySecurityService.isApproverOfProposal(principal,#id)";
}
