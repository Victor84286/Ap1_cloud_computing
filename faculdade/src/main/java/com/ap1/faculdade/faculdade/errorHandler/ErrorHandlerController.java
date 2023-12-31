package com.ap1.faculdade.faculdade.errorHandler;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ap1.faculdade.faculdade.exception.BusinessException;

@ControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse validtionErrorHandler(MethodArgumentNotValidException e){
        ValidationErrorResponse result = new ValidationErrorResponse();

        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            result.addErrorValidation(error.getField(), error.getDefaultMessage());
        }

        return result;
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse businessExceptionHandler(BusinessException e){
        ValidationErrorResponse result = new ValidationErrorResponse();
        result.getBusinessErrors().add(new BusinessError(e.getClass().getSimpleName(), e.getMessage()));
        return result;
    }
}
