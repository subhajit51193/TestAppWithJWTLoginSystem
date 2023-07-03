package com.app.service;

import java.util.List;

import com.app.exception.PatientException;
import com.app.model.Patient;

public interface PatientService {

	public Patient addPatient(Patient patient);
	
	public Patient getpatientById(Integer patientId)throws PatientException;
	
	public List<Patient> getAllPatients()throws PatientException;
	
	public Patient updatePatient(Patient patient,Integer patientId)throws PatientException;
	
	public Patient deletePatient(Integer patientId)throws PatientException;
}
