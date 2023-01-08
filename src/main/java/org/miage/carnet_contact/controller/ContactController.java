package org.miage.carnet_contact.controller;


import org.miage.carnet_contact.application.dto.ContactDTO;
import org.miage.carnet_contact.application.dto.DetailContactDTO;
import org.miage.carnet_contact.application.service.IContactService;
import org.miage.carnet_contact.model.Contact;
import org.miage.carnet_contact.util.CostumeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
public class ContactController {

    ApplicationContext context =  new ClassPathXmlApplicationContext("applicationContext.xml");


    @Autowired
    private IContactService contactService;

    @PostMapping("")
    public ResponseEntity<CostumeResponse<String>> save(@RequestBody @Valid DetailContactDTO contact) {

        contactService.saveContact(contact);
        return new ResponseEntity<>(new CostumeResponse<>(format("contact %s created", contact)), CREATED);
    }

    @PostMapping("/brut")
    public ResponseEntity<CostumeResponse<String>> saveBrut() {

         Contact contact = (Contact) context.getBean("idContact");

        contactService.saveContactBrut(contact);

        return new ResponseEntity<>(new CostumeResponse<>(format("contact %s created", contact)), CREATED);
    }


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ContactDTO> findcontactALl (){
        return contactService.findAllContact();
    }


    @GetMapping(value = "/{id_contact}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DetailContactDTO findcontactById (@PathVariable("id_contact") Long id){
        return contactService.findcontactById(id);
    }

    @GetMapping(value = "findbyFirstName_LastName/{firstName}/{lastName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ContactDTO> findbyFirstName_LastName (@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName){
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

    @GetMapping(value = "findbyKeyWord/{keyword}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ContactDTO> findContactByKeyWord ( @PathVariable("keyword") String keyword){
        return contactService.findContactByKeyWord(keyword);
    }

    @DeleteMapping("/{id_contact}")
    public void deleteContact(@PathVariable("id_contact") Long id) {
        contactService.deleteContact(id);
    }


    @PutMapping("/{id_contact}")
    public void updateContact(@PathVariable("id_contact") Long id,@RequestBody @Valid  DetailContactDTO detailContactDTO){
        contactService.updateContact(id,detailContactDTO);
    }






}
