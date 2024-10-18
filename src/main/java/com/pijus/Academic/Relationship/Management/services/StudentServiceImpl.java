package com.pijus.Academic.Relationship.Management.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pijus.Academic.Relationship.Management.model.Department;
import com.pijus.Academic.Relationship.Management.model.Student;
import com.pijus.Academic.Relationship.Management.model.StudentRequest;
import com.pijus.Academic.Relationship.Management.repository.DepartmentRepository;
import com.pijus.Academic.Relationship.Management.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{
	
	private StudentRepository studentRepository;
	
	private DepartmentRepository departmentRepository;
	
	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository, DepartmentRepository departmentRepository) {
		this.studentRepository=studentRepository;
		this.departmentRepository=departmentRepository;
	}

	@Override
	public Student addStudent(StudentRequest studentRequest) {
		Department department=departmentRepository.findById(studentRequest.getDeptId())
				.orElseThrow(() -> new RuntimeException("No department found"));
		
		Student student=new Student();
		student.setName(studentRequest.getName());
		student.setDepartment(department);
		
		return studentRepository.save(student);
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Optional<Student> getStudentById(Long id) {
		return studentRepository.findById(id);
	}

	@Override
	public Student updateStudent(Long id, StudentRequest studentRequest) {
		
		// find the student in the table
		Student student=studentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Student not found with this id: " + id));
		
		// find the department in the department table for the studentRequest deptId
		Department department=departmentRepository.findById(studentRequest.getDeptId())
				.orElseThrow(() -> new RuntimeException("Department not found with this id: " + id));
		
		
		student.setName(studentRequest.getName());
		student.setDepartment(department);
		
		Student updatedStudent=studentRepository.save(student);
		return updatedStudent;
	}

	@Override
	public Map<String, Boolean> deleteStudentById(Long id) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Student not found with this id: " + id));

		studentRepository.delete(student);
		Map<String, Boolean> res = new HashMap<>();
		res.put("deleted", true);
		return res;
	}

}
