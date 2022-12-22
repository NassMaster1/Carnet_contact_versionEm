package org.miage.carnet_contact.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
public class ContactGroupNotFoundException extends ResponseStatusException {

    public ContactGroupNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, getMessage(id));
        log.warn(getMessage(id));
    }

    private static String getMessage(Long id) {
        return String.format("⚠ Contact group with id <%d> doesn't exists", id);
    }

}
