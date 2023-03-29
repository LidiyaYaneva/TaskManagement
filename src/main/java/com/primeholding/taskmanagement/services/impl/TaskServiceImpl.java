package com.primeholding.taskmanagement.services.impl;

import com.primeholding.taskmanagement.models.dtos.*;
import com.primeholding.taskmanagement.models.entities.ClientEntity;
import com.primeholding.taskmanagement.models.entities.Employee;
import com.primeholding.taskmanagement.models.entities.Task;
import com.primeholding.taskmanagement.models.enums.TaskStatus;
import com.primeholding.taskmanagement.repositories.ClientRepository;
import com.primeholding.taskmanagement.repositories.EmployeeRepository;
import com.primeholding.taskmanagement.repositories.TaskRepository;
import com.primeholding.taskmanagement.services.TaskService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;

    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, EmployeeRepository employeeRepository, ClientRepository clientRepository, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.employeeRepository = employeeRepository;
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveTask(CreateTaskDTO createTaskDTO) {
        Long employeeId = createTaskDTO.getAssignee();
        Long clientId = createTaskDTO.getClient();
        Optional<Employee> assignee = this.employeeRepository.findById(employeeId);
        Optional<ClientEntity> client = this.clientRepository.findById(clientId);

        Task task = this.modelMapper.map(createTaskDTO, Task.class);
        assignee.ifPresent(task::setAssignee);
        client.ifPresent(task::setClient);
        this.taskRepository.save(task);
    }

    @Override
    public List<DisplayTaskDTO> getTasksToDisplay() {
        PageRequest pageRequest = PageRequest.of(0, 18, Sort.by(Sort.Direction.ASC, "id"));
        return this.taskRepository.findAllByOrderById(pageRequest)
                .stream()
                .map(task -> this.modelMapper.map(task, DisplayTaskDTO.class))
                .toList();
    }

    @Override
    public void deleteTaskById(Long id) {
        this.taskRepository.deleteById(id);
    }

    @Override
    public List<DisplayTaskDTO> searchTasksByTitle(String inputTitle) {
        List<Task> tasks;

        if (inputTitle == null) {
            tasks = this.taskRepository.findAll();
        } else {
            tasks = this.taskRepository.findAllByTitleContaining(inputTitle.trim());
        }
        return Arrays.stream(this.modelMapper.map(tasks, DisplayTaskDTO[].class)).toList();
    }

    @Override
    public void completeTaskById(Long id) {
        Optional<Task> optionalTask = this.taskRepository.findById(id);
        if (optionalTask.isEmpty()) {
            LOGGER.info("Task with id and [{}] not found.", id);
        } else {
            Task task = optionalTask.get();
            task.setStatus(TaskStatus.COMPLETED);
            task.setFinishDate(LocalDate.now());
            this.taskRepository.save(task);
        }
    }

    public List<DisplayTaskForFinishedPastMonthStatistics> displayTasksFinishedPastMonth() {

        List<Task> tasks = getTasksFinishedPastMonth();

        return Arrays.stream(this.modelMapper.map(tasks, DisplayTaskForFinishedPastMonthStatistics[].class))
                .toList();
    }

    public List<Task> getTasksFinishedPastMonth() {

        LocalDate currentDate = LocalDate.now().minusDays(1);
        LocalDate pastDate = currentDate.minusMonths(1);

        return this.taskRepository.findAllFinishedPastMonth(pastDate, currentDate);
    }

    @Override
    public void setAssigneeTasksToNull(Long id) {
        List<Task> tasks = this.taskRepository.findAllByAssigneeId(id);
        for (Task t : tasks) {
            t.setAssignee(null);
            this.taskRepository.save(t);
        }
    }

    @Override
    public Long getNumberOfTasksByStatus(Long id, TaskStatus status) {
        return this.taskRepository.countByAssignee_IdAndStatus(id, status);
    }

    @Override
    public List<DisplayTaskInProgress> getTasksInProgress() {
        return this.taskRepository.findAllByStatusAndFinishDateIsNull(TaskStatus.ACTIVE)
                .stream()
                .map(task -> this.modelMapper.map(task, DisplayTaskInProgress.class))
                .toList();
    }

    @Override
    public List<DisplayDelayedTask> getDelayedTasks() {

        return this.taskRepository.findAllByStatus(TaskStatus.COMPLETED)
                .stream()
                .filter(task -> task.getFinishDate() != null && task.getFinishDate().isAfter(task.getDueDate()))
                .map(task -> this.modelMapper.map(task, DisplayDelayedTask.class))
                .peek(t -> t.setDaysDelayed(Period.between(t.getDueDate(), t.getFinishDate()).getDays()))
                .toList();
    }

    @Override
    public List<TotalTaskPerClient> getTasksPerClient() {
        List<ClientEntity> clients = this.clientRepository.findAll();
        return clients.stream()
                .map(client -> new TotalTaskPerClient(client.getName(), client.getTasks().size()))
                .sorted((Comparator.comparing(TotalTaskPerClient::getTaskCount)).reversed())
                .toList();
    }

    @Override
    public Optional<CreateTaskDTO> getById(Long id) {
        return this.taskRepository.findById(id).map(this::mapTaskToDTO);
    }

    private CreateTaskDTO mapTaskToDTO(Task task) {
        Long assigneeId = null;
        Long clientId = null;
        if (task.getAssignee() != null) {
            assigneeId = task.getAssignee().getId();
        }
        if (task.getClient() != null) {
            clientId = task.getClient().getId();
        }
        return new CreateTaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                assigneeId,
                task.getDueDate(),
                task.getStatus(),
                clientId);
    }

}
