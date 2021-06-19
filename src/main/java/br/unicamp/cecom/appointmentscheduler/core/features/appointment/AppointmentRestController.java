package br.unicamp.cecom.appointmentscheduler.core.features.appointment;

import br.unicamp.cecom.appointmentscheduler.core.features.appointment.AppointmentEntity;
import br.unicamp.cecom.appointmentscheduler.core.features.appointment.AppointmentService;
import br.unicamp.cecom.appointmentscheduler.core.features.appointment.to.request.CreateAppointmentRequest;
import br.unicamp.cecom.appointmentscheduler.core.features.appointment.to.request.UpdateAppointmentRequest;
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
@RequestMapping(value = "/api/v1/appointments", produces = APPLICATION_JSON_VALUE)
public class AppointmentRestController {

    private final AppointmentService appointmentService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity create(@Validated @RequestBody CreateAppointmentRequest request) {
        final AppointmentEntity appointment = appointmentService.create(request);
        return created(URI.create(format("/api/v1/appointments/%s", appointment.getAppointmentId()))).build();
    }

    @PutMapping(value = "/{appointmentId}")
    public ResponseEntity update(@Validated @PathVariable UUID appointmentId,
                                 @Validated @RequestBody UpdateAppointmentRequest request) {
        appointmentService.update(appointmentId, request);
        return noContent().build();
    }

    @DeleteMapping(value = "/{appointmentId}")
    public ResponseEntity delete(@Validated @PathVariable UUID appointmentId) {
        appointmentService.delete(appointmentId);
        return noContent().build();
    }

    @GetMapping(value = "/{appointmentId}")
    public ResponseEntity findById(@Validated @PathVariable UUID appointmentId) {
        return Optional.of(appointmentService.findById(appointmentId))
                .map(appointment -> ResponseEntity.ok().body(appointment))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity listAppointments() {
        return Optional.of(appointmentService.listAppointments())
                .map(appointment -> ResponseEntity.ok().body(appointment))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/{doctorId}/doctor")
    public ResponseEntity findByDoctorId(@Validated @PathVariable UUID doctorId) {
        return Optional.of(appointmentService.findByDoctorId(doctorId))
                .map(appointment -> ResponseEntity.ok().body(appointment))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/{patientCpf}/patient")
    public ResponseEntity findByPatientCpf(@Validated @PathVariable String patientCpf) {
        return Optional.of(appointmentService.findByPatientCpf(patientCpf))
                .map(appointment -> ResponseEntity.ok().body(appointment))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
