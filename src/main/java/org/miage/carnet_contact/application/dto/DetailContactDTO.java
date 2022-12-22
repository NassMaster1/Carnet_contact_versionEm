package org.miage.carnet_contact.application.dto;

import org.miage.carnet_contact.model.Adresse;
import org.miage.carnet_contact.model.PhoneNumber;

import java.io.Serializable;
import java.util.Set;


//TO DO ADD VALIDATOR
public record DetailContactDTO(
         ContactDTO contactDTO,
         Set<PhoneNumber> phoneNumbers,
         Adresse adresse
)implements Serializable {
}
