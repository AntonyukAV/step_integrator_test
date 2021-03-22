package step.integrator.test.step_integrator_test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import step.integrator.test.step_integrator_test.entity.user_model.User;
import step.integrator.test.step_integrator_test.exception.FailedToFetchDataException;
import step.integrator.test.step_integrator_test.exception.IllegalRequestParametersException;
import step.integrator.test.step_integrator_test.repo.LogWriter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class StepIntegratorTestService {

    private HttpRestService restService;
    private LogWriter logWriter;

    @Autowired
    public StepIntegratorTestService(HttpRestService restService, LogWriter logWriter) {
        this.restService = restService;
        this.logWriter = logWriter;
    }

    public String getUsernameByPhone(String phoneNumber) throws IllegalRequestParametersException, FailedToFetchDataException {
        List<User> users;
        try {
            users = restService.getUsers();
        } catch (IOException | InterruptedException e) {
            throw new FailedToFetchDataException("Failed to fetch data", phoneNumber, null);
        }
        Optional<User> userOpt = users.stream()
                .filter(u -> u.getPhone().equals(phoneNumber))
                .findAny();
        if (userOpt.isPresent()) {
            String username = userOpt.get().getUsername();
            logWriter.successWithPhone(phoneNumber, username);
            return username;
        } else {
            throw new IllegalRequestParametersException("User with such phone number not found", phoneNumber, null);
        }
    }

    public String getUsernameByEmail(String email) throws IllegalRequestParametersException, FailedToFetchDataException {
        List<User> users;
        try {
            users = restService.getUsers();
        } catch (IOException | InterruptedException e) {
            throw new FailedToFetchDataException("Failed to fetch data", null, email);
        }
        Optional<User> userOpt = users.stream()
                .filter(u -> u.getEmail().equals(email))
                .findAny();
        if (userOpt.isPresent()) {
            String username = userOpt.get().getUsername();
            logWriter.successWithEmail(email, username);
            return username;
        } else {
            throw new IllegalRequestParametersException("User with such e-mail not found", null, email);
        }
    }
}
