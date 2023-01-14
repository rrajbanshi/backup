package com.examples.empapp.repository;

import com.examples.empapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    public List<Employee> findByDepartment(String dept);

    public List<Employee> findByDepartmentAndCountry(String dept, String country);

}
