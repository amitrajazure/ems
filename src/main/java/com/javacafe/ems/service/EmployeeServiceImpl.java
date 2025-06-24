package com.javacafe.ems.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.javacafe.ems.dto.EmployeeDto;
import com.javacafe.ems.entity.Employee;
import com.javacafe.ems.exception.ResourceNotFoundException;
import com.javacafe.ems.mapper.EmployeeMapper;
import com.javacafe.ems.repository.EmployeeRepository;
import com.javacafe.ems.service.impl.EmployeeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	EmployeeRepository repository;
	@Override
	public EmployeeDto createEmployee(EmployeeDto dto) {
		Employee employee =  EmployeeMapper.mapToEmployee(dto);
		return EmployeeMapper.mapToEmployeeDto(repository.save(employee));
	}
	@Override
	public List<EmployeeDto> getAllEmployees() {
		return repository.findAll().stream().map(EmployeeMapper::mapToEmployeeDto)
				.collect(Collectors.toList());
	}
	@Override
	public EmployeeDto getEmployeeById(Long id) {
		return EmployeeMapper.mapToEmployeeDto(repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource with the given id "
					+ id + " not found. ")));
		/*
		 * Optional<Employee> emp = repository.findById(id); if(emp.isPresent()) {
		 * return EmployeeMapper.mapToEmployeeDto(emp.get()); } else { return null; }
		 */
	}
	@Override
	public EmployeeDto updateEmployee(EmployeeDto dto, Long id) {
		Employee emp = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource with the given id "
				+ id + " not found. "));
		emp.setFirstName(dto.getFirstName());
        emp.setLastName(dto.getLastName());
        emp.setEmail(dto.getEmail());
		return EmployeeMapper.mapToEmployeeDto(repository
				.save(emp));
	}
	
	@Override
    public void deleteEmployee(Long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Employee do not exists with the given id:" + id));
        repository.deleteById(id);
    }

}
