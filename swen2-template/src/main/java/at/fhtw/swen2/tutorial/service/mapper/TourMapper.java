package at.fhtw.swen2.tutorial.service.mapper;

import at.fhtw.swen2.tutorial.persistence.entities.TourEntity;
import at.fhtw.swen2.tutorial.service.model.Tour;
import org.springframework.stereotype.Component;

@Component
public class TourMapper extends AbstractMapper<TourEntity, Tour> {

    @Override
    public Tour fromEntity(TourEntity entity) {
        return Tour.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .origin(entity.getOrigin())
                .destination(entity.getDestination())
                .transportType((entity.getTransportType()))
                .distance(entity.getDistance())
                .time(entity.getTime())
                //.routeInformation(entity.getRouteInformation())
                .build();
    }

    @Override
    public TourEntity toEntity(Tour tour) {
        return TourEntity.builder()
                .id(tour.getId())
                .name(tour.getName())
                .description(tour.getDescription())
                .origin(tour.getOrigin())
                .destination(tour.getDestination())
                .transportType(tour.getTransportType())
                .distance(tour.getDistance())
                .time(tour.getTime())
                //.routeInformation(tour.getRouteInformation())
                .build();
    }

}
