package com.primeholding.taskmanagement.repositories;

import com.primeholding.taskmanagement.models.entities.Employee;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);

    List<Employee> findAllByOrderById(PageRequest pageRequest);

    List<Employee> findAllByOrderById();

    List<Employee> findAllByFullNameContaining(String inputName);

//    SELECT e.`full_name`, COUNT(t.id) FROM tasks AS t
//    JOIN employees AS e ON e.id= t.assignee_id
//    WHERE t.status='COMPLETED' AND t.date_completed BETWEEN '2023-02-22' AND '2023-03-22'
//    GROUP BY t.assignee_id
//    ORDER BY COUNT(t.id) DESC
//    LIMIT 5;

}

