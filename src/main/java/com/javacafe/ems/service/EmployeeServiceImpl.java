package com.javacafe.ems.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
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
	
	KafkaTemplate<String, String> kafkaTemplate;
	
	@KafkaListener(topics = "employee-events", groupId = "$Default")
	public void consumeEventHub(String message) {
		System.out.println("Message consumed: " +message);
	}
	
	@Override
	public EmployeeDto createEmployee(EmployeeDto dto) {
		Employee employee = EmployeeMapper.mapToEmployee(dto);
		Employee savedEmployee = repository.save(employee);
		kafkaTemplate.send("employee-events", savedEmployee.getId().toString(), savedEmployee.toString());
		return EmployeeMapper.mapToEmployeeDto(savedEmployee);
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
