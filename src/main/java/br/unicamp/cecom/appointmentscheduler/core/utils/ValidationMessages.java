package br.unicamp.cecom.appointmentscheduler.core.utils;

public class ValidationMessages {

    private ValidationMessages() {
        throw new IllegalStateException("Utility class");
    }

    public static final String FULLNAME_NOT_BLANK_MSG = "Name must not be null or empty";
    public static final String FULLNAME_SIZE_MSG = "Name must have a maximum of 255 characters";
    public static final String FULLNAME_PATTERN_MSG = "Name must have at least first and last name";

    public static final String EMAIL_NOT_BLANK_MSG = "Email must not be null or empty";
    public static final String EMAIL_PATTERN_MSG = "Email should be valid";

    public static final String PHONE_NOT_BLANK_MSG = "Phone must not be null or empty";
    public static final String PHONE_PATTERN_MSG = "Phone should follow the pattern (xx) xxxxx-xxxx";

    public static final String CPF_NOT_BLANK_MSG = "CPF must not be null or empty";
    public static final String CPF_PATTERN_MSG = "CPF must be numeric and have 11 digits";

    public static final String DOCTOR_CRM_NOT_BLANK_MSG = "CRM must not be null and must contain at least one non-whitespace character";
    public static final String DOCTOR_CRM_SIZE_MSG = "CRM must have a maximum of 50 characters";
    public static final String DOCTOR_SPECIALTY_NOT_NULL_MSG = "Specialty must be not null";
}
