
package org.miage.carnet_contact.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.miage.carnet_contact.application.dto.ContactDTO;
import org.miage.carnet_contact.application.dto.DetailContactDTO;
import org.miage.carnet_contact.application.mapper.ContactMapper;
import org.miage.carnet_contact.application.mapper.DetailContactMapper;
import org.miage.carnet_contact.exception.ContactNotFoundExceprion;
import org.miage.carnet_contact.exception.ContactNotFoundExceptionWithName;
import org.miage.carnet_contact.model.Contact;
import org.miage.carnet_contact.repository.DAOContact;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class ContactService {

    private final DAOContact contactRepository;
    private final ContactMapper contactMapper;

    private final DetailContactMapper detailContactMapper;


    @Transactional
    public void saveContact(DetailContactDTO detailContactDTO) {
        String firstName = detailContactDTO.contactDTO().firstName();
        String lastName = detailContactDTO.contactDTO().lastName();

        log.info("Try to Create contact with name {} ...", firstName);

        contactRepository.saveContact(detailContactMapper.mapToDetailContact(detailContactDTO));

        log.info("✅ contact {} created.", detailContactDTO.contactDTO().firstName());
    }

    public DetailContactDTO findcontactById(Long id){
        if(id==null)
        {
            log.error("Contact ID is null");
            return null;
        }
        return contactRepository.findcontactById(id).map(detailContactMapper::mapToDetailConttactDTO).
                orElseThrow(()->new ContactNotFoundExceprion(id));
    }

    public List<ContactDTO> findAllContact(){
        return contactRepository.findAll().map(contactMapper::mapToConttactDTO).
                orElseThrow(() -> new ContactNotFoundExceptionWithName(" all"));
    }




   /* public void isPresenteContactByname(String firstName , String lastName){

        contactRepository.findByFirstNameAndLastName
                        (firstName,lastName).
                ifPresent(contact -> {
                    throw new ContactConflitException(firstName+" "+lastName);
                });
    }*/

    public List<ContactDTO> findByFirstNameAndLastName(String firstName , String lastName){

        return contactRepository.findByFirstNameAndLastName
                        (firstName,lastName).
                map(contactMapper::mapToConttactDTO).
                orElseThrow(() -> new ContactNotFoundExceptionWithName(firstName+" "+lastName));
    }

    public List<ContactDTO> findByFirstName(String firstName ){

        return contactRepository.findContactByFirstName(firstName).
                map(contactMapper::mapToConttactDTO).
                orElseThrow(() -> new ContactNotFoundExceptionWithName(firstName));
    }

    public List<ContactDTO> findByLastName(String lastName ){

        return contactRepository.findContactByLastName(lastName).
                map(contactMapper::mapToConttactDTO).
                orElseThrow(() -> new ContactNotFoundExceptionWithName(lastName));
    }

    public List<ContactDTO> findByEmail(String email ){

        return contactRepository.findContactByEmail(email).
                map(contactMapper::mapToConttactDTO).
                orElseThrow(() -> new ContactNotFoundExceptionWithName(email));
    }

    @Transactional
    public void deleteContact(Long id){
        log.info("Try to delete contact with id {} ...", id);
        contactRepository.deleteContact(id);
        log.info("✅ contact with id  {} deleted.", id);
    }


    @Transactional
    public void updateContact(Long id , ContactDTO contactDTO){

        log.info("Try to update contact with id {} ...", id);
        Contact contactModify=contactMapper.mapToConatct(contactDTO);

        contactRepository.UpdateContact(id,contactModify);

        log.info("✅ contact with id  {} updated.", id);
    }

}

