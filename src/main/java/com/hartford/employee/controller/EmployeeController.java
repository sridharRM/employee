package com.hartford.employee.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hartford.employee.exception.EmployeeNotFoundException;
import com.hartford.employee.model.Employee;
import com.hartford.employee.repository.EmployeeRepository;
import com.hartford.employee.service.EmployeeService;

@RestController
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("/employee/{department}")
	public ResponseEntity<List<Employee>> getEmployees(@PathVariable("department") String department) {
		logger.info("EmployeeController::getEmployeeDetails");
		return new ResponseEntity<>(employeeService.getEmployees(department), HttpStatus.OK);
	}

	@PostMapping("/addEmployee")
	public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
		logger.info("EmployeeController::addEmployee");
		employeeService.addEmployee(employee);
		return new ResponseEntity<>("employee is added", HttpStatus.OK);
	}

	@PutMapping("/employee")
	public ResponseEntity<String> updateEmployeeByIdAndSalary(@RequestParam("empId") Integer empId,
			@RequestParam("salary") String salary) {
		logger.info("EmployeeController::updateEmployeeByIdAndSalary");

		Optional<Employee> employee = employeeRepository.findById(empId);
		if (employee.isPresent()) {
			employeeService.updateEmployee(empId, salary);
			return new ResponseEntity<>("employee salary is updated", HttpStatus.OK);
		} else {
			throw new EmployeeNotFoundException();
		}
	}

	@ExceptionHandler(value = EmployeeNotFoundException.class)
	public ResponseEntity<Object> exception(EmployeeNotFoundException exception) {
		return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
	}

}
