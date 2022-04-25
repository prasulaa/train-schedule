package pl.edu.pw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pw.domain.TrainSchedule;

@Repository
public interface TrainScheduleRepository extends JpaRepository<TrainSchedule, Long> {
}
