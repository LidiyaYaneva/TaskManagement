package com.primeholding.taskmanagement.services.impl;


import com.primeholding.taskmanagement.models.dtos.ClientDTO;
import com.primeholding.taskmanagement.models.entities.ClientEntity;
import com.primeholding.taskmanagement.repositories.ClientRepository;
import com.primeholding.taskmanagement.services.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ClientDTO> getClientsNames() {
        List<ClientEntity> clients = this.clientRepository.findAll();
        return Arrays.stream(this.modelMapper.map(clients, ClientDTO[].class)).toList();
    }
}
