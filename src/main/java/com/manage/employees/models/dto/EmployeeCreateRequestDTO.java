package com.manage.employees.models.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EmployeeCreateRequestDTO {

    @NotBlank(message = "Employee name can not be blank")
    private String employeeName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email field can not be blank")
    private String employeeEmail;

    @Size(min = 8, message = "Password length should be minimum of 8 characters")
    private String password;

    @NotBlank(message = "Department can not be Blank")
    private String department;

    @NotBlank(message = "Role can not be blank")
    private String role;

    public EmployeeCreateRequestDTO(String employeeName, String employeeEmail, String password, String department, String role) {
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
        this.password = password;
        this.department = department;
        this.role = role;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
