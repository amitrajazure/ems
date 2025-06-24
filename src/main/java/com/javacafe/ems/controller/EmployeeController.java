package com.javacafe.ems.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javacafe.ems.dto.EmployeeDto;
import com.javacafe.ems.service.impl.EmployeeService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {
	
	private EmployeeService service;
	
	@PostMapping
	//build add employee api
	public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
		EmployeeDto savedDto = service.createEmployee(employeeDto);
		return new ResponseEntity<EmployeeDto>(savedDto, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
		List<EmployeeDto> allEmployees = service.getAllEmployees();
		return ResponseEntity.ok(allEmployees);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable(name ="id") Long id) {
		EmployeeDto dto = service.getEmployeeById(id);
		/*
		 * if(dto!=null) { return new ResponseEntity<EmployeeDto>(dto,HttpStatus.FOUND);
		 * }
		 */
		return ResponseEntity.ok(dto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto dto,
		 @PathVariable("id") Long id) {
		return ResponseEntity.ok(service.updateEmployee(dto, id));
	}
	
    @DeleteMapping("{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable("id") Long employeeId) {
        service.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee Deleted successfully");
    }
	
}
