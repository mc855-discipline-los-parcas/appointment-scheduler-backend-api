package br.unicamp.cecom.appointmentscheduler.core.features.appointment;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "/api/v1/appointments", produces = APPLICATION_JSON_VALUE)
public class AppointmentRestController {

    private final AppointmentService appointmentService;
}
