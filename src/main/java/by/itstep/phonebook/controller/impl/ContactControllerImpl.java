package by.itstep.phonebook.controller.impl;

import by.itstep.phonebook.controller.ContactController;
import by.itstep.phonebook.dao.entity.Contact;
import by.itstep.phonebook.service.ContactService;
import by.itstep.phonebook.service.ServiceException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class ContactControllerImpl implements ContactController {

    private ContactService contactService;

    public ContactControllerImpl(ContactService contactService) {
        this.contactService = contactService;
    }

    @Override
    public void createContact(Contact contact) throws ServiceException {
        contactService.createContact(contact);
    }

    @Override
    public List<Contact> getAll() {
        return contactService.getAll();
    }

    @Override
    public void deleteContact(Contact contact) {

    }

    @Override
    public void findContact(String phone) {

    }

    @Override
    public Set<Contact> findByName(String lastName) {
        return null;
    }
}
