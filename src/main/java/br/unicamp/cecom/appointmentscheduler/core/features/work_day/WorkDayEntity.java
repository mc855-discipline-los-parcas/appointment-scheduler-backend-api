package br.unicamp.cecom.appointmentscheduler.core.features.work_day;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "work_day")
public class WorkDayEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer workDayId;

    @NotBlank(message = "WeekDay must not be null and must contain at least one non-whitespace character")
    private String weekDay;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Timestamp workStartTime;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Timestamp workEndTime;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Timestamp lunchStartTime;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Timestamp lunchEndTime;
}
