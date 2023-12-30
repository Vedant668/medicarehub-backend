package com.medicarehub.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.medicarehub.entity.Appointment;


public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{
	
	
	 @Query("SELECT a FROM Appointment a WHERE a.doctor.id = :doctorId")
	 List<Appointment> getAppointmentsByDoctorId(@Param("doctorId") int doctorId);
	
}
