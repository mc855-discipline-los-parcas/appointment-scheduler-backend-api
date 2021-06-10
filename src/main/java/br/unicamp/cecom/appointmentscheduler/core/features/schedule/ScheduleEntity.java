package br.unicamp.cecom.appointmentscheduler.core.features.schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "schedule")
public class ScheduleEntity {
    @Id
    private UUID doctorId;

    @Id
    private String patientCPF;

    @Id
    private UUID appointmentId;
}
