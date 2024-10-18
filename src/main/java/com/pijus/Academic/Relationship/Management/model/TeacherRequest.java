package com.pijus.Academic.Relationship.Management.model;

public class TeacherRequest {
	private String name;
	private Long deptId;

	public TeacherRequest(String name, Long deptId) {
		super();
		this.name = name;
		this.deptId = deptId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

}
