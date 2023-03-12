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

}

