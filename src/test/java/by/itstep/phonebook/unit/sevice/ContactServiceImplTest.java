package by.itstep.phonebook.unit.sevice;

import by.itstep.phonebook.dao.entity.Contact;
import by.itstep.phonebook.dao.entity.Group;
import by.itstep.phonebook.dao.repository.ContactRepository;
import by.itstep.phonebook.service.ServiceException;
import by.itstep.phonebook.service.impl.ContactServiceImpl;
import by.itstep.phonebook.service.validation.ServiceValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static by.itstep.phonebook.data.ContactTestDataFactory.createContact;


@ExtendWith(MockitoExtension.class)
public class ContactServiceImplTest {

    @InjectMocks
    private ContactServiceImpl contactService;
    @Mock
    private ContactRepository contactRepository;

    @Test
    public void createContactTest_Success() {
        Contact validContact = createContact("TestLastName", "+375-29-1234567");
        contactService.save(validContact);
    }

    @Test()
    public void createContactTest_Exception() {
        Contact invalidContact = createContact("TestLastName", "+375-29 1234567");
        Assertions.assertThrows(ServiceValidationException.class, () -> contactService.save(invalidContact));
    }

    @Test
    public void updateContactTest_Success(){
        Contact validContact = createContact("TestLastName", "+375-29-1234567");
        validContact.setId(56L);
        contactService.update(validContact);
    }

    @Test
    public void updateContactTest_Id(){
        Contact validContact = createContact("TestLastName", "+375-29-1234567");
        Assertions.assertThrows(ServiceException.class, () -> contactService.update(validContact));
    }

    @Test
    public void getByIdTest_Success(){
        Contact validContact = createContact("TestLastName", "+375-29-1234567");
        Mockito.when(contactRepository.findById(1L)).thenReturn(Optional.of(validContact));
        Assertions.assertEquals(validContact, contactService.getById(1L));
    }

    @Test
    public void getByIdTest_NoContact(){
        Assertions.assertThrows(ServiceException.class, () -> contactService.getById(1L));
    }

    @Test
    public void addContactToGroupTest(){
        Contact validContact = createContact("TestLastName", "+375-29-1234567");
        validContact.setId(1L);
        contactService.addContactToGroup(validContact, new Group(1L, "Family"));
    }
}
