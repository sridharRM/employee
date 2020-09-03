package com.hartford.employee.service;

import java.util.List;

import com.hartford.employee.model.Employee;

public interface EmployeeService {

	public List<Employee> getEmployees(String department);

	public void addEmployee(Employee employee);

	public void updateEmployee(Integer empId, String Salary);

}
