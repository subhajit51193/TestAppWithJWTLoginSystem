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
	public Patient registerPatient(Patient patient) throws PatientException {
		
		List<Authority> authorities= patient.getAuthorities();
		
		for(Authority authority:authorities) {
			authority.setPatient(patient);
		}
		
		Patient pt = patientRepository.save(patient);
		if (pt != null) {
			return pt;
		}
		else {
			throw new PatientException("");
		}
	}

	@Override
	public Patient getPatientDetailsByEmail(String email) throws PatientException{
		
		return patientRepository.findByEmail(email).orElseThrow(() -> new PatientException("Patient not found with email: "+email));
	}
	
	@Override
	public Patient getpatientById(Integer patientId) throws PatientException {
		
		Optional<Patient> opt = patientRepository.findById(patientId);
		if (opt == null) {
			throw new PatientException("Not Found");
		}
		else {
			if (opt.isPresent()) {
				Patient patient = opt.get();
				return patient;
			}
			else {
				throw new PatientException("Not Found");
			}
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
	public Patient updatePatient(Patient patient) throws PatientException {
	
		Optional<Patient> opt = patientRepository.findById(patient.getId());
		if (opt.isEmpty()) {
			throw new PatientException("Not found");
		}
		else {
			Patient updated = patientRepository.save(patient);
			return updated;
		}
	}

	@Override
	public Patient deletePatient(Patient patient) throws PatientException {
		
		if (patient == null) {
			throw new PatientException("Not found");
		}
		else {
			patientRepository.delete(patient);
			return patient;
		}
	}

	

	

	
}
