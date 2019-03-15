/**
 * 
 */
package com.cee.functional.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author chuck
 *
 */
public abstract class Jsr303Validator {
	//private static final Logger LOG = LoggerFactory.getLogger(Jsr303Validator.class);
	
	private static final Validator VALIDATOR;
	
	static {
		final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		VALIDATOR = factory.getValidator();
	}
	
	public static <T> T validate(final T obj) {
        final Set<ConstraintViolation<T>> violations = VALIDATOR.validate(obj);
        VALIDATOR.getConstraintsForClass(obj.getClass());
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        return obj;
    }
	
}
