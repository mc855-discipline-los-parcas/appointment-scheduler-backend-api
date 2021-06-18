package br.unicamp.cecom.appointmentscheduler.core.features.doctor;

import br.unicamp.cecom.appointmentscheduler.core.enums.Specialty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "doctor")
public class DoctorEntity {
    @Id
    private UUID doctorId;

    @NotBlank(message = "Name must not be null and must contain at least one non-whitespace character")
    @Size(max = 255, message = "Name must have a maximum of 255 characters")
    private String fullname;

    @NotBlank(message = "Email must not be null and must contain at least one non-whitespace character")
    @Size(max = 50, message = "Email must have a maximum of 50 characters")
    private String email;

    @NotBlank(message = "Phone must not be null and must contain at least one non-whitespace character")
    @Size(max = 15, message = "Phone must have a maximum of 15 characters")
    private String phone;

    @NotBlank(message = "CRM must not be null and must contain at least one non-whitespace character")
    @Size(max = 50, message = "CRM must have a maximum of 50 characters")
    private String crm;

    @NotNull(message = "Specialty must be not null")
    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @NotNull(message = "Appointment Duration must be not null")
    private Integer appointmentDuration;

    @PrePersist
    public void prePersist() {
        this.doctorId = UUID.randomUUID();
    }
}
