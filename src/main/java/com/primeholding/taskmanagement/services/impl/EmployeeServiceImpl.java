package com.primeholding.taskmanagement.services.impl;

import com.primeholding.taskmanagement.models.dtos.*;
import com.primeholding.taskmanagement.models.entities.Employee;
import com.primeholding.taskmanagement.models.entities.Task;
import com.primeholding.taskmanagement.models.enums.TaskStatus;
import com.primeholding.taskmanagement.repositories.EmployeeRepository;

import com.primeholding.taskmanagement.services.EmployeeService;
import com.primeholding.taskmanagement.services.TaskService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final TaskService taskService;

    private final ModelMapper modelMapper;

    //private final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, TaskService taskService, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.taskService = taskService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveEmployee(CreateEmployeeDTO createEmployeeDTO) {

        Employee employee = this.modelMapper.map(createEmployeeDTO, Employee.class);
        this.employeeRepository.save(employee);
    }

    @Override
    public List<SelectEmployeeDTO> getEmployeesNames() {
        return this.employeeRepository.findAllByOrderById()
                .stream()
                .map(e -> this.modelMapper.map(e, SelectEmployeeDTO.class))
                .toList();
    }

    @Override
    public List<DisplayEmployeeWithStatsDTO> getEmployeesToDisplay() {

        PageRequest pageRequest = PageRequest.of(0, 18, Sort.by(Sort.Direction.ASC, "id"));
        List<Employee> employees = this.employeeRepository.findAllByOrderById(pageRequest);
        DisplayEmployeeWithStatsDTO[] displayEmployeeWithStatsDTOS = this.modelMapper.map(employees, DisplayEmployeeWithStatsDTO[].class);
        for (DisplayEmployeeWithStatsDTO dto : displayEmployeeWithStatsDTOS) {
            dto.setTasksCompletedCount(taskService.getNumberOfTasksByStatus(dto.getId(), TaskStatus.COMPLETED));
            dto.setTasksInProgressCount(taskService.getNumberOfTasksByStatus(dto.getId(), TaskStatus.ACTIVE));
        }
        return Arrays.stream(displayEmployeeWithStatsDTOS).toList();
    }

    @Override
    public List<DisplayEmployeeInfo> searchEmployeesByName(String inputName) {

        List<Employee> employees;

        if (inputName == null) {
            employees = this.employeeRepository.findAll();
        } else {
            employees = this.employeeRepository.findAllByFullNameContaining(inputName.trim());
        }
        return Arrays.stream(this.modelMapper.map(employees, DisplayEmployeeInfo[].class)).toList();
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {

        Optional<Employee> employee = this.employeeRepository.findById(id);
        employee.ifPresent(e -> e.setTasks(null));
        this.taskService.setAssigneeTasksToNull(id);

        this.employeeRepository.delete(employee.orElseThrow());
    }

    @Override
    public List<DisplayProductiveEmployee> getTop5Employees() {

        List<Task> tasks = this.taskService.getTasksFinishedPastMonth();

        Map<Employee, Long> tasksCountByAssignee =
                tasks.stream().collect(Collectors.groupingBy(Task::getAssignee, counting()));

        return tasksCountByAssignee
                .entrySet().stream()
                .map(entry -> new DisplayProductiveEmployee(
                        entry.getKey().getFullName(),
                        entry.getValue().intValue()))
                .sorted(Comparator.comparing(DisplayProductiveEmployee::getNumberOfTasksCompletedPastMonth).reversed())
                .limit(5)
                .toList();


//        Map<Employee, Long> productivityMap = new LinkedHashMap<>();
//        for (Task t: tasks) {
//
//            Employee key = t.getAssignee();
//            productivityMap.putIfAbsent(key, 1L);
//
//            if(productivityMap.containsKey(key)){
//                Long value = productivityMap.get(key)+1L;
//                productivityMap.put(key, value);
//            }
//        }
    }

    @Override
    public Optional<CreateEmployeeDTO> getById(Long id) {
        return this.employeeRepository.findById(id).map(e -> this.modelMapper.map(e, CreateEmployeeDTO.class));
    }

}
