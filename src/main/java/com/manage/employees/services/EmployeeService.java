package com.manage.employees.services;

import com.manage.employees.models.dto.EmployeeCreateRequestDTO;
import com.manage.employees.models.dto.EmployeeResponseDTO;
import com.manage.employees.models.dto.EmployeeUpdateRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    EmployeeResponseDTO createEmployee(EmployeeCreateRequestDTO employeeCreateRequest);

    List<EmployeeResponseDTO> getAllEmployees();

    EmployeeResponseDTO getEmployeeWithId(Long employeeId);

    Page<EmployeeResponseDTO> getEmployeesOfPage(Pageable pageable);

    EmployeeResponseDTO updateEmployeeWithId(Long employeeId, EmployeeUpdateRequestDTO employeeUpdateRequest);
}
