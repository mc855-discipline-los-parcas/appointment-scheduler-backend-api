package br.unicamp.cecom.appointmentscheduler.core.features.schedule;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class ScheduleEntityKey implements Serializable {

    private UUID doctorId;

    private String patientCPF;

    private UUID appointmentId;

    @Column(name = "doctor_pk")
    public UUID getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(UUID doctorId) {
        this.doctorId = doctorId;
    }

    @Column(name = "patient_pk")
    public String getPatientCPF() {
        return patientCPF;
    }

    public void setPatientCPF(String patientCPF) {
        this.patientCPF = patientCPF;
    }

    @Column(name = "appointment_pk")
    public UUID getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(UUID appointmentId) {
        this.appointmentId = appointmentId;
    }
}
