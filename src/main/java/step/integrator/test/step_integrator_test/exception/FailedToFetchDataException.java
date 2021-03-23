package step.integrator.test.step_integrator_test.exception;

/**
 * Exception represents failures while getting users data from outer REST service
 */
public class FailedToFetchDataException extends Exception {

    private final String phone;
    private final String email;

    public FailedToFetchDataException(String message, String phone, String email) {
        super(message);
        this.phone = phone;
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
