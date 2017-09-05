package com.cycligo.backend.core.handler.error;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

/**
 * Created by Mindaugas Urbontaitis on 08/02/2017.
 * cycligo-rest-api
 */
public class ValidationErrorBuilder {

    public static ValidationError fromBindingErrors(Errors errors) {
        ValidationError error = new ValidationError("Validation failed. " + errors.getErrorCount()+ " error(s)");
        for (FieldError fe : errors.getFieldErrors()) {
            error.addValidationError(fe.getField(), fe.getDefaultMessage());
        }

        return error;
    }
}
