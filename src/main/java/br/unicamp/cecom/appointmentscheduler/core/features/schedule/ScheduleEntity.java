package br.unicamp.cecom.appointmentscheduler.core.features.schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "schedule")
public class ScheduleEntity {

    @EmbeddedId
    private ScheduleEntityKey id;
}
