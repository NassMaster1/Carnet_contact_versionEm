package org.miage.carnet_contact.controller;


import org.miage.carnet_contact.application.service.IPhoneNumberService;
import org.miage.carnet_contact.model.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/v1/PhoneNumber", produces = MediaType.APPLICATION_JSON_VALUE)
public class PhoneNumberController {

    @Autowired
    private IPhoneNumberService phoneNumberService;


    @PostMapping("AddPhoneNumber/{id_contact}")
    public void AddPhoneNumberToContact(@PathVariable("id_contact") Long id, @RequestBody @Valid PhoneNumber phoneNumber){
        phoneNumberService.addPhoneNumberToContact(id,phoneNumber);
    }

    @PutMapping("updatePhoneNumber/{id_contact}/{id_phoneNumber}")
    public void updatehoneNumberToContact(@PathVariable("id_contact") Long id, @PathVariable("id_phoneNumber") Long id_phoneNumber,@RequestBody @Valid PhoneNumber phoneNumber){
        phoneNumberService.updatehoneNumberToContact(id,id_phoneNumber,phoneNumber);
    }



    @DeleteMapping("/deletePhoneNumber/{id_contact}/{id_phoneNumber}")
    public void deletePhoneNumberToContact(@PathVariable("id_contact") Long id_contact,@PathVariable("id_phoneNumber") Long id_phoneNumber) {
        phoneNumberService.deletePhoneNumberToContact(id_contact,id_phoneNumber);
    }
}
