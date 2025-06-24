package com.javacafe.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javacafe.ems.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
