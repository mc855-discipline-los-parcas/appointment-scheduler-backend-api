package br.unicamp.cecom.appointmentscheduler.core.features.patient;

import br.unicamp.cecom.appointmentscheduler.core.exception.NotFoundException;
import br.unicamp.cecom.appointmentscheduler.core.features.doctor.DoctorEntity;
import br.unicamp.cecom.appointmentscheduler.core.features.patient.to.request.CreatePatientRequest;
import br.unicamp.cecom.appointmentscheduler.core.features.patient.to.request.UpdatePatientRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
            .patientCPF(request.getCpf())
            .email(request.getEmail())
            .fullname(request.getFullName())
            .phone(request.getPhone())
            .build());
    }

    public void update(final String patientCPF, final UpdatePatientRequest request) {

        try {
            PatientEntity patient = patientRepository.getOne(patientCPF);

            patient.setPatientCPF(request.getCpf());
            patient.setEmail(request.getEmail());
            patient.setFullname(request.getFullName());
            patient.setPhone(request.getPhone());

            patientRepository.save(patient);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "message.patient.notFound");
        }
    }

    public void delete(final String patientCPF) {
        try {
            patientRepository.deleteById(patientCPF);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "message.patient.notFound");
        }
    }

    public Optional<PatientEntity> findByCPF(final String patientCPF) {
        Optional<PatientEntity> patientEntity = patientRepository.findById(patientCPF);
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
