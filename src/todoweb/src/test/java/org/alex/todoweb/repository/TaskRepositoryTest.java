package org.alex.todoweb.repository;


import org.alex.todoweb.config.PersistenceTestContext;
import org.alex.todoweb.model.Status;
import org.alex.todoweb.model.Task;
import org.alex.todoweb.model.TaskList;
import org.alex.todoweb.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceTestContext.class})
public class TaskRepositoryTest {

    @Resource
    private TaskRepository taskRepository;
    @Resource
    private TaskListRepository taskListRepository;
    @Resource
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void test(){
        User user=buildUser("alex","t");
        User user2=buildUser("alex","t2");

        Task task=buildTask("title", "description",Status.InProgress);

        task.setAssignee(user);

        TaskList list=new TaskList();
        list.setTitle("Default task list");

        task.setTaskList(list);

        taskListRepository.save(list);
        userRepository.save(user2);
        userRepository.save(user);
        taskRepository.save(task);
        taskRepository.flush();

    }


    private Task buildTask(String title, String description, Status status) {
        Task tsk=new Task();
        tsk.setTitle(title);
        tsk.setDescription(description);
        tsk.setStatus(status);

        return tsk;
    }

    private User buildUser(String firstName, String secondName) {
        User usr=new User();
        usr.setFirstName(firstName);
        usr.setLastName(secondName);
        return usr;
    }


}