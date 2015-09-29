package org.alex.todoweb.service;

import org.alex.todoweb.dto.TaskDTO;
import org.alex.todoweb.model.Task;
import java.util.List;


public interface TaskService {


    public Task add(TaskDTO added);


    public Task deleteById(Integer id) throws NotFoundException;



    public List<Task> findAll();


    public Task findById(Integer id) throws NotFoundException;


    public Task update(TaskDTO updated) throws NotFoundException;
}
