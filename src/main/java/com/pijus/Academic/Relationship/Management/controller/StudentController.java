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

import com.pijus.Academic.Relationship.Management.model.Student;
import com.pijus.Academic.Relationship.Management.model.StudentRequest;
import com.pijus.Academic.Relationship.Management.services.StudentService;

@RestController
@RequestMapping("/academic")
public class StudentController {

	private StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@PostMapping("/students")
	public ResponseEntity<Student> createStudent(@RequestBody StudentRequest studentRequest) {

		Student savedStudent = this.studentService.addStudent(studentRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);

	}

	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudents() {
		List<Student> list = this.studentService.getAllStudents();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/students/{id}")
	public ResponseEntity<Optional<Student>> getStudentById(@PathVariable("id") Long id) {
		Optional<Student> student = this.studentService.getStudentById(id);
		return ResponseEntity.ok(student);
	}

	@PutMapping("/students/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id,
			@RequestBody StudentRequest studentRequest) {
		Student updateStu = this.studentService.updateStudent(id, studentRequest);
		return ResponseEntity.ok(updateStu);
	}

	@DeleteMapping("/students/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteStudentById(@PathVariable("id") Long id) {
		Map<String, Boolean> res = this.studentService.deleteStudentById(id);
		return ResponseEntity.ok(res);
	}

}
