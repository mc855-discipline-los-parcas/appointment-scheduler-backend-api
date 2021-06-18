package br.unicamp.cecom.appointmentscheduler.core.features.doctor;

import br.unicamp.cecom.appointmentscheduler.core.enums.Specialty;
import br.unicamp.cecom.appointmentscheduler.core.exception.NotFoundException;
import br.unicamp.cecom.appointmentscheduler.core.features.doctor.to.request.CreateDoctorRequest;
import br.unicamp.cecom.appointmentscheduler.core.features.doctor.to.request.UpdateDoctorRequest;
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
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorEntity create(final CreateDoctorRequest request) {
        try {
            return doctorRepository.save(DoctorEntity.builder()
                    .doctorId(UUID.randomUUID())
                    .email(request.getEmail())
                    .fullname(request.getFullName())
                    .phone(request.getPhone())
                    .specialty(Specialty.valueOf(request.getSpecialty()))
                    .crm(request.getCrm())
                    .appointmentDuration(request.getAppointmentDuration())
                    .build());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "message.specialty.invalid");
        }
    }

    public void update(final UUID doctorId, final UpdateDoctorRequest request) {

        try {
            DoctorEntity doctor = doctorRepository.getOne(doctorId);

            doctor.setEmail(request.getEmail());
            doctor.setFullname(request.getFullName());
            doctor.setPhone(request.getPhone());
            doctor.setCrm(request.getCrm());
            doctor.setAppointmentDuration(request.getAppointmentDuration());
            doctor.setSpecialty(Specialty.valueOf(request.getSpecialty()));

            doctorRepository.save(doctor);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"message.doctor.notFound");
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"message.specialty.invalid");
        }
    }

    public void delete(final UUID doctorId) {
        try {
            doctorRepository.deleteById(doctorId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"message.doctor.notFound");
        }
    }

    public Optional<DoctorEntity> findById(final UUID doctorId) {
        Optional<DoctorEntity> doctorEntity = doctorRepository.findById(doctorId);
       if(doctorEntity.isPresent()) {
           return doctorEntity;
       } else{
           throw new ResponseStatusException(HttpStatus.NOT_FOUND,"message.doctor.notFound");
       }
    }

    public List<DoctorEntity> listDoctors() {
        return this.doctorRepository.findAll();
    }
}
