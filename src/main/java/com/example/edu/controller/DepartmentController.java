package com.example.edu.controller;

import com.example.edu.dto.department.DepartmentDTO;
import com.example.edu.dto.department.DepartmentUpdateDTO;
import com.example.edu.dto.person.PersonDTO;
import com.example.edu.service.DepartmentService;
import com.example.edu.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;
    private final PersonService personService;  // TODO: Can i use it here?

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable Long id, @RequestBody DepartmentUpdateDTO dto) {
        return ResponseEntity.ok(departmentService.updateDepartment(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<DepartmentDTO> getDepartmentByName(@PathVariable String name) {
        return ResponseEntity.ok(departmentService.getDepartmentByName(name));
    }

    @GetMapping("{name}/persons/paginated")
    public ResponseEntity<Page<PersonDTO>> getDepartmentPersonsPaginated(
            @PathVariable String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        departmentService.getDepartmentByName(name);  // Only to check if department exists
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(personService.getByDepartmentNamePaginated(name, pageable));
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<DepartmentDTO>> getAllDepartmentsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(departmentService.getAllDepartmentsPaginated(pageable));
    }
}
