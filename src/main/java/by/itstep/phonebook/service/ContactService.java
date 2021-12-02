package by.itstep.phonebook.service;

import by.itstep.phonebook.dao.entity.Contact;
import by.itstep.phonebook.dao.entity.Group;

import java.util.List;

public interface ContactService {

    Contact save(Contact contact);

    Contact update(Contact contact);

    Contact addContactToGroup(Contact contact, Group group);

    List<Contact> getAll();

    List<Contact> getAllByEmail(String email);

    Contact getById(Long id);

    void delete(Contact contact);

    Boolean isExist(Long Id);
}
