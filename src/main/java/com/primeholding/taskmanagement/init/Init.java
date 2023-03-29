package com.primeholding.taskmanagement.init;

import com.primeholding.taskmanagement.models.entities.ClientEntity;
import com.primeholding.taskmanagement.models.entities.Department;
import com.primeholding.taskmanagement.repositories.ClientRepository;
import com.primeholding.taskmanagement.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class Init implements CommandLineRunner {

    private final ClientRepository clientRepository;

    private final DepartmentRepository departmentRepository;

    @Autowired
    public Init(ClientRepository clientRepository, DepartmentRepository departmentRepository) {
        this.clientRepository = clientRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void run(String... args) {
        if (this.clientRepository.count() == 0) {
            List<ClientEntity> clients = List.of(
                    new ClientEntity("Example Corporation Ltd"),
                    new ClientEntity("Production Company Ltd"),
                    new ClientEntity("Bulgaria Travel Ltd")
            );
            this.clientRepository.saveAll(clients);
        }

        if (this.departmentRepository.count() == 0) {
            List<Department> clients = List.of(
                    new Department("IT"),
                    new Department("Business"),
                    new Department("Marketing")
            );
            this.departmentRepository.saveAll(clients);
        }
    }
}
