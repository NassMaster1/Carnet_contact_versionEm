package org.miage.carnet_contact.application.mapper;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.miage.carnet_contact.application.dto.DetailContactDTO;
import org.miage.carnet_contact.model.Contact;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class DetailContactMapper {


    private final ContactMapper contactMapper;


    public Contact mapToDetailContact(DetailContactDTO detailContactDTO){
        Contact contact =new  Contact(
                            detailContactDTO.contactDTO().firstName(),
                            detailContactDTO.contactDTO().lastName(),
                            detailContactDTO.contactDTO().email());

        contact.setAdresse(detailContactDTO.adresse());
        detailContactDTO.phoneNumbers().forEach(phoneNumber -> contact.getPhoneNumber().add(phoneNumber));
        contact.getPhoneNumber().forEach(phoneNumber -> phoneNumber.setContact(contact));

        return contact;
    }


    public  DetailContactDTO mapToDetailConttactDTO (Contact contact){

        return new DetailContactDTO(contactMapper.mapToConttactDTO(contact),
                                    contact.getPhoneNumber(),
                                    contact.getAdresse());
    }

}
