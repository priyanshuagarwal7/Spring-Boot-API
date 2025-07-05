package com.manage.employees.models.dto;

import com.manage.employees.utils.annotations.Password;

public class EmployeeUpdateRequestDTO {

    @Password(message = "Password is invalid !")
    private String password;
    private String department;
    private String role;

    public EmployeeUpdateRequestDTO(String password, String department, String role) {
        this.password = password;
        this.department = department;
        this.role = role;
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
