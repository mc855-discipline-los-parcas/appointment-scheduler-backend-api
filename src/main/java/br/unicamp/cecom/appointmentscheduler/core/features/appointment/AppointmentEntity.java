package br.unicamp.cecom.appointmentscheduler.core.features.appointment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="dd.MM.yyyy hh:mm")
    private Date startDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="dd.MM.yyyy hh:mm")
    private Date endDateTime;

    @PrePersist
    public void prePersist() {
        this.appointmentId = UUID.randomUUID();
    }
}
