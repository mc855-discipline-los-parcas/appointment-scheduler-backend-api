package br.unicamp.cecom.appointmentscheduler.core.features.appointment;

import br.unicamp.cecom.appointmentscheduler.core.features.doctor.DoctorEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

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
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonBackReference
    private DoctorEntity doctor;

    private String patientCpf;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat
    private Date dateTime;

    @PrePersist
    public void prePersist() {
        this.appointmentId = UUID.randomUUID();
    }
}
