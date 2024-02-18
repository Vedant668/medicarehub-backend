package com.medicarehub.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.medicarehub.dto.LoginStatus;
import com.medicarehub.dto.RegistrationStatus;
import com.medicarehub.entity.Appointment;
import com.medicarehub.entity.Doctor;
import com.medicarehub.entity.Patient;
import com.medicarehub.exception.PatientServiceException;
import com.medicarehub.exception.DoctorServiceException;
import com.medicarehub.service.AppointmentService;
import com.medicarehub.service.DoctorService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin
public class DoctorController {
	
	@Autowired
	public DoctorService doctorService;
	
	@Autowired
	public AppointmentService appointmnrtService;
	
	
	//---------------------------jwt method-----------------------------------------
	@Value("${JWTSecret}")
	private String jwtSecret;
	
	@Value("${JWTExpiration}")
	private long jwtExpiration;

	public String generateJwtToken(Doctor doctor) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(doctor.getPhone())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
	
//	
//	@PostMapping("/register")
//	public RegistrationStatus register(@RequestBody Doctor doctor) {
//		
//		try {
//			int id = doctorService.register(doctor);
//			
//			RegistrationStatus status = new RegistrationStatus();
//			status.setStatus(true);
//			status.setStatusMessage("Doctor Registered Successfully!");
//			status.setPatientId(id);
//			
//			return status;
//		}
//		catch (DoctorServiceException e) {
//			RegistrationStatus status = new RegistrationStatus();
//			status.setStatus(false);
//			status.setStatusMessage(e.getMessage());
//			return status;
//		}
//	}
//	
	
	@PostMapping("/doctorLogin")
	public LoginStatus login(@RequestBody Doctor doctor) {
		try {
			Doctor dr = doctorService.login(doctor);
			String token = generateJwtToken(doctor);
			LoginStatus status = new LoginStatus();
			status.setLoginId(dr.getId());
			status.setLoginName(dr.getName());
			status.setLoginPhone(dr.getPhone());
			status.setLoginEmail(dr.getEmail());
			status.setLoginGender(dr.getGender());
			status.setLoginCity(dr.getCity());
			status.setLoginStatus(true);
			status.setToken(token);
			status.setLoginStatusMessage("Login Successfully!");
			
			return status;
		}
		catch (DoctorServiceException e) {
			LoginStatus  status = new LoginStatus ();
			status.setLoginStatus(false);
			status.setLoginStatusMessage(e.getMessage());
			return status;
		}
	}
	
	
	@GetMapping("/getAllDoctors")
	public List <Doctor> getAllDoctors() {
		try {
		List <Doctor> doctors=doctorService.getAllDoctors();
		return doctors;
	}
	catch(DoctorServiceException e) {
		return null;
	}
		
	}
	
	
	@PostMapping("/getTimeSlot")
	public List <String> getTimeSlot(@RequestBody Appointment appointment) {
		try {
		List <String> timeSlot = appointmnrtService.getTimeSlotByDoctorAndDate(appointment);
		
		return timeSlot;
	}
	catch(DoctorServiceException e) {
		return null;
	}
		
	}
}
