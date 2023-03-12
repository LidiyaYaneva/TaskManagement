package com.primeholding.taskmanagement.services;

import java.io.IOException;

public interface SeedService {

    boolean areEmployeesSeeded();

    boolean areTasksSeeded();

    String readEmployeesFromFile() throws IOException;

    String readTasksFromFile() throws IOException;

    void seedEmployees() throws IOException;

    void seedTasks() throws IOException;
}
