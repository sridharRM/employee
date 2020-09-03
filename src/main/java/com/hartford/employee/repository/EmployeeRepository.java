package com.hartford.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hartford.employee.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

	public List<Employee> findByDepartment(String department);

	@Query("Update Employee e set e.salary=:salary where e.id=:empId")
	@Modifying
	@Transactional
	public void updateEmployeeByIdAndSalary(Integer empId, String salary);

}
