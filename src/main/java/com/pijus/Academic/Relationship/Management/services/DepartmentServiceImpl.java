package com.pijus.Academic.Relationship.Management.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pijus.Academic.Relationship.Management.model.Department;
import com.pijus.Academic.Relationship.Management.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	private DepartmentRepository departmentRepository;

	@Autowired
	public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	@Override
	public Department addDepartment(Department department) {
		return departmentRepository.save(department);
	}

	@Override
	public List<Department> getAllDepartments() {
		return departmentRepository.findAll();
	}

	@Override
	public Optional<Department> getDepartmentById(Long id) {
		return departmentRepository.findById(id);
	}

	@Override
	public Map<String, Boolean> deleteDepartmentById(Long id) {
		Department department = departmentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Department not found with this id: " + id));

		departmentRepository.delete(department);
		Map<String, Boolean> res = new HashMap<>();
		res.put("deleted", true);
		return res;
	}

	@Override
	public Department updateDepartment(Long id, Department department) {
		Department dept = departmentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Department not found with this id: " + id));
		
		dept.setName(department.getName());
		Department updatedDepartment=departmentRepository.save(dept);
		return updatedDepartment;
		
	}

}
