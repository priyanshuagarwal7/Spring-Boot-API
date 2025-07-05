package com.manage.employees.factory;

import com.manage.employees.models.Employee;
import com.manage.employees.models.dto.EmployeeCreateRequestDTO;
import com.manage.employees.models.dto.EmployeeResponseDTO;
import com.manage.employees.models.dto.EmployeeUpdateRequestDTO;

import java.util.Date;

public class EmployeeFactory {

    public static EmployeeResponseDTO createResponseFromEmployee(Employee employee) {

        return new EmployeeResponseDTO(
                employee.getEmployeeId(),
                employee.getEmployeeName(),
                employee.getEmployeeEmail(),
                employee.getDepartment(),
                employee.getRole()
        );
    }

    public static Employee createEmployeeFromRequest(EmployeeCreateRequestDTO employeeCreateRequest) {
        Employee employee = new Employee();
        employee.setEmployeeName(employeeCreateRequest.getEmployeeName());
        employee.setEmployeeEmail(employeeCreateRequest.getEmployeeEmail());
        employee.setAccountPassword(employeeCreateRequest.getPassword());
        employee.setDepartment(employeeCreateRequest.getDepartment());
        employee.setRole(employeeCreateRequest.getRole());
        employee.setCreatedAt(new Date(System.currentTimeMillis()));
        employee.setUpdatedAt(new Date(System.currentTimeMillis()));

        return employee;
    }

    public static Employee updateEmployee(EmployeeUpdateRequestDTO employeeUpdateRequest, Employee employee) {
        boolean isEmployeeUpdated = false;
        if (employeeUpdateRequest.getDepartment() != null && !employeeUpdateRequest.getDepartment().isBlank()) {
            employee.setDepartment(employeeUpdateRequest.getDepartment());
            isEmployeeUpdated = true;
        }
        if (employeeUpdateRequest.getRole() != null && !employeeUpdateRequest.getRole().isBlank()) {
            employee.setRole(employeeUpdateRequest.getRole());
            isEmployeeUpdated = true;
        }
        if (!employee.getAccountPassword().equals(employeeUpdateRequest.getPassword()) && !employeeUpdateRequest.getPassword().isBlank()) {
            employee.setAccountPassword(employeeUpdateRequest.getPassword());
            isEmployeeUpdated = true;
        }
        if (isEmployeeUpdated) {
            employee.setUpdatedAt(new Date(System.currentTimeMillis()));
        }
        return employee;
    }
}
