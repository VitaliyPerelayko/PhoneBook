package by.itstep.phonebook.controller;

import by.itstep.phonebook.dao.entity.Group;
import by.itstep.phonebook.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/v1.0/group")
public class GroupRESTController {

    final private GroupService groupService;

    public GroupRESTController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public ResponseEntity<List<Group>> getAll() {
        return new ResponseEntity<>(groupService.getAll(), HttpStatus.OK);
    }
}
