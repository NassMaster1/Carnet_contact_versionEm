
package org.miage.carnet_contact.application.service;

import lombok.extern.slf4j.Slf4j;
import org.miage.carnet_contact.application.dto.ContactDTO;
import org.miage.carnet_contact.application.dto.DetailContactDTO;
import org.miage.carnet_contact.application.mapper.ContactMapper;
import org.miage.carnet_contact.application.mapper.DetailContactMapper;
import org.miage.carnet_contact.exception.ContactNotFoundExceprion;
import org.miage.carnet_contact.exception.ContactNotFoundExceptionWithName;
import org.miage.carnet_contact.model.Contact;
import org.miage.carnet_contact.repository.IDAOContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional(readOnly = true)
public class ContactService  implements IContactService{

    @Autowired
    private   IDAOContact contactRepository;
    @Autowired
    private    ContactMapper contactMapper;
    @Autowired
    private   DetailContactMapper detailContactMapper;

    @Override
    @Transactional
    public void saveContact(DetailContactDTO detailContactDTO) {
        String firstName = detailContactDTO.contactDTO().firstName();
        String lastName = detailContactDTO.contactDTO().lastName();

        log.info("Try to Create contact with name {} ...", firstName + " " + lastName);

        contactRepository.saveContact(detailContactMapper.mapToDetailContact(detailContactDTO));

        log.info("✅ contact {} created.", firstName + " " + lastName);
    }



 // Cette methode uniquement pour l'exemple ajouter un contact en brut  en utilisation applicationContext.xml
    @Override
    @Transactional
    public void saveContactBrut(Contact contact) {
        String firstName = contact.getFirstName();
        String lastName = contact.getLastName();

        log.info("Try to Create contact with name {} ...", firstName + " " + lastName);

        contactRepository.saveContactBrut(contact);

        log.info("✅ contact {} created.", firstName + " " + lastName);
    }


    @Override
    public DetailContactDTO findcontactById(Long id){
        if(id==null)
        {
            log.error("Contact ID is null");
            return null;
        }
        return contactRepository.findcontactById(id).map(detailContactMapper::mapToDetailConttactDTO).
                orElseThrow(()->new ContactNotFoundExceprion(id));
    }

    @Override
    public List<ContactDTO> findAllContact(){
        return contactRepository.findAll().map(contactMapper::mapToConttactDTO).
                orElseThrow(() -> new ContactNotFoundExceptionWithName(" all"));
    }

    @Override
    public List<ContactDTO> findByFirstNameAndLastName(String firstName, String lastName){

        return contactRepository.findByFirstNameAndLastName
                        (firstName,lastName).
                map(contactMapper::mapToConttactDTO).
                orElseThrow(() -> new ContactNotFoundExceptionWithName(firstName+" "+lastName));
    }
    @Override
    public List<ContactDTO> findByFirstName(String firstName){

        return contactRepository.findContactByFirstName(firstName).
                map(contactMapper::mapToConttactDTO).
                orElseThrow(() -> new ContactNotFoundExceptionWithName(firstName));
    }

    @Override
    public List<ContactDTO> findByLastName(String lastName){

        return contactRepository.findContactByLastName(lastName).
                map(contactMapper::mapToConttactDTO).
                orElseThrow(() -> new ContactNotFoundExceptionWithName(lastName));
    }

    @Override
    public List<ContactDTO> findByEmail(String email){

        return contactRepository.findContactByEmail(email).
                map(contactMapper::mapToConttactDTO).
                orElseThrow(() -> new ContactNotFoundExceptionWithName(email));
    }

    @Transactional
    @Override
    public void deleteContact(Long id){
        log.info("Try to delete contact with id {} ...", id);
        contactRepository.deleteContact(id);
        log.info("✅ contact with id  {} deleted.", id);
    }


    @Transactional
    @Override
    public void updateContact(Long id , ContactDTO contactDTO){

        log.info("Try to update contact with id {} ...", id);
        Contact contactModify=contactMapper.mapToConatct(contactDTO);

        contactRepository.UpdateContact(id,contactModify);

        log.info("✅ contact with id  {} updated.", id);
    }



   /* public void isPresenteContactByname(String firstName , String lastName){

        contactRepository.findByFirstNameAndLastName
                        (firstName,lastName).
                ifPresent(contact -> {
                    throw new ContactConflitException(firstName+" "+lastName);
                });
    }*/

}

