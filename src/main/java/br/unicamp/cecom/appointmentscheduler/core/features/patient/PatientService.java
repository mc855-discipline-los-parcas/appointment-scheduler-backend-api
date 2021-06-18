package br.unicamp.cecom.appointmentscheduler.core.features.patient;

import br.unicamp.cecom.appointmentscheduler.core.exception.NotFoundException;
import br.unicamp.cecom.appointmentscheduler.core.features.patient.to.request.CreatePatientRequest;
import br.unicamp.cecom.appointmentscheduler.core.features.patient.to.request.UpdatePatientRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            throw new NotFoundException("message.patient.notFound");
        }
    }

    public void delete(final String patientCPF) {
        try {
            patientRepository.deleteById(patientCPF);
        } catch (EntityNotFoundException e) {
            throw new NotFoundException("message.patient.notFound");
        }
    }

    public Optional<PatientEntity> findByCPF(final String patientCPF) {
        try {
            return patientRepository.findById(patientCPF);
        } catch (EntityNotFoundException e) {
            throw new NotFoundException("message.patient.notFound");
        }
    }

    public List<PatientEntity> listPatients() {
        return this.patientRepository.findAll();
    }
}
