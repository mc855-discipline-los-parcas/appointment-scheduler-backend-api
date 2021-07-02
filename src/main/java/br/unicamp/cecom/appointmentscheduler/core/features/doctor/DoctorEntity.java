package br.unicamp.cecom.appointmentscheduler.core.features.doctor;

import br.unicamp.cecom.appointmentscheduler.core.enums.Specialty;
import br.unicamp.cecom.appointmentscheduler.core.features.appointment.AppointmentEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.UUID;

import static br.unicamp.cecom.appointmentscheduler.core.utils.RegexValidations.*;
import static br.unicamp.cecom.appointmentscheduler.core.utils.ValidationMessages.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "doctor")
public class DoctorEntity {

    @Id
    private UUID doctorId;

    @NotBlank(message = FULLNAME_NOT_BLANK_MSG)
    @Size(max = 255, message = FULLNAME_SIZE_MSG)
    @Pattern(regexp = FULLNAME_REGEX, message = FULLNAME_PATTERN_MSG)
    private String fullname;

    @NotBlank(message = EMAIL_NOT_BLANK_MSG)
    @Email(regexp = EMAIL_REGEX, message = EMAIL_PATTERN_MSG)
    private String email;

    @NotBlank(message = PHONE_NOT_BLANK_MSG)
    @Pattern(regexp = PHONE_REGEX, message = PHONE_PATTERN_MSG)
    private String phone;

    @NotBlank(message = DOCTOR_CRM_NOT_BLANK_MSG)
    @Size(max = 50, message = DOCTOR_CRM_SIZE_MSG)
    @Column(unique = true)
    private String crm;

    @NotNull(message = DOCTOR_SPECIALTY_NOT_NULL_MSG)
    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @OneToMany(mappedBy = "doctor")
    @JsonManagedReference
    private List<AppointmentEntity> appointmentEntityList;

    @PrePersist
    public void prePersist() {
        this.doctorId = UUID.randomUUID();
    }
}
