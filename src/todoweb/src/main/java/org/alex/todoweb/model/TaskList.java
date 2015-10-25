package org.alex.todoweb.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name="tasklists")
public class TaskList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TASKID")
    private Integer id;

    @Column(nullable = false)
    private String title;

    @JsonIgnore
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="taskListCreatedBy", referencedColumnName = "USER_ID")
    private User createdBy;

    @JsonIgnore
    @OneToMany(targetEntity = Task.class, mappedBy = "taskList", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Task> tasks=new LinkedHashSet<Task>();

    public Set<Task> getTasks() {
        return tasks;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "TaskList{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", createdBy=" + createdBy +
                ", tasks=" + tasks +
                '}';
    }
}
