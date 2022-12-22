package org.miage.carnet_contact.controller;


import org.miage.carnet_contact.application.ContactService;
import org.miage.carnet_contact.application.dto.ContactDTO;
import org.miage.carnet_contact.application.dto.DetailContactDTO;
import org.miage.carnet_contact.util.CostumeResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/v1/contact", produces = MediaType.APPLICATION_JSON_VALUE)
public record ContactController(ContactService contactService) {



    @PostMapping("")
    public ResponseEntity<CostumeResponse<String>> save(@RequestBody @Valid DetailContactDTO contact) {

        contactService.saveContact(contact);
        return new ResponseEntity<>(new CostumeResponse<>(format("contact %s created", contact)), CREATED);
    }


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ContactDTO> findcontactById (){
        return contactService.findAllContact();
    }


    @GetMapping(value = "/{id_contact}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DetailContactDTO findcontactById (@PathVariable("id_contact") Long id){
        return contactService.findcontactById(id);
    }

    @GetMapping(value = "findbyFirstName_LastName/{firstName}/{lastName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ContactDTO> findcontactById (@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName){
        return contactService.findByFirstNameAndLastName(firstName,lastName);
    }

    @GetMapping(value = "findbyFirstName/{firstName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ContactDTO> findcontactByFirstName (@PathVariable("firstName") String firstName){
        return contactService.findByFirstName(firstName);
    }

    @GetMapping(value = "findbyLastName/{lastName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ContactDTO> findByLastName ( @PathVariable("lastName") String lastName){
        return contactService.findByLastName(lastName);
    }

    @GetMapping(value = "findbyEmail/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ContactDTO> findcontactByEmail (@PathVariable("email") String email){
        return contactService.findByEmail(email);
    }

    @DeleteMapping("/{id_contact}")
    public void deleteContact(@PathVariable("id_contact") Long id) {
        contactService.deleteContact(id);
    }


    @PutMapping("/{id_contact}")
    public void updateContact(@PathVariable("id_contact") Long id,@RequestBody @Valid  ContactDTO contact){
        contactService.updateContact(id,contact);
    }






}
