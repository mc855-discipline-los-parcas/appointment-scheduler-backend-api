package br.unicamp.cecom.appointmentscheduler.core.features.patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional
public interface PatientRepository extends JpaRepository<PatientEntity, UUID> {}
