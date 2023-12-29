package com.medicarehub.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.medicarehub.entity.Appointment;


public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{

}
