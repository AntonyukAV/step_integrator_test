package step.integrator.test.step_integrator_test.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import step.integrator.test.step_integrator_test.entity.RequestLog;

import java.time.LocalDateTime;

@Component
public class LogWriter {

    private RequestLogRepo repo;

    @Autowired
    public LogWriter(RequestLogRepo repo) {
        this.repo = repo;
    }

    public void successWithEmail(String email, String username) {
        repo.save(new RequestLog(
                LocalDateTime.now(),
                null,
                email,
                200,
                username
        ));
    }

    public void successWithPhone(String phone, String username) {
        repo.save(new RequestLog(
                LocalDateTime.now(),
                phone,
                null,
                200,
                username
        ));
    }

    public void fail(int status, String phone, String email) {
        repo.save(new RequestLog(
                LocalDateTime.now(),
                phone,
                email,
                status,
                null
        ));
    }
}
