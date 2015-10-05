package org.alex.todoweb.controller;


import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import org.alex.todoweb.dto.TaskDTO;
import org.alex.todoweb.model.*;
import org.alex.todoweb.repository.TaskListRepository;
import org.alex.todoweb.repository.TaskRepository;
import org.alex.todoweb.repository.UserRepository;
import org.alex.todoweb.service.ConcurrencyException;
import org.alex.todoweb.service.NotFoundException;
import org.alex.todoweb.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/rs")
public class TaskControllerRest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskControllerRest.class);

    @Resource
    private TaskService taskService;

    @Resource
    private TaskRepository repository;


    @RequestMapping("/tasks")
    public TaskDTO[] getAllTask(){

        LOGGER.info("REST call: getAllTasks");

        List<Task> allTasks = repository.findAll();

        LOGGER.info("Total tasks found: "+allTasks.size());
        TaskDTO[] dtos = allTasks.stream().map(x -> TaskDTO.create(x)).toArray(size -> new TaskDTO[size]);
        return dtos;
    }

   @RequestMapping(value = "/task/{id}",method = RequestMethod.GET)
    public Task[] getTask(@PathVariable("id") int id) throws NotFoundException {

        LOGGER.info("REST call: getTask by id: "+id);
        Task task = repository.findOne(id);

        return new Task[]{task};
    }

    @RequestMapping(value = "/task", method = RequestMethod.POST)
    public Task[] createTask(@RequestBody TaskDTO taskDTO) {

        LOGGER.info("REST call: create Task: " + taskDTO);

        taskDTO.setStatus(Status.New.name());

        Task saved=taskService.add(taskDTO);

        return new Task[]{saved};
    }

    @RequestMapping(value = "/task", method = RequestMethod.PUT)
    public Task[] updateTask(@RequestBody TaskDTO taskDTO) throws ConcurrencyException {

        LOGGER.info("REST call: update Task: " + taskDTO);

        Task updated = taskService.update(taskDTO);

        return new Task[]{updated};
    }


    @RequestMapping(value = "/task/{id}",method = RequestMethod.DELETE)
    public void deleteTask(@PathVariable("id") int id) throws NotFoundException {

        LOGGER.info("REST call: deleteTask by id: "+id);
        repository.delete(id);

        LOGGER.info("deleted task: "+id);
    }
}
