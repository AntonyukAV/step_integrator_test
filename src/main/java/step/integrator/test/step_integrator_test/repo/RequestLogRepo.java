package step.integrator.test.step_integrator_test.repo;

import org.springframework.data.repository.CrudRepository;
import step.integrator.test.step_integrator_test.entity.RequestLog;

import java.time.LocalDateTime;
import java.util.List;

public interface RequestLogRepo extends CrudRepository<RequestLog, Integer> {

    List<RequestLog> findByOrderByTimestamp();

    List<RequestLog> findByTimestampBetweenOrderByTimestamp(LocalDateTime start, LocalDateTime end);
}
