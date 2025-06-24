package com.javacafe.ems.service.impl;

import java.util.List;

import com.javacafe.ems.dto.EmployeeDto;

public interface EmployeeService {
	
	public EmployeeDto createEmployee(EmployeeDto dto);
	
	public List<EmployeeDto> getAllEmployees();
	
	public EmployeeDto getEmployeeById(Long id);
	
	public EmployeeDto updateEmployee(EmployeeDto dto, Long id);

	void deleteEmployee(Long id);

}
