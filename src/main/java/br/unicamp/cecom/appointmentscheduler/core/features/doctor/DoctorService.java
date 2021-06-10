package br.unicamp.cecom.appointmentscheduler.core.features.doctor;

import br.unicamp.cecom.appointmentscheduler.core.exception.NotFoundException;
import br.unicamp.cecom.appointmentscheduler.core.features.doctor.to.request.CreateDoctorRequest;
import br.unicamp.cecom.appointmentscheduler.core.features.doctor.to.request.UpdateDoctorRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        return doctorRepository.save(DoctorEntity.builder()
                .doctorId(UUID.randomUUID())
                .email(request.getEmail())
                .fullname(request.getFullName())
                .phone(request.getPhone())
                .build());
    }

    public void update(final UUID doctorId, final UpdateDoctorRequest request) {

        try {
            DoctorEntity doctor = doctorRepository.getOne(doctorId);

            doctor.setEmail(request.getEmail());
            doctor.setFullname(request.getFullName());
            doctor.setPhone(request.getPhone());

            doctorRepository.save(doctor);
        } catch (EntityNotFoundException e) {
            throw new NotFoundException("message.doctor.notFound");
        }
    }

    public void delete(final UUID doctorId) {
        try {
            doctorRepository.deleteById(doctorId);
        } catch (EntityNotFoundException e) {
            throw new NotFoundException("message.doctor.notFound");
        }
    }

    public Optional<DoctorEntity> findById(final UUID doctorId) {
        try {
            return doctorRepository.findById(doctorId);
        } catch (EntityNotFoundException e) {
            throw new NotFoundException("message.doctor.notFound");
        }
    }

    public List<DoctorEntity> listDoctors() {
        return this.doctorRepository.findAll();
    }
}
