package com.pijus.Academic.Relationship.Management.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	// One-to-many with student
	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
	// @JsonManagedReference
	@JsonIgnoreProperties("department")
	private Set<Student> students = new HashSet<>();

	// Many-to-Many with teacher

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "department_teacher", joinColumns = @JoinColumn(name = "department_id"), inverseJoinColumns = @JoinColumn(name = "teacher_id"))
	@JsonIgnoreProperties("department")
	private Set<Teacher> teachers = new HashSet<>();

	public Department() {

	}

	public Department(Long id, String name, Set<Student> students, Set<Teacher> teachers) {
		super();
		this.id = id;
		this.name = name;
		this.students = students;
		this.teachers = teachers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Set<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}

	// add or remove teacher from the "teachers" set
	public void addTeacher(Teacher teacher) {
		teachers.add(teacher);
	}

	public void removeTeacher(Teacher teacher) {
		teachers.remove(teacher);
	}

}
