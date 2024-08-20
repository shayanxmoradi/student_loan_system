package org.example.entities.validator.universitynamevalidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniversityNameValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)public @interface ValidUniversityName {
    String message() default "Invalid University name";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
