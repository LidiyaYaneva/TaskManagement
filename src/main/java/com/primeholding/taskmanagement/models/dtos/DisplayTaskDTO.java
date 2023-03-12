package com.primeholding.taskmanagement.models.dtos;

import com.primeholding.taskmanagement.models.enums.TaskStatus;

import java.time.LocalDate;

public class DisplayTaskDTO {

    private Long id;

    private String title;

    private String description;

    private String assigneeFullName;

    private LocalDate dueDate;

    private TaskStatus status;

    private String clientName;

    public DisplayTaskDTO() {
    }

    public DisplayTaskDTO(Long id, String title, String description, String assigneeFullName, LocalDate dueDate, TaskStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.assigneeFullName = assigneeFullName;
        this.dueDate = dueDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssigneeFullName() {
        return assigneeFullName;
    }

    public void setAssigneeFullName(String assigneeFullName) {
        this.assigneeFullName = assigneeFullName;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
