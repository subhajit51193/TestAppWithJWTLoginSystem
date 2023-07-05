package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.app.exception.PatientException;
import com.app.model.Patient;
import com.app.service.PatientService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/patient")
public class PatientController {

	
		
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
//	---------------------------------------------------
	
	
	

	
	@GetMapping("/hello")
	public String testHandler() {
		return "Welcome to Spring Security";
	}
	
	
	/*
	
	 {
        "name": "ram",
        "email": "ram@gmail.com",
        "password": "1234",
        "contactNo" "7894561230"
        "address": "delhi",
        "pincode": "700056"
        "authorities":[
            {
                "name": "ROLE_USER"
            },
            {
                "name": "ROLE_ADMIN"
            }
        ]
    }
	
	
	
	*/
	
	@PostMapping("/signUp")
	public ResponseEntity<Patient> savePatientHandler(@RequestBody Patient patient) throws PatientException{

		
		patient.setPassword(passwordEncoder.encode(patient.getPassword()));
		
		Patient registeredPatient= patientService.registerPatient(patient);
		
		return new ResponseEntity<>(registeredPatient,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/getPatient/{email}")
	public ResponseEntity<Patient> getPatientByEmailHandler(@PathVariable("email") String email) throws PatientException{
		
		
		Patient patient= patientService.getPatientDetailsByEmail(email);
		
		return new ResponseEntity<>(patient,HttpStatus.ACCEPTED);
		
	}
	@GetMapping("/getMyDetails")
	public ResponseEntity<Patient> getMyDetails() throws PatientException{
		
		Patient myDetails = patientService.getMyDetails();
		return new ResponseEntity<Patient>(myDetails,HttpStatus.OK);
	}
	
	
}
