package com.primeholding.taskmanagement.models.dtos;

import com.primeholding.taskmanagement.models.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class CreateTaskDTO {

    Long id;

    @NotBlank
    @Size(min=2)
    private String title;

    @NotBlank
    @Size(min=2)
    private String description;

    @NotNull
    private LocalDate dueDate;

    @NotNull
    private Long assignee;

    @NotNull
    private TaskStatus status;

    private Long client;

    public CreateTaskDTO() {
    }

    public CreateTaskDTO(Long id, String title, String description, Long assignee, LocalDate dueDate,
                         TaskStatus status, Long client) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.assignee = assignee;
        this.dueDate = dueDate;
        this.status = status;
        this.client = client;
    }

    public Long getAssignee() {
        return assignee;
    }

    public void setAssignee(Long assignee) {
        this.assignee = assignee;
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

    public Long getClient() {
        return client;
    }

    public void setClient(Long client) {
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
