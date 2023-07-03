package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.PatientException;
import com.app.model.Patient;
import com.app.service.PatientService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/patient")
public class AdminController {

	@Autowired
	private PatientService patientService;
	
	@PostMapping("/add")
	public ResponseEntity<Patient> addPatienthandler(@RequestBody Patient patient){
		
		Patient newPatient = patientService.registerPatient(patient);
		return new ResponseEntity<Patient>(newPatient,HttpStatus.CREATED);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Patient> getPatientDetailshandler(@PathVariable("id") Integer patientId) throws PatientException{
		
		Patient patient = patientService.getpatientById(patientId);
		return new ResponseEntity<Patient>(patient,HttpStatus.OK);
	}
	@GetMapping("/all")
	public ResponseEntity<List<Patient>> getAllPatientsHandler()throws PatientException{
		
		List<Patient> list = patientService.getAllPatients();
		return new ResponseEntity<List<Patient>>(list,HttpStatus.OK);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Patient> updatePatientHandler(@RequestBody Patient patient,@PathVariable("id") Integer patientId)throws PatientException{
		
		Patient updated = patientService.updatePatient(patient, patientId);
		return new ResponseEntity<Patient>(updated,HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Patient> deletePatientHandler(@PathVariable("id") Integer patientId)throws PatientException{
		
		Patient deletedPatient = patientService.deletePatient(patientId);
		return new ResponseEntity<Patient>(deletedPatient,HttpStatus.OK);
	}
}
