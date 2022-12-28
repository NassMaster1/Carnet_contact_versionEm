package org.miage.carnet_contact.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
public class BadRequestException  extends ResponseStatusException {

    public BadRequestException(String name) {
        super(HttpStatus.BAD_REQUEST, getMessage(name));
        log.warn(getMessage(name));
    }

    private static String getMessage(String name) {

        return String.format("⚠ Bad Request ",name);
    }
}
