package org.miage.carnet_contact.application.dto;


import java.io.Serializable;

//TO DO ADD VALIDATOR
public record ContactGroupDTO
        (Long id,
         String GroupName
        )implements Serializable {
}
