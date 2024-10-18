package com.pijus.Academic.Relationship.Management.services;

import java.util.List;
import java.util.Map;

import com.pijus.Academic.Relationship.Management.model.Teacher;
import com.pijus.Academic.Relationship.Management.model.TeacherRequest;

public interface TeacherService {
	public Teacher createTeacher(TeacherRequest teacherRequest);
	
	public Map<String,String> addTeacherToDepartment(Long teacherId,Long departmentId);
	
	public List<Teacher> getAllTeachers();
	
	public Teacher getTeacherById(Long id);
	
	public Map<String,Boolean> deleteTeacherById(Long id);
	
}
