package com.primeholding.taskmanagement.init;

import com.primeholding.taskmanagement.models.entities.ClientEntity;
import com.primeholding.taskmanagement.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class Init implements CommandLineRunner {

    private final ClientRepository clientRepository;

    @Autowired
    public Init(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
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
    }
}
