package by.itstep.phonebook.service.impl;

import by.itstep.phonebook.dao.entity.Contact;
import by.itstep.phonebook.dao.entity.Group;
import by.itstep.phonebook.dao.repository.ContactRepository;
import by.itstep.phonebook.service.ContactService;
import by.itstep.phonebook.service.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static by.itstep.phonebook.Utils.*;

@Service
public class ContactServiceImpl implements ContactService {

    private ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    @Transactional
    public Contact createContact(Contact contact) throws ServiceException {
        validateNewContact(contact);
        Set<Group> groups = contact.getGroups();
        if (groups != null && !groups.isEmpty()) {
            groups = groups.stream().filter(group ->
                    group.getId() == null).collect(Collectors.toSet());
            if (groups.isEmpty()) {
                contact.setGroups(null);
            } else {
                contact.setGroups(groups);
            }
        }
        return contactRepository.save(contact);
    }

    @Override
    @Transactional
    public Contact addContactToGroup(Contact contact, Group group) {
        return contact;
    }

    @Override
    public List<Contact> getAll() {
        return contactRepository.findAll();
    }

    private void validateNewContact(Contact contact) throws ServiceException {
        if (contact == null) throw new ServiceException("Contact is null");
        if (!isText(contact.getFirsName()))
            throw new ServiceException("Contact firstName must contain only letters");
        if (!isText(contact.getLastName()))
            throw new ServiceException("Contact lastName must contain only letters");
        if (!isEmail(contact.getEmail())) {
            throw new ServiceException("Contact email is not valid");
        }
        if (contact.getPhones().isEmpty()) {
            throw new ServiceException("Contact must contain at least one phone");
        } else {
            if (!isPhones(contact.getPhones()))
                throw new ServiceException("Contact phone format is illegal. Try +###-##-#######");
        }
    }
}
