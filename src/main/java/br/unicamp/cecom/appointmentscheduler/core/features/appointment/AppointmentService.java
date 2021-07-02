package br.unicamp.cecom.appointmentscheduler.core.features.appointment;

import br.unicamp.cecom.appointmentscheduler.core.features.appointment.to.request.CreateAppointmentRequest;
import br.unicamp.cecom.appointmentscheduler.core.features.appointment.to.request.UpdateAppointmentRequest;
import br.unicamp.cecom.appointmentscheduler.core.features.doctor.DoctorEntity;
import br.unicamp.cecom.appointmentscheduler.core.features.doctor.DoctorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentEntity create(final CreateAppointmentRequest request) {
        Optional<DoctorEntity> doctorEntity = doctorRepository.findById(request.getDoctorId());
        if(doctorEntity.isPresent()) {
            return appointmentRepository.save(AppointmentEntity.builder()
                    .appointmentId(UUID.randomUUID())
                    .doctor(doctorEntity.get())
                    .patientCpf(request.getPatientCpf())
                    .dateTime(request.getDateTime())
                    .build());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "message.doctor.notFound");
        }
    }

    public void update(final UUID appointmentId, final UpdateAppointmentRequest request) {
        Optional<DoctorEntity> doctorEntity = doctorRepository.findById(request.getDoctorId());

        if(doctorEntity.isPresent()) {
            try {
                AppointmentEntity appointment = appointmentRepository.getOne(appointmentId);

                appointment.setDoctor(doctorEntity.get());
                appointment.setPatientCpf(request.getPatientCpf());
                appointment.setDateTime(request.getDateTime());

                appointmentRepository.save(appointment);
            } catch (EntityNotFoundException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "message.appointment.notFound");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "message.appointment.notFound");
        }
    }

    public void delete(final UUID appointmentId) {
        try {
            appointmentRepository.deleteById(appointmentId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "message.appointment.notFound");
        }
    }

    public Optional<AppointmentEntity> findById(final UUID appointmentId) {
        Optional<AppointmentEntity> appointmentEntity = appointmentRepository.findById(appointmentId);
        if (appointmentEntity.isPresent()) {
            return appointmentEntity;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"message.appointment.notFound");
        }
    }

    public List<AppointmentEntity> listAppointments() {
        return this.appointmentRepository.findAll();
    }

    public List<AppointmentEntity> findByDoctorId(final UUID doctorId) {
        return this.appointmentRepository.findByDoctorDoctorId(doctorId);
    }

    public List<AppointmentEntity> findByPatientCpf(final String patientCpf) {
        return this.appointmentRepository.findByPatientCpf(patientCpf);
    }
}
