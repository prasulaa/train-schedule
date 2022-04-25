package pl.edu.pw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pw.domain.Departure;

@Repository
public interface DepartureRepository extends JpaRepository<Departure, Long> {
}
