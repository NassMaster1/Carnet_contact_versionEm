package org.miage.carnet_contact.controller;


import org.miage.carnet_contact.application.dto.ContactDTO;
import org.miage.carnet_contact.application.dto.ContactGroupDTO;
import org.miage.carnet_contact.application.service.IContactGroupService;
import org.miage.carnet_contact.util.CostumeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/v1/contactGroup", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContactGroupController {

    private IContactGroupService contactGroupService;

    @Autowired
    public ContactGroupController(IContactGroupService contactGroupService) {
        this.contactGroupService = contactGroupService;
    }

    @PostMapping("")
    public ResponseEntity<CostumeResponse<String>> SaveContactGroup(@RequestBody @Valid ContactGroupDTO ContactGroupDTO) {
        contactGroupService.SaveContactGroup(ContactGroupDTO);
        return new ResponseEntity<>(new CostumeResponse<>(format("contact group %s created", ContactGroupDTO.GroupName())), CREATED);
    }

    @GetMapping(value = "/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ContactGroupDTO> findAllContact (){
        return contactGroupService.findAllContactGroup();
    }


    @GetMapping(value = "/{id_group}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ContactGroupDTO findConatctGroupById (@PathVariable("id_group") Long id){
        return contactGroupService.findConatctGroupById(id) ;
    }

    @GetMapping(value = "findByName/{nameGroup}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ContactGroupDTO findConatctGroupByNameGroup (@PathVariable("nameGroup") String nameGroup){
        return contactGroupService.findContactGroupWithName(nameGroup) ;
    }

    @PostMapping("/addListContact/{id_contact}/{id_group}")
    public ResponseEntity<CostumeResponse<String>> AddListContactToGroup(@PathVariable("id_contact") Long id_contact,@PathVariable("id_group") Long id_group) {
        contactGroupService.AddContactToGroup(id_contact,id_group);
        return new ResponseEntity<>(new CostumeResponse<>(format("contact id %s added",id_contact )), CREATED);
    }

    @DeleteMapping("/{id_contact_group}")
    public void deleteContactGroup(@PathVariable("id_contact_group") Long id) {
        contactGroupService.deleteContactGroup(id);
    }

    @PutMapping("/{id_contact_group}")
    public void updateContact(@PathVariable("id_contact_group") Long id,@RequestBody @Valid  ContactGroupDTO contactGroupDto){
        contactGroupService.updateGroup(id,contactGroupDto);
    }

    @GetMapping(value = "/findContactsFromGroup/{id_group}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ContactDTO> findContactsFromGroup (@PathVariable("id_group") Long id){
        return contactGroupService.findContactsFromGroup(id) ;
    }



}
