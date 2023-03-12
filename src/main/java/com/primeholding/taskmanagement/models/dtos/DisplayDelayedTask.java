package com.primeholding.taskmanagement.models.dtos;

import java.time.LocalDate;

public class DisplayDelayedTask {

    private Long id;

    private String title;

    private String clientName;


    private String assigneeFullName;

    private LocalDate dueDate;

    private LocalDate finishDate;

    private long daysDelayed;

    public DisplayDelayedTask() {
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

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
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

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public long getDaysDelayed() {
        return daysDelayed;
    }

    public void setDaysDelayed(long daysDelayed) {
        this.daysDelayed = daysDelayed;
    }
}
