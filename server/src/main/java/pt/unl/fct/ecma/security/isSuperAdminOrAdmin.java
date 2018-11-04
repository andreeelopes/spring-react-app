package pt.unl.fct.ecma.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@PreAuthorize(isSuperAdminOrAdmin.Condition)
public @interface isSuperAdminOrAdmin {
    String Condition = "@mySecurityService.IsAdminOfCompany(principal,#id) or hasRole('ADMIN')";
}
