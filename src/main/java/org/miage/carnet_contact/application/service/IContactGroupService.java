package org.miage.carnet_contact.application.service;

import org.miage.carnet_contact.application.dto.ContactDTO;
import org.miage.carnet_contact.application.dto.ContactGroupDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IContactGroupService {
    List<ContactGroupDTO> findAllContactGroup();

    @Transactional
    void SaveContactGroup(ContactGroupDTO contactGroupDTO);

    ContactGroupDTO findConatctGroupById(Long id);

    ContactGroupDTO findContactGroupWithName(String nameGroup);

    @Transactional
    void AddContactToGroup(Long id_Contact, Long id_group);

    @Transactional
    void deleteContactGroup(Long id);

    @Transactional
    void updateGroup(Long id_group, ContactGroupDTO groupDTO);

    List<ContactDTO> findContactsFromGroup(Long id);

    List<ContactGroupDTO> findContactgroupByKeyWord(String keyword);
}
