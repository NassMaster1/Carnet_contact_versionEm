package org.miage.carnet_contact.repository;


import org.miage.carnet_contact.model.Contact;

import java.util.List;
import java.util.Optional;



public interface IDAOContact {

     void saveContact( Contact detailContact);

     Optional<Contact>  findcontactById(long id);

     Optional<List<Contact>> findAll();

     Optional<List<Contact>>   findByFirstNameAndLastName(String firstName, String lastName);

     Optional<List<Contact>> findContactByFirstName(String firstname);

     Optional<List<Contact>> findContactByLastName(String lastname);

     Optional<List<Contact>> findContactByEmail(String email);

     void deleteContact(Long id );

     void UpdateContact(Long id, Contact contactModify);
}
