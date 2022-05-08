package pl.edu.pw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pw.domain.Departure;

import java.util.List;

@Repository
public interface DepartureRepository extends JpaRepository<Departure, Long> {

    List<Departure> findAllByTrainStation_Name(String name);

}
