package org.miage.carnet_contact.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
public class PhoneNumberNotFoundException  extends ResponseStatusException {

    public PhoneNumberNotFoundException(String name) {
        super(HttpStatus.NOT_FOUND, getMessage(name));
        log.warn(getMessage(name));
    }

    private static String getMessage(String name) {
        return String.format("⚠ Phone Number  with id <%s> doesn't exists", name);
    }

}
