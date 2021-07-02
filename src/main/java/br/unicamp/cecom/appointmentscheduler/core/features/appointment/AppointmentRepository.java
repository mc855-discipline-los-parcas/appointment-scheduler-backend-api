package br.unicamp.cecom.appointmentscheduler.core.features.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, UUID> {

    List<AppointmentEntity> findByDoctorDoctorId(final UUID doctorId);

    List<AppointmentEntity> findByPatientCpf(final String patientCpf);
}
