package com.pijus.Academic.Relationship.Management.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pijus.Academic.Relationship.Management.model.Department;
import com.pijus.Academic.Relationship.Management.services.DepartmentService;

@RestController
@RequestMapping("/academic")
public class DepartmentController {

	private DepartmentService departmentService;

	@Autowired
	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	
	@PostMapping("/departments")
	public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
		try {
			Department newDep = this.departmentService.addDepartment(department);
			return ResponseEntity.ok(newDep);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	
	@GetMapping("/departments")
	public ResponseEntity<List<Department>> getAllDepartments() {

		try {
			List<Department> list = this.departmentService.getAllDepartments();
			return ResponseEntity.ok(list);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@GetMapping("/departments/{id}")
	public ResponseEntity<Optional<Department>> getDepartmentById(@PathVariable("id") Long id){
		Optional<Department> department=this.departmentService.getDepartmentById(id);
		return ResponseEntity.ok(department);
	}
	
	
	@PutMapping("/departments/{id}")
	public ResponseEntity<Department> updateDepartment(@PathVariable("id") Long id,@RequestBody Department department){
		Department updatedDept=this.departmentService.updateDepartment(id, department);
		return ResponseEntity.ok(updatedDept);
	}

	
	@DeleteMapping("/departments/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteDepartmentById(@PathVariable("id") Long id) {

		try {
			Map<String, Boolean> res = this.departmentService.deleteDepartmentById(id);
			return ResponseEntity.ok(res);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
}
