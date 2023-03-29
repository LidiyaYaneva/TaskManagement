package com.primeholding.taskmanagement.services.impl;

import com.primeholding.taskmanagement.models.dtos.DepartmentDTO;
import com.primeholding.taskmanagement.models.entities.Department;
import com.primeholding.taskmanagement.models.entities.Task;
import com.primeholding.taskmanagement.services.DepartmentService;
import com.primeholding.taskmanagement.services.TaskService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;

@Service
public class DepartmentImpl implements DepartmentService {

    private final TaskService taskService;


    public DepartmentImpl(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public List<DepartmentDTO> getDepartmentTaskCount() {
        List<Task> tasks = this.taskService.getTasksFinishedPastMonth();

        Map<Department, Long> tasksCountByDepartment =
                tasks.stream().collect(Collectors.groupingBy( task -> task.getAssignee().getDepartment(), counting()));

        return tasksCountByDepartment.entrySet()
                .stream()
                .map(entry -> new DepartmentDTO (
                entry.getKey().getName(),
                entry.getValue().intValue()))
                .sorted(Comparator.comparing(DepartmentDTO::getNumberOfTasksCompletedPastMonth).reversed())
                .toList();

    }
}
