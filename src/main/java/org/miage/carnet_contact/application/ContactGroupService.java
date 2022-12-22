/*package org.miage.carnet_contact.application;


import org.miage.carnet_contact.application.dto.ContactDTO;
import org.miage.carnet_contact.application.dto.ContactGroupDTO;
import org.miage.carnet_contact.application.dto.DetailContactGroupDTO;
import org.miage.carnet_contact.application.mapper.ContactGroupMapper;
import org.miage.carnet_contact.application.mapper.ContactMapper;
import org.miage.carnet_contact.application.mapper.DetailContactGroupMapper;
import org.miage.carnet_contact.exception.*;
import org.miage.carnet_contact.model.Contact;
import org.miage.carnet_contact.model.ContactGroup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.miage.carnet_contact.repository.DAOContact;
import org.miage.carnet_contact.repository.DAOContactGroup;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContactGroupService {

    private final DAOContactGroup contactGroupRepository;

    private final ContactGroupMapper contactGroupMapper;
    private final DetailContactGroupMapper detailContactGroupMapper;

    private final ContactService contactService;

    private final DAOContact contactRepository;

    private final ContactMapper contactMapper;

    public List<ContactGroupDTO> findAllContactGroup(){
     return  contactGroupRepository.findAll().stream().map(contactGroupMapper::mapToContactGroupDTO).toList();
    }

    public DetailContactGroupDTO findConatctGroupById(Long id){
        if(id==null)
        {
            log.error("ContactGroup ID is null");
            return null;
        }
        return  contactGroupRepository.findById(id).map(detailContactGroupMapper::mapToDetailContactGroupDTO).
                orElseThrow(()->new ContactGroupNotFoundException(id));
    }


    public DetailContactGroupDTO findContactGroupWithName(String nameGroup){
        if(nameGroup==null)
        {
            log.error("ContactGroup nameGroup is null");
            return null;
        }
        return  contactGroupRepository.findByName_group(nameGroup).map(detailContactGroupMapper::mapToDetailContactGroupDTO).
                orElseThrow(()->new ContactGroupNotFoundExceptoinWithName(nameGroup));
    }

    @Transactional
    public void SaveContactGroup(ContactGroupDTO contactGroupDTO){

        //todo lorsque j'ajoute les contacts du group il cree des doublons

        log.info("Try to Create contact groupe with name {} ...", contactGroupDTO.GroupName() );

        //Verifier si le nom du groupe à ajouter existe deja
        contactGroupRepository.findByName_group(contactGroupDTO.GroupName()).ifPresent(contactGroup1 -> {
            throw new ContactGroupConflitException(contactGroupDTO.GroupName());
        });

        contactGroupRepository.save(contactGroupMapper.mapToConatctGroup(contactGroupDTO));

        log.info("✅ contact group {} created.",  contactGroupDTO.GroupName());
    }

    @Transactional
    public void AddContactToGroup(Long id , ContactDTO contactDTO){

        log.info("Try to add contact to groupe with name {} ...", contactDTO.firstName() );

        ContactGroup contactGroup= detailContactGroupMapper.mapToDetailConatctGroup(this.findConatctGroupById(id));

        Contact contactToAdd = contactRepository.findByFirstNameAndLastName(contactDTO.firstName(), contactDTO.lastName()).
                orElseThrow(() -> new ContactNotFoundExceptionWithName(contactDTO.firstName()+" "+contactDTO.lastName()));

        Boolean contactIsPresentInGroup=contactGroup.getContacts().stream().filter(
                contact -> contact.getFirstName().equals(contactDTO.firstName()) &&
                contact.getLastName().equals(contactDTO.lastName())).toList().isEmpty();
        if (contactIsPresentInGroup)
        {
            contactGroup.addToListContact(contactToAdd);
        }
        else {
            throw new ContactConflitException(contactToAdd.getFirstName()+" in this group");
        }

        contactGroupRepository.save(contactGroup);

        log.info("✅ contact  {} added to group.", contactDTO.firstName() );
    }


    @Transactional
    public void deleteContactGroup(Long id ){
        log.info("Try to delete contact group with id {} ...", id);
        ContactGroup contactGroup= detailContactGroupMapper.mapToDetailConatctGroup(this.findConatctGroupById(id));
        contactGroupRepository.delete(contactGroup);
        log.info("✅ contact group with id  {} deleted.", id);
    }

    @Transactional
    public void updateContactGroup(Long id,ContactGroupDTO contactGroupDto){
        log.info("Try to update contact group with id {} ...", id);

        ContactGroup contactGroup= detailContactGroupMapper.mapToDetailConatctGroup(this.findConatctGroupById(id));
        contactGroupRepository.findByName_group(contactGroupDto.GroupName()).ifPresent(c -> {
            throw new ContactGroupConflitException(contactGroupDto.GroupName());
        });

        contactGroup.setName_group(contactGroupDto.GroupName());
        contactGroupRepository.save(contactGroup);
    }
}
*/