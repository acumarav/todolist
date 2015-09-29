package org.alex.todoweb.repository;

import org.alex.todoweb.model.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskListRepository extends JpaRepository<TaskList, Integer> {
}
