package at.fhtw.swen2.tutorial.persistence.repositories;
import at.fhtw.swen2.tutorial.persistence.entities.TourEntity;
import at.fhtw.swen2.tutorial.persistence.entities.TourLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TourLogRepository extends JpaRepository<TourLogEntity, Long> {
    @Query("SELECT t FROM TourLogEntity t WHERE t.tourId = :tourId")
    List<TourLogEntity> findByTourId(@Param("tourId") Long tourId);

    @Modifying
    @Query("DELETE FROM TourLogEntity t WHERE t.tourId = :tourId")
    void deleteByTourId(@Param("tourId") Long tourId);
}
