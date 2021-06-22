package br.unicamp.cecom.appointmentscheduler.core.features.doctor;

import br.unicamp.cecom.appointmentscheduler.core.enums.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface DoctorRepository extends JpaRepository<DoctorEntity, UUID> {

    List<DoctorEntity> findBySpecialty(final Specialty specialty);
}
