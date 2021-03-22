package step.integrator.test.step_integrator_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import step.integrator.test.step_integrator_test.entity.RequestLog;
import step.integrator.test.step_integrator_test.repo.RequestLogRepo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/log")
public class DataRestController {

    private RequestLogRepo repo;

    @Autowired
    public DataRestController(RequestLogRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/whole")
    public List<RequestLog> getWholeHistory() {
        return repo.findByOrderByTimestamp();
    }

    @GetMapping("/today")
    public List<RequestLog> getTodayHistory() {
        return repo.findByTimestampBetweenOrderByTimestamp(LocalDateTime.of(LocalDate.now(), LocalTime.MIN), LocalDateTime.now());
    }
}
