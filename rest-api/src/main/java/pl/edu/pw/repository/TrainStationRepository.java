package pl.edu.pw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pw.domain.TrainStation;

@Repository
public interface TrainStationRepository extends JpaRepository<TrainStation, Long> {
}
