package by.itstep.phonebook.dao.repository;

import by.itstep.phonebook.dao.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    Optional<Group> getByName(String name);
}
