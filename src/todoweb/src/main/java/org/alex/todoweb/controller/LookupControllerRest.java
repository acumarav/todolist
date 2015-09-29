package org.alex.todoweb.controller;

import org.alex.todoweb.model.Priority;
import org.alex.todoweb.model.Status;
import org.alex.todoweb.model.User;
import org.alex.todoweb.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/rs")
public class LookupControllerRest {

    private static final Logger LOGGER = LoggerFactory.getLogger(LookupControllerRest.class);

    @Resource
    private UserRepository userRepository;

    @RequestMapping("/users")
    public User[] getAllTask(){

        LOGGER.info("REST call: getAllUser");

        List<User> allUsers = userRepository.findAll();

        LOGGER.info("Total users found: " + allUsers.size());
        return allUsers.toArray(new User[allUsers.size()]);
    }

    @RequestMapping("/statuses")
    public String[] getStatuses(){
        return Status.getNames();
    }

    @RequestMapping("/priorities")
    public String[] getPriorities(){
        return Priority.getNames();
    }
}
