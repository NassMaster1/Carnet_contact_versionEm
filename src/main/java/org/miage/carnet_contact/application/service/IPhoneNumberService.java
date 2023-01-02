package org.miage.carnet_contact.application.service;

import org.miage.carnet_contact.model.PhoneNumber;
import org.springframework.transaction.annotation.Transactional;

public interface IPhoneNumberService {
    @Transactional
    void addPhoneNumberToContact(Long id, PhoneNumber phoneNumber);

    @Transactional
    void deletePhoneNumberToContact(Long id_contact, Long id_phoneNumber);


    void updatehoneNumberToContact(Long id_contact, Long idPhoneNumber, PhoneNumber phoneNumber);
}
