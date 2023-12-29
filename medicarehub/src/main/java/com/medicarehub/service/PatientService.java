package com.medicarehub.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicarehub.entity.Patient;
import com.medicarehub.exception.PatientServiceException;
import com.medicarehub.repository.PatientRepository;

@Service
public class PatientService {
	
	@Autowired
	private PatientRepository patientRepository;
	
	public int register(Patient patient) {
	
		Optional<Patient> patientCheck = patientRepository.findByPhone(patient.getPhone());
		
		if(patientCheck.isEmpty()) {
			Patient savedPatient = patientRepository.save(patient);
			return savedPatient.getId();
		}
		else {
			throw new PatientServiceException("Patient already registered !");
		}
	}
	
	
	
	public int login(Patient patient) {
	    Optional<Patient> patientCheck = patientRepository.findByPhone(patient.getPhone());

	    if (patientCheck.isEmpty()) {
	        throw new PatientServiceException("Patient doesn't exist");
	    } else {
	        Patient existingPatient = patientCheck.get();
	        if (patient.getPassword().equals(existingPatient.getPassword()) && patient.getEmail().equals(existingPatient.getEmail())) {
	            return existingPatient.getId();
	        }
	    }

	    throw new PatientServiceException("Incorrect email or password");
	}
}
