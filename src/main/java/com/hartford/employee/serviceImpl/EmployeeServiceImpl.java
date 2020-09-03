package com.hartford.employee.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hartford.employee.model.Employee;
import com.hartford.employee.repository.EmployeeRepository;
import com.hartford.employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getEmployees(String department) {
		return employeeRepository.findByDepartment(department);
	}

	@Override
	public void addEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	@Override
	public void updateEmployee(Integer empId, String salary) {
		employeeRepository.updateEmployeeByIdAndSalary(empId, salary);

	}

}
