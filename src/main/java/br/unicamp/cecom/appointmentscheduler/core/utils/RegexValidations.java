package br.unicamp.cecom.appointmentscheduler.core.utils;

public class RegexValidations {

    private RegexValidations() {
        throw new IllegalStateException("Utility class");
    }

    public static final String FULLNAME_REGEX = "^[A-Za-z]+ [A-Za-z]+(?: [A-Za-z]+)*+$";

    public static final String EMAIL_REGEX = "^[a-z0-9.]+@[a-z0-9]+\\.[a-z]+(?:\\.[a-z]+)?$";

    public static final String PHONE_REGEX = "^\\(\\d{2}\\) \\d{5}\\-\\d{4}$";

    public static final String CPF_REGEX = "\\d{11}";
}
