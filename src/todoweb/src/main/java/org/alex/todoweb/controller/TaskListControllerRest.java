package org.alex.todoweb.controller;


import org.alex.todoweb.model.TaskList;
import org.alex.todoweb.repository.TaskListRepository;
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
public class TaskListControllerRest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskListControllerRest.class);

    @Resource
    private TaskListRepository repository;


    @RequestMapping("/lists")
    public TaskList[] getAllTaskLists() {

        LOGGER.info("REST call: getAllTaskLists");

        List<TaskList> allLists = repository.findAll();

        LOGGER.info("Total tasks found: " + allLists.size());
        return allLists.toArray(new TaskList[allLists.size()]);
    }

    @RequestMapping(value = "/lists", method = RequestMethod.POST)
    public TaskList createTaskList(@RequestBody TaskList newList) {

        LOGGER.info("REST call: createTaskList: " + newList);

        TaskList saved = repository.save(newList);

        //return saved.getId();
        return saved;
    }
}
