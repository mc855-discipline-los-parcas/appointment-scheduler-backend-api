package br.unicamp.cecom.appointmentscheduler.core.features.doctor;

import br.unicamp.cecom.appointmentscheduler.core.features.doctor.to.request.CreateDoctorRequest;
import br.unicamp.cecom.appointmentscheduler.core.features.doctor.to.request.UpdateDoctorRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "/api/v1/doctors", produces = APPLICATION_JSON_VALUE)
public class DoctorRestController {

    private final DoctorService doctorService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity create(@Validated @RequestBody CreateDoctorRequest request){
        final DoctorEntity doctor = doctorService.create(request);
        return created(URI.create(format("/api/v1/doctors/%s", doctor.getDoctorId()))).build();
    }

    @PutMapping(value = "/{doctorId}")
    public ResponseEntity update(@Validated @PathVariable UUID doctorId, @Validated @RequestBody UpdateDoctorRequest request) {
        doctorService.update(doctorId, request);
        return noContent().build();
    }

    @DeleteMapping(value = "/{doctorId}")
    public ResponseEntity delete(@Validated @PathVariable UUID doctorId){
        doctorService.delete(doctorId);
        return noContent().build();
    }

    @GetMapping(value = "/{doctorId}" )
    public ResponseEntity findById(@Validated @PathVariable UUID doctorId){
        return Optional.ofNullable(doctorService.findById(doctorId))
                .map(doctor -> ResponseEntity.ok().body(doctor))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity listDoctors(){
        return Optional.ofNullable(doctorService.listDoctors())
                .map(doctor -> ResponseEntity.ok().body(doctor))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
