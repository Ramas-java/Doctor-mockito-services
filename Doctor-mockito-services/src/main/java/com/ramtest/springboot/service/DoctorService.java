package com.ramtest.springboot.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ramtest.springboot.model.Doctor_Specialization;
import com.ramtest.springboot.model.Doctor;

@Component
public class DoctorService {

	private static List<Doctor> doctors = new ArrayList<>();

	static {
		//Initialize Data
		Doctor_Specialization specialization1 = new Doctor_Specialization("Specialization1","MBBS General", "Gneral surgen",Arrays.asList("raj","ram","krishna","subash"));
		Doctor_Specialization specialization2 = new Doctor_Specialization("Specialization2", "Daibaties", "Sugar Specialist",
					Arrays.asList("Roy", "James", "Rudra",
							"Rakesh"));
		Doctor_Specialization specialization3 = new Doctor_Specialization("Specialization3" ,"Cardiologist", "Heart specilaist",
					Arrays.asList("suri", "rammi",
							"rankesh", "manesh"));
		Doctor_Specialization specialization4 = new Doctor_Specialization("Specialization4", "Neuro Surgen",
				"Neuro Specialist", Arrays.asList(
							"omkar", "Rakesh", "Arun"
			));

		Doctor rama = new Doctor("Doctor1", "Ramakrishna goud",
				"MBBS FRCS General", new ArrayList<>(Arrays
						.asList(specialization1,specialization2,specialization3,specialization4)));

		Doctor suresh = new Doctor("Doctor2", "Suesh",
				"Hiker, Programmer and Architect", new ArrayList<>(Arrays
						.asList(specialization1,specialization2,specialization3,specialization4)));

		doctors.add(rama);
		doctors.add(suresh);
	}

	public List<Doctor> retrieveAllDoctors() {
		return doctors;
	}

	public Doctor retrieveDoctor(String doctorId) {
		for (Doctor doctor : doctors) {
			if (doctor.getId().equals(doctorId)) {
				return doctor;
			}
		}
		return null;
	}

	public List<Doctor_Specialization> retrieveSpecialization(String doctorId) {
		Doctor doctor = retrieveDoctor(doctorId);

		if (doctor == null) {
			return null;
		}

		return doctor.getCourses();
	}

	public Doctor_Specialization retrieveCourse(String doctorId, String specializationId) {
		Doctor doctor = retrieveDoctor(doctorId);

		if (doctor == null) {
			return null;
		}

		for (Doctor_Specialization course : doctor.getCourses()) {
			if (course.getId().equals(specializationId)) {
				return course;
			}
		}

		return null;
	}

	private SecureRandom random = new SecureRandom();

	public Doctor_Specialization addSpecialization(String doctorId, Doctor_Specialization course) {
		Doctor doctor = retrieveDoctor(doctorId);

		if (doctor == null) {
			return null;
		}

		String randomId = new BigInteger(130, random).toString(32);
		course.setId(randomId);

		doctor.getCourses().add(course);

		return course;
	}

	
	

	
	
}