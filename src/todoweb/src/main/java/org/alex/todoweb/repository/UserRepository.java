package org.alex.todoweb.repository;

import org.alex.todoweb.model.Task;
import org.alex.todoweb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Integer> {

    User findByFirstName(String firstName);
}
