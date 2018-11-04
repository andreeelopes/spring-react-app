package pt.unl.fct.ecma.security;


import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@PreAuthorize(CanModifyReview.Condition)
public @interface CanModifyReview {
    String Condition = "@mySecurityService.canModifyReview(principal, #review) and " +
            "@mySecurityService.belongsToTeamProposal(principal, #proposalId)";
}