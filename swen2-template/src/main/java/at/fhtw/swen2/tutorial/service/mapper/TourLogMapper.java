package at.fhtw.swen2.tutorial.service.mapper;
import at.fhtw.swen2.tutorial.persistence.entities.TourLogEntity;
import at.fhtw.swen2.tutorial.service.model.TourLog;
import org.springframework.stereotype.Component;
@Component
public class TourLogMapper extends AbstractMapper<TourLogEntity, TourLog>{
    @Override
    public TourLog fromEntity(TourLogEntity entity) {
        return TourLog.builder()
                .id(entity.getId())
                //.tourId(entity.getTourId())
                .date(entity.getDate())
                .time(entity.getTime())
                .comment(entity.getComment())
                .difficulty(entity.getDifficulty())
                .totalTime(entity.getTotalTime())
                .rating(entity.getRating())
                .build();
    }

    @Override
    public TourLogEntity toEntity(TourLog tourLog) {
        return TourLogEntity.builder()
                .id(tourLog.getId())
                //.tourId(tourLog.getTourId())
                .date(tourLog.getDate())
                .time(tourLog.getTime())
                .comment(tourLog.getComment())
                .difficulty(tourLog.getDifficulty())
                .totalTime(tourLog.getTotalTime())
                .rating(tourLog.getRating())
                .build();

    }
}
