package br.unicamp.cecom.appointmentscheduler.core.features.work_day;

import br.unicamp.cecom.appointmentscheduler.core.enums.WeekDay;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;


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

    @NotBlank(message = "Week Day must not be null and must contain at least one non-whitespace character")
    @Enumerated(EnumType.STRING)
    private WeekDay weekDay;

    @Temporal(TemporalType.TIME)
    private Date workStartTime;

    @Temporal(TemporalType.TIME)
    private Date workEndTime;

    @Temporal(TemporalType.TIME)
    private Date lunchStartTime;

    @Temporal(TemporalType.TIME)
    private Date lunchEndTime;
}
