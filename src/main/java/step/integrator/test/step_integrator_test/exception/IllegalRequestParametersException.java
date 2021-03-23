package step.integrator.test.step_integrator_test.exception;

/**
 * Exception represents illegal set of input parameters of GET request
 */
public class IllegalRequestParametersException extends Exception {

    private final String phone;
    private final String email;

    public IllegalRequestParametersException(String message, String phone, String email) {
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
