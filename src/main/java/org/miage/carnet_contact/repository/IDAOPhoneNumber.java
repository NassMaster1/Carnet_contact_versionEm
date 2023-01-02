package org.miage.carnet_contact.repository;

import org.miage.carnet_contact.model.PhoneNumber;


public interface IDAOPhoneNumber {
    void addPhoneNumberToContact(Long id, PhoneNumber phoneNumber);

    void DeletePhoneNumberToContact(Long id, Long phoneNumber);

    void updatehoneNumberToContact(Long idContact, Long idPhoneNumber, PhoneNumber phoneNumber);
}
