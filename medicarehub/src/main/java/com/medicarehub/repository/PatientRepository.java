package com.medicarehub.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medicarehub.entity.Patient;


public interface PatientRepository extends JpaRepository<Patient, Integer>{
	
	public Optional<Patient> findByPhone(String phone);
	
	
}
