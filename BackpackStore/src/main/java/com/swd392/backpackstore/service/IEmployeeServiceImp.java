package com.swd392.backpackstore.service;

import com.swd392.backpackstore.model.Employee;
import com.swd392.backpackstore.model.dto.EmployeeDTO;
import com.swd392.backpackstore.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class IEmployeeServiceImp implements IEmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> updateEmployee(int id, Employee employee) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        return employeeOptional.map(emp ->{
            emp.setFirstName(employee.getFirstName());
            emp.setLastName(employee.getLastName());
            emp.setEmail(employee.getEmail());
            emp.setAddress(employee.getAddress());
            emp.setPhone(employee.getPhone());
            emp.setGender(employee.isGender());
            return employeeRepository.save(emp);
        });
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
}
