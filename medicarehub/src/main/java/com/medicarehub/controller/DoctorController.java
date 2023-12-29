package com.medicarehub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.medicarehub.dto.LoginStatus;
import com.medicarehub.entity.Doctor;
import com.medicarehub.exception.PatientServiceException;
import com.medicarehub.service.DoctorService;

@RestController
@CrossOrigin
public class DoctorController {
	
	@Autowired
	public DoctorService doctorService;
	
	@PostMapping("/doctorLogin")
	public LoginStatus login(@RequestBody Doctor doctor) {
		try {
			int id = doctorService.login(doctor);
			
			LoginStatus status = new LoginStatus();
			status.setLoginStatus(true);
			status.setLoginStatusMessage("Login Successfully!");
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
