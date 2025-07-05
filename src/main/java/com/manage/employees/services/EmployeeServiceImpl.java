package com.manage.employees.services;

import com.manage.employees.factory.EmployeeFactory;
import com.manage.employees.models.Employee;
import com.manage.employees.models.dto.EmployeeCreateRequestDTO;
import com.manage.employees.models.dto.EmployeeResponseDTO;
import com.manage.employees.models.dto.EmployeeUpdateRequestDTO;
import com.manage.employees.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeCreateRequestDTO employeeCreateRequest) {
        Employee employee = EmployeeFactory.createEmployeeFromRequest(employeeCreateRequest);
        employee = employeeRepository.save(employee);
        return EmployeeFactory.createResponseFromEmployee(employee);
    }

    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(EmployeeFactory::createResponseFromEmployee).collect(Collectors.toList());
    }

    @Override
    public EmployeeResponseDTO getEmployeeWithId(Long employeeId) {
        Employee employee = employeeRepository.getReferenceById(employeeId);
        return EmployeeFactory.createResponseFromEmployee(employee);

    }

    @Override
    public Page<EmployeeResponseDTO> getEmployeesOfPage(Pageable pageable) {
        return employeeRepository.findAll(pageable).map(EmployeeFactory::createResponseFromEmployee);
    }

    @Override
    public EmployeeResponseDTO updateEmployeeWithId(Long employeeId, EmployeeUpdateRequestDTO employeeUpdateRequest) {
        boolean employeeExists = employeeRepository.existsById(employeeId);
        if (employeeExists) {
            Employee employee = employeeRepository.getReferenceById(employeeId);
            employee = EmployeeFactory.updateEmployee(employeeUpdateRequest, employee);
            employee = employeeRepository.save(employee);
            return EmployeeFactory.createResponseFromEmployee(employee);
        }
        return null;
    }
}
