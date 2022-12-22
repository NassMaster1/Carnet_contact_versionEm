package org.miage.carnet_contact.application.mapper;

import org.miage.carnet_contact.application.dto.ContactDTO;
import org.miage.carnet_contact.model.Contact;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContactMapper {


    //Map to contact depuis contactDTO
    public Contact mapToConatct(ContactDTO contactDTO){
        return new Contact(contactDTO.firstName(),
                contactDTO.lastName() , contactDTO.email()) ;
    }

    //Map list of contact from list of contactDTO (use maptoContact())
    public List<Contact> mapToConatct(List<ContactDTO> contactDTO){
       return contactDTO.stream().map(this::mapToConatct).toList();
    }


    public  ContactDTO mapToConttactDTO (Contact contact){
        return new ContactDTO(contact.getId_contact(),contact.getFirstName(),contact.getLastName(),contact.getEmail());
    }

    public  List<ContactDTO> mapToConttactDTO (List<Contact> contact){
        return contact.stream().map(this::mapToConttactDTO).toList();
    }

}
