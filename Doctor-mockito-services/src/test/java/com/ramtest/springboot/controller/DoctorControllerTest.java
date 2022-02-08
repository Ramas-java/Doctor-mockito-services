package com.ramtest.springboot.controller;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ramtest.springboot.controller.DoctorController;
import com.ramtest.springboot.model.Doctor_Specialization;
import com.ramtest.springboot.service.DoctorService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DoctorController.class)
@WithMockUser
public class DoctorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DoctorService studentService;

	Doctor_Specialization mockCourse = new Doctor_Specialization("specialization1", "MBBS General", "Gneral surgen",Arrays.asList("raj","ram","krishna","subash"));

	String exampleCourseJson = "{\"specname\":\"MBBS General\",\"description\":\"Gneral surgen\"}";

	@Test
	public void retrieveDetailsForCourse() throws Exception {

		Mockito.when(
				studentService.retrieveCourse(Mockito.anyString(),
						Mockito.anyString())).thenReturn(mockCourse);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/doctors/Doctor1/specializations/Specialization1").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{id:specialization1,specname:MBBS General,description:Gneral surgen}";

		// {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}

		//JSONAssert.assertEquals(expected, result.getResponse()
			//	.getContentAsString(), false);
	}

	@Test
	public void createStudentCourse() throws Exception {
		Doctor_Specialization mockCourse = new Doctor_Specialization("1", "Smallest Number", "1",
				Arrays.asList("1","2","3","4"));

		// studentService.addCourse to respond back with mockCourse
		Mockito.when(
				studentService.addSpecialization(Mockito.anyString(),
						Mockito.any(Doctor_Specialization.class))).thenReturn(mockCourse);

		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/doctors/Doctor1/specializations")
				.accept(MediaType.APPLICATION_JSON).content(exampleCourseJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		assertEquals("http://localhost/doctors/Doctor1/specializations/1",
				response.getHeader(HttpHeaders.LOCATION));

	}

}
