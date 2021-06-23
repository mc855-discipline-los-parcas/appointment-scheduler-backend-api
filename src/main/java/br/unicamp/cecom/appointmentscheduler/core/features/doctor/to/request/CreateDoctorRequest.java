package br.unicamp.cecom.appointmentscheduler.core.features.doctor.to.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

import static br.unicamp.cecom.appointmentscheduler.core.utils.RegexValidations.*;
import static br.unicamp.cecom.appointmentscheduler.core.utils.ValidationMessages.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateDoctorRequest {

    @NotBlank(message = FULLNAME_NOT_BLANK_MSG)
    @Size(max = 255, message = FULLNAME_SIZE_MSG)
    @Pattern(regexp = FULLNAME_REGEX, message = FULLNAME_PATTERN_MSG)
    private String fullName;

    @NotBlank(message = EMAIL_NOT_BLANK_MSG)
    @Email(regexp = EMAIL_REGEX, message = EMAIL_PATTERN_MSG)
    private String email;

    @NotBlank(message = PHONE_NOT_BLANK_MSG)
    @Pattern(regexp = PHONE_REGEX, message = PHONE_PATTERN_MSG)
    private String phone;

    @NotBlank(message = DOCTOR_CRM_NOT_BLANK_MSG)
    @Size(max = 50, message = DOCTOR_CRM_SIZE_MSG)
    private String crm;

    @NotNull(message = DOCTOR_SPECIALTY_NOT_NULL_MSG)
    @Enumerated(EnumType.STRING)
    private String specialty;
}
