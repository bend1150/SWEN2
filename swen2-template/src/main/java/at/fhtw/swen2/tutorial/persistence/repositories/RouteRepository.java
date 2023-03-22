package at.fhtw.swen2.tutorial.persistence.repositories;
import at.fhtw.swen2.tutorial.persistence.entities.TourEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<TourEntity, Long> {


}
