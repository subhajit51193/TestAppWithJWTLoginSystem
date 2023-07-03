package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer>{

}
