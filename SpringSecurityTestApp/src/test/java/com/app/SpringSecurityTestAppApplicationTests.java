package com.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.exception.PatientException;
import com.app.model.Authority;
import com.app.model.Patient;
import com.app.repository.PatientRepository;
import com.app.service.PatientService;
import static org.mockito.ArgumentMatchers.anyInt;

@SpringBootTest
@RunWith(SpringRunner.class)
class SpringSecurityTestAppApplicationTests {

	@Autowired
	private PatientService patientService;
	
	@MockBean
	private PatientRepository patientRepository;
	
	@Test
	public void savePatientWithExceptionTest() {
	
		Patient patient = new Patient();
		patient.setId(100);
		patient.setName("Demo");
		patient.setEmail("demo@gmail.com");
		patient.setPassword("demoPassword");
		patient.setContactNo("123456789");
		patient.setAddress("DemoAddress");
		patient.setPincode("700098");
		patient.getAuthorities().add(new Authority(111, "ROLE_USER", patient));
		
		when(patientRepository.save(patient)).thenReturn(null);
		assertThrows(PatientException.class,
				() ->{
					patientService.registerPatient(patient);
				});
	}
	@Test
	public void savePatientTest() throws PatientException {
		
		Patient patient = new Patient();
		patient.setId(100);
		patient.setName("Demo");
		patient.setEmail("demo@gmail.com");
		patient.setPassword("demoPassword");
		patient.setContactNo("123456789");
		patient.setAddress("DemoAddress");
		patient.setPincode("700098");
		patient.getAuthorities().add(new Authority(111, "ROLE_USER", patient));
		
		when(patientRepository.save(patient)).thenReturn(patient);
		assertEquals(patient, patientService.registerPatient(patient));
	}
	
	@Test
	public void gerAllPatientsExceptionTest() {
		
		when(patientRepository.findAll()).thenReturn(Collections.emptyList());
		assertThrows(PatientException.class,
				() ->{
					patientService.getAllPatients();
				});
	}
	
	@Test
	public void getAllPatientsTest() throws PatientException {
		
		Patient patient = new Patient();
		patient.setId(100);
		patient.setName("Demo");
		patient.setEmail("demo@gmail.com");
		patient.setPassword("demoPassword");
		patient.setContactNo("123456789");
		patient.setAddress("DemoAddress");
		patient.setPincode("700098");
		patient.getAuthorities().add(new Authority(111, "ROLE_USER", patient));
		
		when(patientRepository.findAll()).thenReturn(Stream
				.of(patient).collect(Collectors.toList()));
		assertEquals(1, patientService.getAllPatients().size());
	}
	
	@Test
	public void getpatientByIdExceptionTest() {
		
		when(patientRepository.findById(anyInt())).thenReturn(null);
		assertThrows(PatientException.class,
				()->{
					patientService.getpatientById(anyInt());
				});
	}
	
	@Test
	public void getPatientByIdTest() throws PatientException {
		
		Patient patient = new Patient();
		patient.setId(100);
		patient.setName("Demo");
		patient.setEmail("demo@gmail.com");
		patient.setPassword("demoPassword");
		patient.setContactNo("123456789");
		patient.setAddress("DemoAddress");
		patient.setPincode("700098");
		patient.getAuthorities().add(new Authority(111, "ROLE_USER", patient));
		
		when(patientRepository.findById(anyInt())).thenReturn(Optional.of(patient));
		assertEquals(Optional.of(patient).get(), patientService.getpatientById(anyInt()));
	}
	
	
	@Test
	public void deletePatientTest() throws PatientException {
		
		Patient patient = new Patient();
		patient.setId(100);
		patient.setName("Demo");
		patient.setEmail("demo@gmail.com");
		patient.setPassword("demoPassword");
		patient.setContactNo("123456789");
		patient.setAddress("DemoAddress");
		patient.setPincode("700098");
		patient.getAuthorities().add(new Authority(111, "ROLE_USER", patient));
		
		patientService.deletePatient(patient);
		verify(patientRepository,times(1)).delete(patient);
	}
	

}
