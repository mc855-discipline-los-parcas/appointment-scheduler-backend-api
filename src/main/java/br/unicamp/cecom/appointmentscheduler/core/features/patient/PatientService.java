package br.unicamp.cecom.appointmentscheduler.core.features.patient;

import br.unicamp.cecom.appointmentscheduler.core.features.patient.to.request.CreatePatientRequest;
import br.unicamp.cecom.appointmentscheduler.core.features.patient.to.request.UpdatePatientRequest;
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

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientEntity create(final CreatePatientRequest request) {
        return patientRepository.save(PatientEntity.builder()
            .patientCpf(request.getCpf())
            .email(request.getEmail())
            .fullname(request.getFullName())
            .phone(request.getPhone())
            .build());
    }

    public void update(final String patientCpf, final UpdatePatientRequest request) {
        try {
            PatientEntity patient = patientRepository.getOne(patientCpf);

            patient.setEmail(request.getEmail());
            patient.setFullname(request.getFullName());
            patient.setPhone(request.getPhone());

            patientRepository.save(patient);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "message.patient.notFound");
        }
    }

    public void delete(final String patientCpf) {
        try {
            patientRepository.deleteById(patientCpf);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "message.patient.notFound");
        }
    }

    public Optional<PatientEntity> findByCpf(final String patientCpf) {
        Optional<PatientEntity> patientEntity = patientRepository.findById(patientCpf);
        if(patientEntity.isPresent()) {
            return patientEntity;
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"message.doctor.notFound");
        }
    }

    public List<PatientEntity> listPatients() {
        return this.patientRepository.findAll();
    }
}
