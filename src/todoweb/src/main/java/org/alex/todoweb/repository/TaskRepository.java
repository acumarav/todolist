package org.alex.todoweb.repository;

import org.alex.todoweb.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task,Integer>{
}
