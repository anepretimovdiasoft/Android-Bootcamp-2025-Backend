package com.example.edu.service.impl;

import com.example.edu.dto.department.DepartmentDTO;
import com.example.edu.dto.department.DepartmentUpdateDTO;
import com.example.edu.entity.Department;
import com.example.edu.entity.Place;
import com.example.edu.exception.NotFoundException;
import com.example.edu.repository.DepartmentRepository;
import com.example.edu.repository.PlaceRepository;
import com.example.edu.service.DepartmentService;
import com.example.edu.utils.mappers.DepartmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final PlaceRepository placeRepository;

    @Override
    public DepartmentDTO createDepartment(DepartmentDTO dto) {
        // TODO: Name should be unique?

        Optional<Place> place = placeRepository.findById(dto.getPlace().getId());
        if (place.isEmpty()) place = placeRepository.findById(1L);
        if (place.isEmpty()) throw new NotFoundException("Unable to create department: bad place specified");

        Department department = new Department();
        department.setName(dto.getName());
        department.setPlace(place.get());

        return DepartmentMapper.convertToDTO(departmentRepository.save(department));
    }

    @Override
    public DepartmentDTO getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .map(DepartmentMapper::convertToDTO)
                .orElseThrow(() -> new NotFoundException("Department with id " + id + "was not found"));
    }

    @Override
    public DepartmentDTO getDepartmentByName(String name) {
        Optional<Department> departmentOptional = departmentRepository.findByName(name);

        if (departmentOptional.isEmpty()) throw new NotFoundException("Department with name " + name + " was not found");

        return DepartmentMapper.convertToDTO(departmentOptional.get());
    }

    @Override
    public DepartmentDTO updateDepartment(Long id, DepartmentUpdateDTO dto) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new NotFoundException("Department with id " + id + "was not found"));

        // TODO: Name should be unique?
        department.setName(dto.getName());

        if (dto.getPlaceId() != null) {
             Optional<Place> place = placeRepository.findById(dto.getPlaceId());
             if (place.isEmpty()) throw new NotFoundException("Unable to update department: unknown place id " + dto.getPlaceId());
             department.setPlace(place.get());
        }

        return DepartmentMapper.convertToDTO(departmentRepository.save(department));
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(DepartmentMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<DepartmentDTO> getAllDepartmentsPaginated(Pageable pageable) {
        return departmentRepository.findAll(pageable).map(DepartmentMapper::convertToDTO);
    }
}
