package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer>{

	public Optional<Patient> findByEmail(String email);
}
