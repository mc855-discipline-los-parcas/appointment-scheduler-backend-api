package br.unicamp.cecom.appointmentscheduler.core.features.doctor_work_day;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class DoctorWorkDayEntityKey implements Serializable {

    private UUID doctorId;

    private Integer workDayId;

    @Column(name = "doctor_pk")
    public UUID getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(UUID doctorId) {
        this.doctorId = doctorId;
    }

    @Column(name = "work_day_pk")
    public Integer getWorkDayId() {
        return workDayId;
    }

    public void setWorkDayId(Integer workDayId) {
        this.workDayId = workDayId;
    }
}
