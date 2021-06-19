package br.unicamp.cecom.appointmentscheduler.core.features.appointment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.UUID;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "appointment")
public class AppointmentEntity {
    @Id
    private UUID appointmentId;

    @NotNull
    // TODO adicionar relacionamento com Doctor
    private UUID doctorId;

    @Size(max = 11, min = 11, message = "CPF must have 11 characters")
    private String patientCpf;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="dd.MM.yyyy hh:mm")
    private Date dateTime;

    @PrePersist
    public void prePersist() {
        this.appointmentId = UUID.randomUUID();
    }
}