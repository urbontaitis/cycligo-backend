package com.cycligo.backend.base.handler.error;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

/**
 * Created by Mindaugas Urbontaitis on 08/02/2017.
 * cycligo-rest-api
 */
public class ValidationErrorBuilder {

    public static ValidationError fromBindingErrors(Errors errors) {
        ValidationError error = new ValidationError("Validation failed. " + errors.getErrorCount() + " error(s)");
        for (ObjectError objectError : errors.getAllErrors()) {
            error.addValidationError(objectError.getDefaultMessage());
        }
        return error;
    }
}
