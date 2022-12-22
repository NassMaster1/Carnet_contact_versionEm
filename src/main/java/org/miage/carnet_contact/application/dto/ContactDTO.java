package org.miage.carnet_contact.application.dto;

import java.io.Serializable;

//TO DO ADD VALIDATOR
public record ContactDTO(
        Long id,
         String firstName,
        String lastName,

        String email
)implements Serializable {
}
