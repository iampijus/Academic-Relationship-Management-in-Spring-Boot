package com.pijus.Academic.Relationship.Management.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.pijus.Academic.Relationship.Management.model.Department;

public interface DepartmentService {

	public Department addDepartment(Department department);

	public List<Department> getAllDepartments();
	
	public Optional<Department> getDepartmentById(Long id);
	
	public Department updateDepartment(Long id,Department department);
	
	public Map<String,Boolean> deleteDepartmentById(Long id);
}
