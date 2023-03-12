package com.primeholding.taskmanagement.repositories;

import com.primeholding.taskmanagement.models.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

}