package br.unicamp.cecom.appointmentscheduler.core.features.doctor_work_day;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "doctor_work_day")
public class DoctorWorkDayEntity {
    @Id
    private UUID doctorId;

    @Id
    private Integer workDayId;
}
