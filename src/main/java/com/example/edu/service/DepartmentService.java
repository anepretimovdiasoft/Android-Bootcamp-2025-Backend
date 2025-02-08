package com.example.edu.service;

import com.example.edu.dto.department.DepartmentDTO;
import com.example.edu.dto.department.DepartmentUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartmentService {

    DepartmentDTO createDepartment(DepartmentDTO dto);

    DepartmentDTO getDepartmentById(Long id);

    DepartmentDTO getDepartmentByName(String name);

    DepartmentDTO updateDepartment(Long id, DepartmentUpdateDTO dto);

    void deleteDepartment(Long id);

    List<DepartmentDTO> getAllDepartments();

    Page<DepartmentDTO> getAllDepartmentsPaginated(Pageable pageable);
}
