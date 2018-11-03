package pt.unl.fct.ecma.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@PreAuthorize(isPrincipal.Condition)
public @interface isPrincipal {
    String Condition = "@mySecurityService.isPrincipal(principal, #id)";
}
