package org.alex.todoweb.service;

import org.alex.todoweb.dto.TaskDTO;
import org.alex.todoweb.model.*;
import org.alex.todoweb.repository.TaskListRepository;
import org.alex.todoweb.repository.TaskRepository;
import org.alex.todoweb.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RepositoryTaskService implements TaskService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryTaskService.class);

    @Resource
    private TaskRepository repository;
    @Resource
    private UserRepository userRepository;
    @Resource
    private TaskListRepository listRepository;

    @Transactional(readOnly = true)
    @Override
    public Task add(TaskDTO dto) {

        Task task = new Task();

        User assignee = userRepository.findOne(dto.getAssignee());
        TaskList taskList = listRepository.findOne(dto.getTaskListId());

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(Status.valueOf(dto.getStatus()));
        task.setPriority(Priority.parse(dto.getPriority()));

        task.setStatus(Status.New);

        task.setAssignee(assignee);
        task.setTaskList(taskList);

        Task saved = repository.saveAndFlush(task);

        return saved;
    }

    @Transactional(rollbackFor = NotFoundException.class)
    @Override
    public Task deleteById(Integer id) throws NotFoundException {
        LOGGER.debug("Deleting task by id: {}", id);

        Task deleted = findById(id);

        repository.delete(deleted.getId());

        repository.flush();

        LOGGER.debug("Deleted task: {}", deleted);

        return deleted;
    }


    @Transactional(readOnly = true)
    @Override
    public List<Task> findAll() {
        LOGGER.debug("Finding all tasks");
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Task findById(Integer id) throws NotFoundException {
        LOGGER.debug("Finding task by id: {}", id);

        Task found = repository.findOne(id);

        if (found == null) {
            LOGGER.debug("No task found with id: {}", id);
            throw new NotFoundException("No task found with id: " + id);
        }

        LOGGER.debug("Found task: {}", found);

        return found;
    }

    @Transactional(rollbackFor = NotFoundException.class)
    @Override
    public Task update(TaskDTO updated) throws ConcurrencyException {
        LOGGER.debug("Updating task with information: {}", updated);

        Task found = repository.findOne(updated.getId());


        if (updated.getVersion().equals(found.getVersion())) {

            Status status = Status.valueOf(updated.getStatus());
            found.setStatus(status);
            found.setPriority(Priority.parse(updated.getPriority()));

            Task saved = repository.saveAndFlush(found);

            return saved;
        }
        else{
            throw new ConcurrencyException("Data Has been updated");
        }
    }
}
