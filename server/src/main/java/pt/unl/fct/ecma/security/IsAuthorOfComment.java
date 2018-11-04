package pt.unl.fct.ecma.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@PreAuthorize(IsAuthorOfComment.Condition)
public @interface IsAuthorOfComment {

    String Condition =
            "@mySecurityService.isAuthorOfExistingComment(principal, #commentid)";
}
