package com.ramtest.springboot.model;

import java.util.List;

public class Doctor_Specialization {
	private String id;
	private String specname;
	private String description;
	private List<String> patients;

	// Needed by Caused by: com.fasterxml.jackson.databind.JsonMappingException:
	// Can not construct instance of com.in28minutes.springboot.model.Course:
	// no suitable constructor found, can not deserialize from Object value
	// (missing default constructor or creator, or perhaps need to add/enable
	// type information?)
	public Doctor_Specialization() {

	}

	public Doctor_Specialization(String id,String specname, String description,List<String> patients) {
		super();
		this.id = id;
		this.specname = specname;
		this.description = description;
		this.patients = patients;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getSpecname() {
		return specname;
	}

	public String getDescription() {
		return description;
	}

	public List<String> getPatients() {
	return patients;
    }

	@Override
	public String toString() {
		return String.format(
				"Course [id=%s,specname=%s, description=%s, steps=%s,patients=%s]", id, specname,
				description);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor_Specialization other = (Doctor_Specialization) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}