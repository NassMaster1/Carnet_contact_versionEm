package org.miage.carnet_contact.application.mapper;

import lombok.RequiredArgsConstructor;
import org.miage.carnet_contact.application.dto.ContactDTO;
import org.miage.carnet_contact.application.dto.DetailContactGroupDTO;
import org.miage.carnet_contact.model.ContactGroup;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DetailContactGroupMapper {

    private ContactMapper contactMapper;

    public ContactGroup mapToDetailConatctGroup(DetailContactGroupDTO detailGroup){
        List<ContactDTO> contactDTOS =detailGroup.contactDTOS();

        ContactGroup contactGroup= new ContactGroup(detailGroup.id(),
                detailGroup.GroupName());
        contactGroup.setContacts(contactMapper.mapToConatct(contactDTOS));
        return contactGroup;
    }


    public  DetailContactGroupDTO mapToDetailContactGroupDTO (ContactGroup contactGroup){
        return new DetailContactGroupDTO(contactGroup.getGroup_id(),
                contactGroup.getName_group(),
                contactMapper.mapToConttactDTO( contactGroup.getContacts()));
    }
}
