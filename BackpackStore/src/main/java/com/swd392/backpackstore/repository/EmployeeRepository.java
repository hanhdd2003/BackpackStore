package com.swd392.backpackstore.repository;

import com.swd392.backpackstore.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
