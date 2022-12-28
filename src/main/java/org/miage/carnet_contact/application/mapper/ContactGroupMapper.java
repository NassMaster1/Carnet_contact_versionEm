package org.miage.carnet_contact.application.mapper;


import lombok.RequiredArgsConstructor;
import org.miage.carnet_contact.application.dto.ContactGroupDTO;
import org.miage.carnet_contact.model.ContactGroup;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class ContactGroupMapper {


    public ContactGroup mapToConatctGroup(ContactGroupDTO contactGroupDTO){
        return new ContactGroup(contactGroupDTO.id(),
                            contactGroupDTO.GroupName());
    }

    public  ContactGroupDTO mapToContactGroupDTO (ContactGroup contactGroup){
        return new ContactGroupDTO(contactGroup.getGroup_id(),
                                contactGroup.getName_group());
    }


    public List<ContactGroupDTO> mapToContactGroupDTO (List<ContactGroup> contactGroups){
        return contactGroups.stream().map(this::mapToContactGroupDTO).toList();
    }

}
