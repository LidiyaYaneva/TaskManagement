package com.primeholding.taskmanagement.services;

import com.primeholding.taskmanagement.models.dtos.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDTO> getDepartmentTaskCount();
}
