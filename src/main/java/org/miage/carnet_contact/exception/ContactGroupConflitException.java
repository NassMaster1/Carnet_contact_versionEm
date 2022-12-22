package org.miage.carnet_contact.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
public class ContactGroupConflitException extends ResponseStatusException {

    public ContactGroupConflitException(String name) {
        super(HttpStatus.CONFLICT, getMessage(name));
        log.warn(getMessage(name));
    }

    private static String getMessage(String name) {

        return String.format("⚠ contact group with -%s- is already exist", name );
    }

}
