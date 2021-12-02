package by.itstep.phonebook.controller;

import by.itstep.phonebook.dao.entity.Contact;
import by.itstep.phonebook.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/rest/v1.0/contact")
public class ContactRESTController {

    private final ContactService contactService;

    private static final String SUCCESS = "SUCCESS";

    public ContactRESTController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getAll() {
        return new ResponseEntity<>(contactService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Contact> getById(@PathVariable Long id) {
        return new ResponseEntity<>(contactService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        System.out.println("=====CONTROLLER======= " + contact);
        return new ResponseEntity<>(contactService.save(contact), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Contact> updateContact(@RequestBody Contact contact, @PathVariable Long id) {
        if (!Objects.equals(contact.getId(), id)) throw new ControllerException("Unexpected contact id. Path id %s doesn't match contact id %s", id, contact);
        return new ResponseEntity<>(contactService.update(contact), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteContact(@RequestBody Contact contact) {
        contactService.delete(contact);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Long id) {
        Contact contact = new Contact();
        contact.setId(id);
        return deleteContact(contact);
    }
}
