package com.sontaypham.todolist.Validator;

import com.nimbusds.jose.Payload;
import jakarta.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NameValidator.class)
public @interface NameConstraint {
  String message() default "INVALID_USERNAME";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
