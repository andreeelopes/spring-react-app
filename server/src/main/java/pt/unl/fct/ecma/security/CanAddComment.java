package pt.unl.fct.ecma.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@PreAuthorize(CanAddComment.Condition)
public @interface CanAddComment {
    String Condition =
            "@MySecurityService.isAuthorOfComment(principal, #comment) and " +
            "@MySecurityService.belongsToTeamProposal(principal, #proposalid)";
}
