package org.alex.todoweb.dto;

import org.alex.todoweb.model.Task;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;


public class TaskDTO {

    public TaskDTO(){}

    public static TaskDTO create(Task task){
        TaskDTO dto=new TaskDTO();

        dto.setPriority(task.getPriority().name());
        dto.setDescription(task.getDescription());
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setVersion(task.getVersion());
        dto.setStatus(task.getStatus().name());
        dto.setAssignee(task.getAssignee()!=null?task.getAssignee().getId():null);
        dto.setTaskListId(task.getTaskList().getId());

        return dto;
    }

    private Integer id;

    @NotEmpty
    @Column(length = 300)
    private String description;

    @NotEmpty
    @Column(length = 50)
    private String title;


    @Column(length = 30)
    private String priority;

    private Integer assignee;

    private Integer taskListId;

    private String status;

    private Integer version;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Integer getTaskListId() {
        return taskListId;
    }

    public void setTaskListId(Integer taskListId) {
        this.taskListId = taskListId;
    }

    public Integer getAssignee() {
        return assignee;
    }

    public void setAssignee(Integer assignee) {
        this.assignee = assignee;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public Integer getVersion() {
        return version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }
}
