package org.miage.carnet_contact.application.service;


import lombok.extern.slf4j.Slf4j;
import org.miage.carnet_contact.application.dto.ContactDTO;
import org.miage.carnet_contact.application.dto.ContactGroupDTO;
import org.miage.carnet_contact.application.mapper.ContactGroupMapper;
import org.miage.carnet_contact.application.mapper.ContactMapper;
import org.miage.carnet_contact.exception.ContactGroupConflitException;
import org.miage.carnet_contact.exception.ContactGroupNotFoundException;
import org.miage.carnet_contact.exception.ContactGroupNotFoundExceptoinWithName;
import org.miage.carnet_contact.exception.ContactNotFoundExceptionWithName;
import org.miage.carnet_contact.model.ContactGroup;
import org.miage.carnet_contact.repository.IDAOContactGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Transactional(readOnly = true)
public class ContactGroupService implements IContactGroupService {

    private IDAOContactGroup contactGroupRepository;

    private  ContactGroupMapper contactGroupMapper;

    private  ContactMapper contactMapper;

    @Autowired
    public void setContactGroupRepository(IDAOContactGroup contactGroupRepository) {
        this.contactGroupRepository = contactGroupRepository;
    }

    @Autowired
    public void setContactGroupMapper(ContactGroupMapper contactGroupMapper) {
        this.contactGroupMapper = contactGroupMapper;
    }

    @Autowired
    public void setContactMapper(ContactMapper contactMapper) {
        this.contactMapper = contactMapper;
    }

    @Override
    public List<ContactGroupDTO> findAllContactGroup() {
        return contactGroupRepository.findAll().map(contactGroupMapper::mapToContactGroupDTO).
                orElseThrow(() -> new ContactGroupNotFoundExceptoinWithName(" all"));
    }

    @Transactional
    @Override
    public void SaveContactGroup(ContactGroupDTO contactGroupDTO){

        //todo lorsque j'ajoute les contacts du group il cree des doublons

        log.info("Try to Create contact groupe with name {} ...", contactGroupDTO.GroupName() );

        //Verifier si le nom du groupe à ajouter existe deja
        contactGroupRepository.findByName_group(contactGroupDTO.GroupName()).ifPresent(contactGroup1 -> {
            throw new ContactGroupConflitException(contactGroupDTO.GroupName());
        });

        contactGroupRepository.saveGroup(contactGroupMapper.mapToConatctGroup(contactGroupDTO));

        log.info("✅ contact group {} created.",  contactGroupDTO.GroupName());
    }

    @Override
    public ContactGroupDTO findConatctGroupById(Long id){
        if(id==null)
        {
            log.error("ContactGroup ID is null");
            return null;
        }
        return  contactGroupRepository.findById(id).map(contactGroupMapper::mapToContactGroupDTO).
                orElseThrow(()->new ContactGroupNotFoundException(id));
    }

    @Override
    public ContactGroupDTO findContactGroupWithName(String nameGroup){
        if(nameGroup==null)
        {
            log.error("ContactGroup nameGroup is null");
            return null;
        }
        return  contactGroupRepository.findByName_group(nameGroup).map(contactGroupMapper::mapToContactGroupDTO).
                orElseThrow(()->new ContactGroupNotFoundExceptoinWithName(nameGroup));
    }


    @Transactional
    @Override
    public void AddContactToGroup(Long id_Contact, Long id_group){

        log.info("Try to add contact to groupe with id {} ...", id_group );

        contactGroupRepository.AddContactToGroup(id_Contact,id_group);

        log.info("✅ contact  {} added to group id.", id_group );
    }

    @Transactional
    @Override
    public void deleteContactGroup(Long id){
        log.info("Try to delete contact group with id {} ...", id);
        contactGroupRepository.delete(id);
        log.info("✅ contact group with id  {} deleted.", id);
    }


    @Transactional
    @Override
    public void updateGroup(Long id_group, ContactGroupDTO groupDTO){
        log.info("Try to update contact group with id {} ...", id_group);

        //Verifier si le nom du groupe à ajouter existe deja
        contactGroupRepository.findByName_group(groupDTO.GroupName()).ifPresent(contactGroup1 -> {
            throw new ContactGroupConflitException(groupDTO.GroupName());
        });

        ContactGroup groupModify=contactGroupMapper.mapToConatctGroup(groupDTO);

        contactGroupRepository.UpdateContactGroup(id_group,groupModify);
        log.info("✅ contact group with id  {}  updated.", id_group);
    }

    @Override
    public List<ContactDTO> findContactsFromGroup(Long id){
        return contactGroupRepository.findListContactFromGroup(id).map(contactMapper::mapToConttactDTO).
                orElseThrow(() -> new ContactNotFoundExceptionWithName(" all"));
    }

    @Override
    public List<ContactGroupDTO> findContactgroupByKeyWord(String keyword) {

        return contactGroupRepository.findContactByKeyWord(keyword).
                map(contactGroupMapper::mapToContactGroupDTO).
                orElseThrow(() -> new ContactGroupNotFoundExceptoinWithName(keyword));
    }

}