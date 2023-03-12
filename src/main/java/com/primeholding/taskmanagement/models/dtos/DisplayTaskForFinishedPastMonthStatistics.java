package com.primeholding.taskmanagement.models.dtos;

import java.time.LocalDate;

public class DisplayTaskForFinishedPastMonthStatistics {

    private String title;
    private String assigneeFullName;
    private LocalDate finishDate;

    public DisplayTaskForFinishedPastMonthStatistics() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAssigneeFullName() {
        return assigneeFullName;
    }

    public void setAssigneeFullName(String assigneeFullName) {
        this.assigneeFullName = assigneeFullName;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }
}
