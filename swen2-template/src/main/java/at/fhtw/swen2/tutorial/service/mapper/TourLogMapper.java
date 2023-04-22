package at.fhtw.swen2.tutorial.service.mapper;
import at.fhtw.swen2.tutorial.persistence.entities.TourLogEntity;
import at.fhtw.swen2.tutorial.service.model.TourLog;
import org.springframework.stereotype.Component;
@Component
public class TourLogMapper extends AbstractMapper<TourLogEntity, TourLog>{
    @Override
    public TourLog fromEntity(TourLogEntity entity) {
        return null;
    }

    @Override
    public TourLogEntity toEntity(TourLog dto) {
        return null;
    }
}
