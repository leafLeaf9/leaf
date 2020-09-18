package com.gousade.validator;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.gousade.annotation.Zone;

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
			if (Arrays.asList(zone).contains(value)) {
				return true;
			} else {
				return false;
			}
		}
	}

}
