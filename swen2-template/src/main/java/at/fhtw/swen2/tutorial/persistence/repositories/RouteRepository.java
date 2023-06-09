package at.fhtw.swen2.tutorial.persistence.repositories;
import at.fhtw.swen2.tutorial.persistence.entities.TourEntity;
import at.fhtw.swen2.tutorial.persistence.entities.TourLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RouteRepository extends JpaRepository<TourEntity, Long> {

    @Query("SELECT t FROM TourEntity t WHERE LOWER(t.name) LIKE LOWER(CONCAT('%', :searchText, '%'))" +
            "OR LOWER(t.description) LIKE LOWER(CONCAT('%', :searchText, '%'))" +
            "OR LOWER(t.origin) LIKE LOWER(CONCAT('%', :searchText, '%'))" +
            "OR LOWER(t.destination) LIKE LOWER(CONCAT('%', :searchText, '%'))" +
            "OR LOWER(t.transportType) LIKE LOWER(CONCAT('%', :searchText, '%'))")
    List<TourEntity> filterByString(@Param("searchText") String searchText);

}
