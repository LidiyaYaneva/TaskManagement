package com.primeholding.taskmanagement.services;

import com.primeholding.taskmanagement.models.dtos.*;
import com.primeholding.taskmanagement.models.entities.Task;
import com.primeholding.taskmanagement.models.enums.TaskStatus;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    void saveTask(CreateTaskDTO createTaskDTO);

    List<DisplayTaskDTO> getTasksToDisplay();

    void deleteTaskById(Long id);

    List<DisplayTaskDTO> searchTasksByTitle(String inputTitle);

    void completeTaskById(Long id);

    List<DisplayTaskForFinishedPastMonthStatistics> displayTasksFinishedPastMonth();

    List<Task> getTasksFinishedPastMonth();

    void setAssigneeTasksToNull(Long id);

    Long getNumberOfTasksByStatus(Long id, TaskStatus status);

    List<DisplayTaskInProgress> getTasksInProgress();

    List<DisplayDelayedTask> getDelayedTasks();

    List<TotalTaskPerClient> getTasksPerClient();

    Optional<CreateTaskDTO> getById(Long id);
}
