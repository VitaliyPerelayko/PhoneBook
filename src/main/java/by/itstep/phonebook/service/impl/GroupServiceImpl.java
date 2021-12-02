package by.itstep.phonebook.service.impl;

import by.itstep.phonebook.dao.entity.Group;
import by.itstep.phonebook.dao.repository.GroupRepository;
import by.itstep.phonebook.service.GroupService;
import by.itstep.phonebook.service.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group getByName(String name){
        return groupRepository.getByName(name).orElseThrow(() -> new ServiceException("NO Group with name %s", name));
    }

    @Override
    public Group save(Group group){
        return groupRepository.saveAndFlush(group);
    }
}
