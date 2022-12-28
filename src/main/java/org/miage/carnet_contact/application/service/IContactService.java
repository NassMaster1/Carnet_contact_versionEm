package org.miage.carnet_contact.application.service;

import org.miage.carnet_contact.application.dto.ContactDTO;
import org.miage.carnet_contact.application.dto.DetailContactDTO;
import org.miage.carnet_contact.model.Contact;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IContactService {
    @Transactional
    void saveContact(DetailContactDTO detailContactDTO);

    // Cette methode uniquement pour l'exemple ajouter un contact en brut  en utilisation applicationContext.xml
       @Transactional
       void saveContactBrut(Contact contact);

    DetailContactDTO findcontactById(Long id);

    List<ContactDTO> findAllContact();

    List<ContactDTO> findByFirstNameAndLastName(String firstName, String lastName);

    List<ContactDTO> findByFirstName(String firstName);

    List<ContactDTO> findByLastName(String lastName);

    List<ContactDTO> findByEmail(String email);

    @Transactional
    void deleteContact(Long id);

    @Transactional
    void updateContact(Long id, ContactDTO contactDTO);


}
