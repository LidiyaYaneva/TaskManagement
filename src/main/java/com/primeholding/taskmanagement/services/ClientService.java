package com.primeholding.taskmanagement.services;

import com.primeholding.taskmanagement.models.dtos.ClientDTO;

import java.util.List;

public interface ClientService {
    List<ClientDTO> getClientsNames();
}
