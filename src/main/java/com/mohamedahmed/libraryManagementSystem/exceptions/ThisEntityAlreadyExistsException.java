package com.mohamedahmed.libraryManagementSystem.exceptions;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ThisEntityAlreadyExistsException extends DuplicateKeyException {

    public ThisEntityAlreadyExistsException(String msg) {
        super(msg);
    }

    public ThisEntityAlreadyExistsException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
