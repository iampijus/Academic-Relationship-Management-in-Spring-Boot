package com.pijus.Academic.Relationship.Management.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@ManyToMany(mappedBy = "teachers")
	@JsonIgnoreProperties("teachers")
	private Set<Department> departments = new HashSet<>();
	
	public Teacher() {

	}

	public Teacher(Long id, String name, Set<Department> departments) {
		super();
		this.id = id;
		this.name = name;
		this.departments = departments;
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

	public Set<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}
	
	// add or remove department from the "departments" set
	public void addDepartment(Department department) {
		departments.add(department);
	}
	
	public void removeDepartment(Department department) {
		departments.remove(department);
	}

}
