package br.unicamp.cecom.appointmentscheduler.core.features.patient;

import br.unicamp.cecom.appointmentscheduler.core.features.patient.to.request.CreatePatientRequest;
import br.unicamp.cecom.appointmentscheduler.core.features.patient.to.request.UpdatePatientRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

import static java.lang.String.format;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "/api/v1/patients", produces = APPLICATION_JSON_VALUE)
public class PatientRestController {

    private final PatientService patientService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity create(@Validated @RequestBody CreatePatientRequest request){
        final PatientEntity patient = patientService.create(request);
        return created(URI.create(format("/api/v1/patients/%s", patient.getPatientCPF()))).build();
    }

    @PutMapping(value = "/{patientCPF}")
    public ResponseEntity update(@Validated @PathVariable String patientCPF, @Validated @RequestBody UpdatePatientRequest request) {
        patientService.update(patientCPF, request);
        return noContent().build();
    }

    @DeleteMapping(value = "/{patientCPF}")
    public ResponseEntity delete(@Validated @PathVariable String patientCPF){
        patientService.delete(patientCPF);
        return noContent().build();
    }

    @GetMapping(value = "/{patientCPF}" )
    public ResponseEntity findById(@Validated @PathVariable String patientCPF){
        return Optional.ofNullable(patientService.findById(patientCPF))
                .map(patient -> ResponseEntity.ok().body(patient))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity listPatients(){
        return Optional.ofNullable(patientService.listPatients())
                .map(patient -> ResponseEntity.ok().body(patient))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
