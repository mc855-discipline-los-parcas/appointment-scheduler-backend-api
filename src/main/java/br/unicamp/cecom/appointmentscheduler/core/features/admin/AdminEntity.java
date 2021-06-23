package br.unicamp.cecom.appointmentscheduler.core.features.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

import static br.unicamp.cecom.appointmentscheduler.core.utils.RegexValidations.*;
import static br.unicamp.cecom.appointmentscheduler.core.utils.ValidationMessages.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "admin")
public class AdminEntity {

    @Id
    private UUID adminId;

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

    @PrePersist
    public void prePersist() {
        this.adminId = UUID.randomUUID();
    }
}
