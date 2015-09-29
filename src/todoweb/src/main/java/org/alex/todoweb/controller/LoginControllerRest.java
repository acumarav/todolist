package org.alex.todoweb.controller;

import org.alex.todoweb.dto.LoginToken;
import org.alex.todoweb.model.User;
import org.alex.todoweb.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/rs")
public class LoginControllerRest {

    @Resource
    private UserRepository userRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginControllerRest.class);

    @RequestMapping(value = "/login", method={RequestMethod.POST}, produces = "application/json")
    public String  login(@RequestBody LoginToken loginData){

        if(loginData!=null)
        {
            LOGGER.info("REST call: Login");

           User user = userRepository.findByFirstName(loginData.getUsername());
            if(user!=null && loginData.getPassword().equals("pwd"))
            {
              String authToken =  String.format("{\"authenticated\":\"%s\",\"userId\":\"%s\"}",true,user.getId());
              return authToken;
            //return "{\"authenticated\":\"true\"}";
            }

        }

        return "{}";
    }
}

