package com.epm.recipe.web_api.advice;

import com.epm.recipe.web_api.exception.BusinessException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    @ExceptionHandler
    public ResponseEntity<ErrorWrapper> onBusinessException(BusinessException exception) {
        log.trace("Business exception", exception);
        return new ResponseEntity<>(new ErrorWrapper(new ExceptionBody(exception)), HttpStatus.BAD_REQUEST);
    }

    public static class ExceptionBody {

        public final String name;
        public final String message;

        public ExceptionBody(BusinessException exception) {
            this.name = exception.name();
            this.message = exception.description();
        }

        public String getName() {
            return name;
        }

        public String getMessage() {
            return message;
        }
    }


    public static class ErrorWrapper {

        public final ExceptionBody error;

        public ErrorWrapper(ExceptionBody error) {
            this.error = error;
        }
    }
}
