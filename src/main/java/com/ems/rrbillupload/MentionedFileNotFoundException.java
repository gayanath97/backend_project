package com.ems.rrbillupload;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MentionedFileNotFoundException extends RuntimeException {

    public MentionedFileNotFoundException(String message) {
        super(message);
    }

    public MentionedFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
