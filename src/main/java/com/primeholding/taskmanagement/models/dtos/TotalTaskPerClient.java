package com.primeholding.taskmanagement.models.dtos;

public class TotalTaskPerClient {

    private String clientName;

    private long taskCount;

    public TotalTaskPerClient() {
    }

    public TotalTaskPerClient(String clientName, long taskCount) {
        this.clientName = clientName;
        this.taskCount = taskCount;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public long getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(long taskCount) {
        this.taskCount = taskCount;
    }
}
