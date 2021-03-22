package step.integrator.test.step_integrator_test.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "request_log")
public class RequestLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime timestamp;
    @Column(name = "input_phone")
    private String inputPhone;
    @Column(name = "input_email")
    private String inputEmail;
    @Column(name = "response_status")
    private int responseStatus;
    private String username;

    public RequestLog() {
    }

    public RequestLog(LocalDateTime timestamp, String inputPhone, String inputEmail, int responseStatus, String username) {
        this.timestamp = timestamp;
        this.inputPhone = inputPhone;
        this.inputEmail = inputEmail;
        this.responseStatus = responseStatus;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getInputPhone() {
        return inputPhone;
    }

    public void setInputPhone(String inputPhone) {
        this.inputPhone = inputPhone;
    }

    public String getInputEmail() {
        return inputEmail;
    }

    public void setInputEmail(String inputEmail) {
        this.inputEmail = inputEmail;
    }

    public int getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
