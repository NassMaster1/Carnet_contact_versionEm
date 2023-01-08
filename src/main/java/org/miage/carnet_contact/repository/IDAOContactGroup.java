package org.miage.carnet_contact.repository;

import io.micrometer.observation.ObservationFilter;
import org.miage.carnet_contact.model.Contact;
import org.miage.carnet_contact.model.ContactGroup;

import java.util.List;
import java.util.Optional;

public interface IDAOContactGroup {


    Optional<List<ContactGroup>> findAll();

    Optional<ContactGroup> findByName_group(String groupName);

    void saveGroup(ContactGroup conatctGroup);

    Optional<ContactGroup> findById(Long id);


    void AddContactToGroup(Long id_contact, Long id_group);

    void delete(Long id);

    void UpdateContactGroup(Long idGroup, ContactGroup groupModify);

    Optional<List<Contact>> findListContactFromGroup(Long id_group);

    Optional<List<ContactGroup>> findContactByKeyWord(String keyword);
}
