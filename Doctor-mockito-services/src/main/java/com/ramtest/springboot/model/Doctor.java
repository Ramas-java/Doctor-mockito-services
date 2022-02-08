package com.ramtest.springboot.model;

import java.util.List;

public class Doctor {
	private String id;
	private String name;
	private String description;
	private List<Doctor_Specialization> specializations;

	public Doctor(String id, String name, String description,
			List<Doctor_Specialization> courses) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.specializations = courses;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Doctor_Specialization> getCourses() {
		return specializations;
	}

	public void setCourses(List<Doctor_Specialization> specializations) {
		this.specializations = specializations;
	}

	@Override
	public String toString() {
		return String.format(
				"Student [id=%s, name=%s, description=%s, courses=%s]", id,
				name, description, specializations);
	}
}