package com.swd392.backpackstore.controller;

import com.swd392.backpackstore.model.Employee;
import com.swd392.backpackstore.model.dto.EmployeeDTO;
import com.swd392.backpackstore.service.IEmployeeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeServiceImp employeeServiceImp;

    @GetMapping("/getAllEmployee")
    public ResponseEntity<?> getAllEmployee() {
        List<Employee> employees = employeeServiceImp.getAllEmployees();
        return ResponseEntity.ok(employees);
    }


    @GetMapping("/getEmployeeDetailById/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id) {
        Optional<Employee> employee = employeeServiceImp.getEmployeeById(id);
        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/saveEmployee")
    public ResponseEntity<Employee> addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setAddress(employeeDTO.getAddress());
        employee.setPhone(employeeDTO.getPhone());
        employee.setGender(employeeDTO.isGender());
        return ResponseEntity.ok(employeeServiceImp.addEmployee(employee));

    }
    @PutMapping("/editEmployee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") int id, @RequestBody Employee  employee) {
        Optional<Employee> existingEmployee = employeeServiceImp.getEmployeeById(id);
//        System.out.println(id+" "+ employee.getAddress());
        if (existingEmployee.isPresent()) {
            return ResponseEntity.ok(employeeServiceImp.updateEmployee(id, employee).get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("deleteEmployeeById/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") int id) {
        Optional<Employee> existingEmployee = employeeServiceImp.getEmployeeById(id);

        if (existingEmployee.isPresent()) {
            employeeServiceImp.deleteEmployee(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}
