package com.swd392.backpackstore.service;

import com.swd392.backpackstore.model.Employee;
import com.swd392.backpackstore.model.dto.EmployeeDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IEmployeeService {

    List<Employee> getAllEmployees();

    Optional<Employee> getEmployeeById(int id);

    Employee addEmployee(Employee employee);

    Optional<Employee> updateEmployee(int id, Employee employee);

    void deleteEmployee(int id);
}
