package com.gousade.validator;

import com.gousade.annotation.Zone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class ZoneValidator implements ConstraintValidator<Zone, String> {

    private String[] zone;

    @Override
    public void initialize(Zone constraintAnnotation) {
        this.zone = constraintAnnotation.zone();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || "".equals(value)) {
            return false;
        } else {
			return Arrays.asList(zone).contains(value);
        }
    }

}
