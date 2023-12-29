package com.medicarehub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.medicarehub.dto.LoginStatus;
import com.medicarehub.dto.RegistrationStatus;
import com.medicarehub.entity.Patient;
import com.medicarehub.exception.PatientServiceException;
import com.medicarehub.service.PatientService;

@RestController
@CrossOrigin
public class PatientController {

	@Autowired
	private PatientService patientService;
	
	@PostMapping("/register")
	public RegistrationStatus register(@RequestBody Patient patient) {
		
		try {
			int id = patientService.register(patient);
			
			RegistrationStatus status = new RegistrationStatus();
			status.setStatus(true);
			status.setStatusMessage("Patient Registered Successfully!");
			status.setPatientId(id);
			return status;
		}
		catch (PatientServiceException e) {
			RegistrationStatus status = new RegistrationStatus();
			status.setStatus(false);
			status.setStatusMessage(e.getMessage());
			return status;
		}
	}
	@PostMapping("/patientLogin")
	public LoginStatus login(@RequestBody Patient patient) {
		try {
			int id = patientService.login(patient);
			
			LoginStatus status = new LoginStatus();
			status.setLoginStatus(true);
			status.setLoginStatusMessage("Patient Login Successfully!");
			status.setPatientId(id);
			return status;
		}
		catch (PatientServiceException e) {
			LoginStatus  status = new LoginStatus ();
			status.setLoginStatus(false);
			status.setLoginStatusMessage(e.getMessage());
			return status;
		}
	}
}
