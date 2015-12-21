package org.alex.todoweb.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = {"/todo", "/", "/todolist"}, method = RequestMethod.GET)

    public String showHomePage(Model model) {
        LOGGER.debug("Rendering home page");


        return "index";
    }
}
