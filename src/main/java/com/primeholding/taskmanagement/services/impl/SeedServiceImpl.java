package com.primeholding.taskmanagement.services.impl;

import com.google.gson.Gson;
import com.primeholding.taskmanagement.models.dtos.SeedEmployeeDTO;
import com.primeholding.taskmanagement.models.dtos.SeedTaskDTO;
import com.primeholding.taskmanagement.models.entities.ClientEntity;
import com.primeholding.taskmanagement.models.entities.Employee;
import com.primeholding.taskmanagement.models.entities.Task;
import com.primeholding.taskmanagement.models.enums.TaskStatus;
import com.primeholding.taskmanagement.repositories.ClientRepository;
import com.primeholding.taskmanagement.repositories.EmployeeRepository;
import com.primeholding.taskmanagement.repositories.TaskRepository;
import com.primeholding.taskmanagement.services.SeedService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SeedServiceImpl implements SeedService {

    private final EmployeeRepository employeeRepository;

    private final TaskRepository taskRepository;

    private final ClientRepository clientRepository;

    private final Gson gson;

    private final ModelMapper modelMapper;

    @Autowired
    public SeedServiceImpl(EmployeeRepository employeeRepository, TaskRepository taskRepository, ClientRepository clientRepository, Gson gson, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.taskRepository = taskRepository;
        this.clientRepository = clientRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean areEmployeesSeeded() {
        return this.employeeRepository.count() > 0;
    }

    @Override
    public boolean areTasksSeeded() {
        return this.taskRepository.count() > 0;
    }

    @Override
    public String readEmployeesFromFile() throws IOException {
        Path path = Path.of("src", "main", "resources", "static", "seedExampleData", "employees.json");
        return Files.readString(path);
    }

    @Override
    public String readTasksFromFile() throws IOException {
        Path path = Path.of("src", "main", "resources", "static", "seedExampleData", "tasks.json");
        return Files.readString(path);
    }

    @Override
    public void seedEmployees() throws IOException {

        String json = readEmployeesFromFile();
        SeedEmployeeDTO[] seedEmployeeDTO = this.gson.fromJson(json, SeedEmployeeDTO[].class);

        List<Employee> employees = new ArrayList<>();

        for (SeedEmployeeDTO dto : seedEmployeeDTO) {
            Employee employee = this.modelMapper.map(dto, Employee.class);
            employees.add(employee);
        }
        this.employeeRepository.saveAll(employees);

    }

    @Override
    public void seedTasks() throws IOException {

        String json = readTasksFromFile();
        SeedTaskDTO[] seedTaskDTO = this.gson.fromJson(json, SeedTaskDTO[].class);

        List<Task> tasks = new ArrayList<>();

        for (SeedTaskDTO dto : seedTaskDTO) {
            Task task = this.modelMapper.map(dto, Task.class);
            Optional<Employee> optionalEmployee = this.employeeRepository.findById(dto.getAssignee());
            optionalEmployee.ifPresent(task::setAssignee);
            if (dto.getClient() != null) {
                Optional<ClientEntity> optionalClientEntity = this.clientRepository.findById(dto.getClient());
                optionalClientEntity.ifPresent(task::setClient);
            }
            if (!task.getStatus().equals(TaskStatus.COMPLETED)) //this is a temporary solution, because ModelMapper does not convert String to LocalDate if String is null
                task.setFinishDate(null);
            tasks.add(task);
        }
        this.taskRepository.saveAll(tasks);
    }
}
