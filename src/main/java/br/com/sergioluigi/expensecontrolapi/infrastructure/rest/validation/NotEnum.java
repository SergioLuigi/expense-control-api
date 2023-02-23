package br.com.sergioluigi.expensecontrolapi.infrastructure.rest.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({PARAMETER,FIELD})
@Constraint(validatedBy = NotEnumValidator.class)
public @interface NotEnum {

    Class<? extends Enum<?>> enumClass();

    String message() default "invalid enum value";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
