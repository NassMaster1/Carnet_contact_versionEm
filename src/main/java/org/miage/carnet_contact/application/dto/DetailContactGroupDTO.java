package org.miage.carnet_contact.application.dto;

import java.io.Serializable;
import java.util.List;

public record DetailContactGroupDTO(
        Long id,
        String GroupName,
        List<ContactDTO> contactDTOS
)implements Serializable {
}
