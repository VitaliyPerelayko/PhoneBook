package by.itstep.phonebook.service.impl;

import by.itstep.phonebook.dao.entity.Contact;
import by.itstep.phonebook.dao.entity.Group;
import by.itstep.phonebook.dao.repository.ContactRepository;
import by.itstep.phonebook.service.ContactService;
import by.itstep.phonebook.service.ServiceException;
import by.itstep.phonebook.service.validation.ServiceValidationException;
import org.h2.engine.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static by.itstep.phonebook.service.validation.ValidationUtils.*;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    @Transactional
    public Contact save(Contact contact) {
        validateNewContact(contact);
        return contactRepository.saveAndFlush(contact);
    }

    @Override
    @Transactional
    public Contact update(Contact contact) {
        if (contact.getId() == null) throw new ServiceException("You can update only persist contacts. contact %s does not have an id", contact);
        validateNewContact(contact);
        return contactRepository.saveAndFlush(contact);
    }

    @Override
    @Transactional
    public Contact addContactToGroup(Contact contact, Group group) {
        Set<Group> groups = contact.getGroups();
        if (groups != null) {
            groups.add(group);
        } else {
            groups = Collections.singleton(group);
        }
        contact.setGroups(groups);
        return update(contact);
    }

    @Override
    public List<Contact> getAll() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getById(Long id){
        return contactRepository.findById(id).orElseThrow(() -> new ServiceException("NO Contact with id %s", id));
    }

    @Override
    public void delete(Contact contact){
        if (contact.getId() == null) throw new ServiceException("Contact id is null");
        if (!isExist(contact.getId())) throw new ServiceException("NO Contact with id %s", contact.getId());
        contactRepository.deleteById(contact.getId());
    }

    @Override
    public Boolean isExist(Long id){
        return contactRepository.existsById(id);
    }

    private void validateNewContact(Contact contact) throws ServiceException {
        if (contact == null) throw new ServiceException("Contact is null");
        Set<Group> groups = contact.getGroups();
        if (groups != null && !groups.isEmpty()) {
            if (groups.stream().anyMatch(group -> group.getId() == null)){
                throw new ServiceException("All groups should be persist objects. \n %s", groups);
            }
        }
        if (!isEmail(contact.getEmail())) {
            throw new ServiceValidationException("Contact email is not valid");
        }
        if (contact.getPhones().isEmpty()) {
            throw new ServiceValidationException("Contact must contain at least one phone");
        } else {
            List<String> invalidPhones = filterInvalidPhones(contact.getPhones());
            if (!invalidPhones.isEmpty())
                throw new ServiceValidationException("Contact phone format is illegal \n%s. \nTry +###-##-#######", invalidPhones);
        }
    }
}
