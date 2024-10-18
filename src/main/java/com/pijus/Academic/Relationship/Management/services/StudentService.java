package com.pijus.Academic.Relationship.Management.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.pijus.Academic.Relationship.Management.model.Student;
import com.pijus.Academic.Relationship.Management.model.StudentRequest;

public interface StudentService {
	
	public Student addStudent(StudentRequest studentRequest);

	public List<Student> getAllStudents();
	
	public Optional<Student> getStudentById(Long id);
	
	public Student updateStudent(Long id,StudentRequest studentRequest);
	
	public Map<String,Boolean> deleteStudentById(Long id);
}
