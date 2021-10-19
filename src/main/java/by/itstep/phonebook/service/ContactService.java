package by.itstep.phonebook.service;

import by.itstep.phonebook.dao.entity.Contact;
import by.itstep.phonebook.dao.entity.Group;

import java.util.List;

public interface ContactService {

    Contact createContact(Contact contact) throws ServiceException;

    Contact addContactToGroup(Contact contact, Group group);

    List<Contact> getAll();
}
