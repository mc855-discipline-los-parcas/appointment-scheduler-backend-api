package br.unicamp.cecom.appointmentscheduler.core.features.appointment.to.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAppointmentRequest {

    @NotNull(message = "doctor Id must not be null")
    private UUID doctorId;

    @Size(max = 11, message = "CPF must have a maximum of 11 characters")
    @Pattern(regexp="([0-9]{3}[.]?[0-9]{3}[.]?[0-9]{3}-[0-9]{2})|([0-9]{11})", message = "Invalid CPF number")
    private String patientCpf;

    @NotNull(message = "Date time must not be null")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="dd.MM.yyyy hh:mm")
    private Date dateTime;
}
