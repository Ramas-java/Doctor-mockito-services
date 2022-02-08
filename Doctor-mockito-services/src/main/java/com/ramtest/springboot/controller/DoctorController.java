package com.ramtest.springboot.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ramtest.springboot.model.Doctor_Specialization;
import com.ramtest.springboot.service.DoctorService;

@RestController
public class DoctorController {

	@Autowired
	private DoctorService doctorServices;

	@GetMapping("/doctors/{doctorId}/specializations")
	public List<Doctor_Specialization> retrieveCoursesForStudent(@PathVariable String doctorId) {
		return doctorServices.retrieveSpecialization(doctorId);
	}
	
	@GetMapping("/doctors/{doctorId}/specializations/{specializationId}")
	public Doctor_Specialization retrieveDetailsForCourse(@PathVariable String doctorId,
			@PathVariable String specializationId) {
		return doctorServices.retrieveCourse(doctorId, specializationId);
	}
	
	@PostMapping("/doctors/{doctorId}/specializations")
	public ResponseEntity<Void> registerStudentForCourse(
			@PathVariable String doctorId, @RequestBody Doctor_Specialization newCourse) {

		Doctor_Specialization course = doctorServices.addSpecialization(doctorId, newCourse);

		if (course == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{id}").buildAndExpand(course.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

}
