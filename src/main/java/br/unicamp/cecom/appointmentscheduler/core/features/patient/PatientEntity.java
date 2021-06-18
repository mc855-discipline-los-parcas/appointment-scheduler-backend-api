package br.unicamp.cecom.appointmentscheduler.core.features.patient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "patient")
public class PatientEntity {
    @Id
    @Size(max = 11, min = 11, message = "CPF must have 11 characters")
    private String patientCPF;

    @NotBlank(message = "Name must not be null and must contain at least one non-whitespace character")
    @Max(value = 255, message = "Name must have a maximum of 255 characters")
    private String fullname;

    @NotBlank(message = "Email must not be null and must contain at least one non-whitespace character")
    @Max(value = 50, message = "Email must have a maximum of 50 characters")
    private String email;

    @NotBlank(message = "Phone must not be null and must contain at least one non-whitespace character")
    @Max(value = 15, message = "Phone must have a maximum of 15 characters")
    private String phone;

}
