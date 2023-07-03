package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.app.exception.PatientException;
import com.app.model.Authority;
import com.app.model.Patient;
import com.app.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService{

	@Autowired
	private PatientRepository patientRepository;
	
	@Override
	public Patient registerPatient(Patient patient) {
		
		List<Authority> authorities= patient.getAuthorities();
		
		for(Authority authority:authorities) {
			authority.setPatient(patient);
		}
		
		return patientRepository.save(patient);
	}

	@Override
	public Patient getPatientDetailsByEmail(String email) throws PatientException{
		
		return patientRepository.findByEmail(email).orElseThrow(() -> new PatientException("Patient not found with email: "+email));
	}
	
	@Override
	public Patient getpatientById(Integer patientId) throws PatientException {
		
		Optional<Patient> opt = patientRepository.findById(patientId);
		if (opt.isPresent()) {
			Patient patient = opt.get();
			return patient;
		}
		else {
			throw new PatientException("Not Found");
		}
	}
	
	
	@Override
	public Patient getMyDetails() throws PatientException {
		
		Optional<Patient> opt = patientRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		System.out.println(opt.get());
		if (opt.isEmpty()) {
			throw new PatientException("Not found");
		}
		else {
			return opt.get();
		}
	}
	

	@Override
	public List<Patient> getAllPatients() throws PatientException {
		
		List<Patient> patients = patientRepository.findAll();
		if (patients.isEmpty()) {
			throw new PatientException("Empty List");
		}
		else {
			return patients;
		}
	}

	@Override
	public Patient updatePatient(Patient patient, Integer patientId) throws PatientException {
		
		Optional<Patient> opt = patientRepository.findById(patientId);
		if (opt.isPresent()) {
			Patient foundPatient = opt.get();
			foundPatient.setName(patient.getName());
			foundPatient.setContactNo(patient.getContactNo());
			foundPatient.setAddress(patient.getAddress());
			foundPatient.setPincode(patient.getPincode());
			return patientRepository.save(foundPatient);
			
		}
		else {
			throw new PatientException("Not Found");
		}
	}

	@Override
	public Patient deletePatient(Integer patientId) throws PatientException {
		
		Optional<Patient> opt = patientRepository.findById(patientId);
		if (opt.isPresent()) {
			Patient patient = opt.get();
			patientRepository.delete(patient);
			return patient;
		}
		else {
			throw new PatientException("Not Found");
		}
	}

	

	

	
}
