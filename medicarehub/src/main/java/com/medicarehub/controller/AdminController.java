package com.medicarehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.medicarehub.dto.BookingStatus;
import com.medicarehub.dto.LoginStatus;
import com.medicarehub.dto.RemovingStatus;
import com.medicarehub.entity.Admin;
import com.medicarehub.entity.Appointment;
import com.medicarehub.entity.Doctor;
import com.medicarehub.entity.Patient;
import com.medicarehub.exception.AdminServiceException;
import com.medicarehub.exception.AppointmentServiceException;
import com.medicarehub.service.AdminService;
import com.medicarehub.service.PatientService;

@RestController
@CrossOrigin
public class AdminController {
	
	@Autowired
	public AdminService adminService;
	
	

	@PostMapping("/adminLogin")
	public LoginStatus login(@RequestBody Admin admin) {
		try {
			Admin ad = adminService.login(admin);
			
			LoginStatus status = new LoginStatus();
			status.setLoginId(ad.getId());
			status.setLoginName(ad.getName());
			status.setLoginPhone(ad.getPhone());
			status.setLoginEmail(ad.getEmail());
			status.setLoginGender(ad.getGender());
			status.setLoginCity(ad.getCity());
			status.setLoginStatus(true);
			status.setLoginStatusMessage("Login Successfully!");
			
			return status;
		}
		catch (AdminServiceException e) {
			LoginStatus  status = new LoginStatus ();
			status.setLoginStatus(false);
			status.setLoginStatusMessage(e.getMessage());
			return status;
		}
		
		 
	}
	
	
	@GetMapping("/fetchPatients")
    public ResponseEntity<List<Patient>> getAllPatients() {
        try {
            List<Patient> patients = adminService.getAllPatients();
            return ResponseEntity.ok(patients);
        } catch (AdminServiceException e) {
        	
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
	
	@GetMapping("/fetchDoctors")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        try {
            List<Doctor> doctors = adminService.getAllDoctors();
            return ResponseEntity.ok(doctors);
        } catch (AdminServiceException e) {
        	
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
	
	@GetMapping("/fetchAdmins")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        try {
            List<Admin> admins = adminService.getAllAdmins();
            return ResponseEntity.ok(admins);
        } catch (AdminServiceException e) {
        	
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
	
	@DeleteMapping("/removePatient/{patientId}")
    public RemovingStatus removePatient(@PathVariable int patientId) {
    	boolean removingStatus= adminService.deletePatientById(patientId);
    	RemovingStatus status=new RemovingStatus();
		//status.setBookingId(appointmentStatus.getId());
		status.setRemovingStatus(true);
		status.setRemovingStatusMessage("User deleted Successfully!");
		return status; 
    }
	
	@DeleteMapping("/removeDoctor/{doctorId}")
    public RemovingStatus removeDoctor(@PathVariable int doctorId) {
    	boolean removingStatus= adminService.deleteDoctorById(doctorId);
    	RemovingStatus status=new RemovingStatus();
		//status.setBookingId(appointmentStatus.getId());
		status.setRemovingStatus(true);
		status.setRemovingStatusMessage("User deleted Successfully!");
		return status; 
    }
	
	@DeleteMapping("/removeAdmin/{adminId}")
    public RemovingStatus removeAdmin(@PathVariable int adminId) {
    	boolean removingStatus= adminService.deleteAdminId(adminId);
    	RemovingStatus status=new RemovingStatus();
		//status.setBookingId(appointmentStatus.getId());
		status.setRemovingStatus(true);
		status.setRemovingStatusMessage("User deleted Successfully!");
		return status; 
    }
}
