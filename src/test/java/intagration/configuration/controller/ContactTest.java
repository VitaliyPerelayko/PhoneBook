package intagration.configuration.controller;

import by.itstep.phonebook.dao.entity.Contact;
import by.itstep.phonebook.dao.entity.Group;
import by.itstep.phonebook.data.ContactTestDataFactory;
import by.itstep.phonebook.service.ContactService;
import by.itstep.phonebook.service.GroupService;
import intagration.configuration.TestConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {TestConfiguration.class})
@WebAppConfiguration
@Transactional
public class ContactTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ContactService contactService;

    @Autowired
    private GroupService groupService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void getByIdTest() throws Exception {
        Group family = groupService.save(new Group("family"));
        Group work = groupService.save(new Group("work"));
        Contact contact = ContactTestDataFactory.createContact(
                "TestWF1",
                "+375-29-1234567",
                "test_wf.1@emai.com",
                Set.of(family, work));
        Contact testWF1 = contactService.save(contact);
        mockMvc.perform(get("/rest/v1.0/contact/" + testWF1.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName").value("TestWF1"))
                .andExpect(jsonPath("$.phones[0]").value("+375-29-1234567"))
                .andReturn();
    }

    @Test
    public void testSaveBadRequest() throws Exception {
        Group family = groupService.save(new Group("family"));
        Group work = groupService.save(new Group("work"));
        String body = "{\"firsName\":\"testFirstName\",\"lastName\":\"TestWF1\",\"phones\":[\"+375-29-1234567\"],\"email\":\"test_wf.1@emai.com\",\"groups\":[{\"id\":4},{\"id\":3}]}";
        mockMvc.perform(post("/rest/v1.0/contact/").contentType(APPLICATION_JSON_UTF8).content(body))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

//    @Test
//    public void getByIdTest() throws Exception {
//        by.itstep.phonebook.service.ServiceException: All groups should be persist objects
////        Group family = groupService.save(new Group("family"));
////        Group work = groupService.save(new Group("work"));
//        Group family = new Group("family");
//        Group work = new Group("work");
//
//        Contact contact = ContactTestDataFactory.createContact(
//                "TestWF1",
//                "+375-29-1234567",
//                "test_wf.1@emai.com",
//                Set.of(family, work));
//        Contact testWF1 = contactService.save(contact);
//        mockMvc.perform(get("/rest/v1.0/contact/" + testWF1.getId()))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.lastName").value("TestWF1"))
//                .andExpect(jsonPath("$.phones[0]").value("+375-29-1234567"))
//                .andReturn();
//    }
}
