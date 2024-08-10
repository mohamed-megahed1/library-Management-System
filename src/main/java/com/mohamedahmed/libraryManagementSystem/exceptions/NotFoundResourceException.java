package com.mohamedahmed.libraryManagementSystem.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundResourceException extends RuntimeException {

    public NotFoundResourceException() {
    }

    public NotFoundResourceException(String message) {
        super(message);
    }


}
