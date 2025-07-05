package com.manage.employees.controller;

import com.manage.employees.models.dto.EmployeeCreateRequestDTO;
import com.manage.employees.models.dto.EmployeeResponseDTO;
import com.manage.employees.models.dto.EmployeeUpdateRequestDTO;
import com.manage.employees.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    ResponseEntity<EmployeeResponseDTO> createEmployee(@Valid @RequestBody EmployeeCreateRequestDTO employeeCreateRequest) {
        EmployeeResponseDTO employeeResponse = employeeService.createEmployee(employeeCreateRequest);
        return ResponseEntity.ok(employeeResponse);
    }

    @GetMapping
    ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
        List<EmployeeResponseDTO> allEmployees = employeeService.getAllEmployees();
        return ResponseEntity.ok(allEmployees);
    }

    @GetMapping("/id/{employeeId}")
    ResponseEntity<EmployeeResponseDTO> getEmployeeWithId(@PathVariable Long employeeId) {
        EmployeeResponseDTO employee = employeeService.getEmployeeWithId(employeeId);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/page")
    ResponseEntity<List<EmployeeResponseDTO>> getEmployeesOfPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "employeeId") String field,
            @RequestParam(defaultValue = "asc") String sortingDirection
    ) {
        Sort.Direction direction = Sort.Direction.fromString(sortingDirection);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, field));

        List<EmployeeResponseDTO> employeesOfPage = employeeService.getEmployeesOfPage(pageable).stream().toList();
        return ResponseEntity.ok(employeesOfPage);
    }

    @PutMapping("/id/{employeeId}")
    ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable Long employeeId,
                                                       @Valid @RequestBody EmployeeUpdateRequestDTO employeeUpdateRequest) {
        EmployeeResponseDTO employeeResponseDTO = employeeService.updateEmployeeWithId(employeeId, employeeUpdateRequest);
        return ResponseEntity.ok(employeeResponseDTO);
    }
}
