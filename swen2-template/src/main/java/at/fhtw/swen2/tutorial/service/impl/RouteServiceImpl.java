package at.fhtw.swen2.tutorial.service.impl;

import at.fhtw.swen2.tutorial.persistence.entities.TourEntity;
import at.fhtw.swen2.tutorial.persistence.repositories.RouteRepository;
import at.fhtw.swen2.tutorial.service.RouteService;
import at.fhtw.swen2.tutorial.service.mapper.TourMapper;
import at.fhtw.swen2.tutorial.service.PersonService;
import at.fhtw.swen2.tutorial.service.model.Person;
import at.fhtw.swen2.tutorial.service.model.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.RouteMatcher;

import java.util.List;

@Service
@Transactional
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private TourMapper tourMapper;

    @Override
    public List<Tour> getRouteList() {
        return tourMapper.fromEntity(routeRepository.findAll());
    }

    @Override
    public Tour addNew(Tour tour) {
        if (tour == null){
            return null;
        }
        TourEntity entity = routeRepository.save(tourMapper.toEntity(tour));
        return tourMapper.fromEntity(entity);
    }

    @Override
    public Tour getByName(String name) {
        return null;
    }


    @Override
    public void deleteById(long id){
        routeRepository.deleteById(id);
    }

    @Override
    public Tour getById(long id){
        return tourMapper.fromEntity(routeRepository.getById(id));
    }

    @Override
    public void update(Tour tour){
        routeRepository.save(tourMapper.toEntity(tour));
    }
}
