package by.itstep.phonebook.dao.repository;

import by.itstep.phonebook.dao.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> getAllByEmail(String email);
}
