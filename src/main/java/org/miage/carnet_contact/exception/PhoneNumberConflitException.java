package org.miage.carnet_contact.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
public class PhoneNumberConflitException extends ResponseStatusException {

    public PhoneNumberConflitException(String name) {
        super(HttpStatus.CONFLICT, getMessage(name));
        log.warn(getMessage(name));
    }

    private static String getMessage(String phoneNumber) {

        return String.format("⚠ Phone Number  with -%s- is already exist ", phoneNumber );
    }

}
