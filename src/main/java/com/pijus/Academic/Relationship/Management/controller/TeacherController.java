package com.pijus.Academic.Relationship.Management.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pijus.Academic.Relationship.Management.model.Teacher;
import com.pijus.Academic.Relationship.Management.model.TeacherRequest;
import com.pijus.Academic.Relationship.Management.services.TeacherService;

@RestController
@RequestMapping("/academic")
public class TeacherController {

	private TeacherService teacherService;

	@Autowired
	public TeacherController(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	// create a teacher and assign them to a department
	@PostMapping("/teachers")
	public ResponseEntity<Teacher> createTeacher(@RequestBody TeacherRequest teacherRequest) {
		Teacher savedTeacher = this.teacherService.createTeacher(teacherRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedTeacher);
	}

	// method to assign an existing teacher to another department
	@PostMapping("/teachers/{teacherId}/departments/{departmentId}")
	public ResponseEntity<Map<String, String>> assignTeacherToDepartment(@PathVariable Long teacherId,
			@PathVariable Long departmentId) {
		Map<String, String> res = this.teacherService.addTeacherToDepartment(teacherId, departmentId);
		return ResponseEntity.ok(res);
	}
	
	// get all teachers
	@GetMapping("/teachers")
	public ResponseEntity<List<Teacher>> getAllTeachers(){
		List<Teacher> list=this.teacherService.getAllTeachers();
		return ResponseEntity.ok(list);
	}
	
	
	// get teacher by id
	@GetMapping("/teachers/{id}")
	public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id){
		Teacher teacher= this.teacherService.getTeacherById(id);
		return ResponseEntity.ok(teacher);
	}
	
	
	// delete teacher by id
	@DeleteMapping("/teachers/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteTeacherById(@PathVariable Long id){
		Map<String,Boolean> res=this.teacherService.deleteTeacherById(id);
		return ResponseEntity.ok(res);
	}
	
	

}
