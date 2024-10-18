package com.pijus.Academic.Relationship.Management.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pijus.Academic.Relationship.Management.model.Department;
import com.pijus.Academic.Relationship.Management.model.Teacher;
import com.pijus.Academic.Relationship.Management.model.TeacherRequest;
import com.pijus.Academic.Relationship.Management.repository.DepartmentRepository;
import com.pijus.Academic.Relationship.Management.repository.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService {

	private TeacherRepository teacherRepository;

	private DepartmentRepository departmentRepository;

	@Autowired
	public TeacherServiceImpl(TeacherRepository teacherRepository, DepartmentRepository departmentRepository) {
		this.teacherRepository = teacherRepository;
		this.departmentRepository = departmentRepository;
	}

	@Override
	public Teacher createTeacher(TeacherRequest teacherRequest) {
		Department department = departmentRepository.findById(teacherRequest.getDeptId())
				.orElseThrow(() -> new RuntimeException("No department found"));

		Teacher teacher = new Teacher();
		teacher.setName(teacherRequest.getName());

		// add the teacher to the department
		department.addTeacher(teacher);

		// add department to the teacher
		teacher.addDepartment(department);

		// Since the CascadeType.ALL is set on the many-to-many relationship,
		// saving the teacher will automatically save the department of the associated
		// teacher

		return teacherRepository.save(teacher);

	}

	@Override
	public Map<String, String> addTeacherToDepartment(Long teacherId, Long departmentId) {
		// find teacher by id
		Optional<Teacher> teacherOpt = teacherRepository.findById(teacherId);

		// find department by id
		Optional<Department> departmentOpt = departmentRepository.findById(departmentId);

		if (teacherOpt.isPresent() && departmentOpt.isPresent()) {
			Teacher teacher = teacherOpt.get();
			Department department = departmentOpt.get();

			department.getTeachers().add(teacher);
			teacher.getDepartments().add(department);

			teacherRepository.save(teacher);

			Map<String, String> res = new HashMap<>();
			res.put("status", "success");
			return res;
		}
		return null;

	}

	@Override
	public List<Teacher> getAllTeachers() {
		return teacherRepository.findAll();
	}

	@Override
	public Teacher getTeacherById(Long id) {
		Optional<Teacher> teacherOpt=teacherRepository.findById(id);
		
		if(teacherOpt.isPresent()) {
			Teacher teacher=teacherOpt.get();
			return teacher;
		}
		return null;
	}

	@Override
	public Map<String, Boolean> deleteTeacherById(Long id) {
		Teacher teacher=teacherRepository.findById(id)
				.orElseThrow(()->new RuntimeException("Teacher not found with this id: "+id));
		
		// Remove the teacher from all departments
		for(Department dept : teacher.getDepartments()) {
			dept.removeTeacher(teacher);
		}
		
		// Save the departments after removal of the teacher
		for(Department dept: teacher.getDepartments()) {
			departmentRepository.save(dept);
		}
		
		
		teacherRepository.delete(teacher);
		Map<String,Boolean> res=new HashMap<>();
		res.put("deleted", true);
		return res;
	}

}
