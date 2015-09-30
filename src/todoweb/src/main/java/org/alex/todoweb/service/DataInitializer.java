package org.alex.todoweb.service;

import org.alex.todoweb.model.*;
import org.alex.todoweb.repository.TaskListRepository;
import org.alex.todoweb.repository.UserRepository;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryTaskService.class);

    @Resource
    private UserRepository userRepository;

    @Resource
    TaskListRepository taskListRepository;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        List<User> users = userRepository.findAll();

        createTasks(users);

        LOGGER.info("App Event: " + ToStringBuilder.reflectionToString(event));
    }

    private void createTasks(List<User> users) {

        TaskList tasks = new TaskList();
        tasks.setTitle("Default task list");

        Task firstTask = new Task();
        firstTask.setTitle("01. XSS spring");
        firstTask.setStatus(Status.Complete);
        firstTask.setPriority(Priority.Urgent);
        firstTask.setDescription("Support spring default xss protection in client authentication");
        firstTask.setAssignee(users.get(0));
        firstTask.setTaskList(tasks);

        Task secondTask = new Task();
        secondTask.setTitle("02. DB concurency");
        secondTask.setStatus(Status.New);
        secondTask.setPriority(Priority.Medium);
        secondTask.setDescription("DB model supports optimistic concurency");
        secondTask.setAssignee(users.get(1));
        secondTask.setTaskList(tasks);

        Task thirdTask = new Task();
        thirdTask.setTitle("03. Clear DTO and Model entity ");
        thirdTask.setStatus(Status.InProgress);
        thirdTask.setPriority(Priority.Urgent);
        thirdTask.setDescription("Entities should not be exposed from Rest or Business Services, only DTOs");
        thirdTask.setAssignee(users.get(2));
        thirdTask.setTaskList(tasks);

        Task fourthTask = new Task();
        fourthTask.setTitle("04. Host Live Demo");
        fourthTask.setStatus(Status.InProgress);
        fourthTask.setPriority(Priority.Urgent);
        fourthTask.setDescription("Demo App should be available in Web, find a web place to host ");
        fourthTask.setAssignee(users.get(0));
        fourthTask.setTaskList(tasks);

        Task fithTask = new Task();
        fithTask.setTitle("05. WebAnalytics");
        fithTask.setStatus(Status.InProgress);
        fithTask.setPriority(Priority.Urgent);
        fithTask.setDescription("Integrate Web Analytics into live demo ");
        fithTask.setAssignee(users.get(0));
        fithTask.setTaskList(tasks);


        Task sixthTask = new Task();
        sixthTask.setTitle("06. Registration");
        sixthTask.setStatus(Status.InProgress);
        sixthTask.setPriority(Priority.Urgent);
        sixthTask.setDescription("Add live user registration and remove default logins");
        sixthTask.setAssignee(users.get(0));
        sixthTask.setTaskList(tasks);

        Task seventhTask = new Task();
        seventhTask.setTitle("07. Brush Up Git Repo");
        seventhTask.setStatus(Status.InProgress);
        seventhTask.setPriority(Priority.Urgent);
        seventhTask.setDescription("Create description/intro on the git repo home page");
        seventhTask.setAssignee(users.get(0));
        seventhTask.setTaskList(tasks);


        tasks.getTasks().addAll(Arrays.asList(firstTask, secondTask, thirdTask,fourthTask, fithTask, sixthTask,seventhTask));

        taskListRepository.save(tasks);
    }

}
