package at.fhtw.swen2.tutorial.service.impl;

import at.fhtw.swen2.tutorial.persistence.entities.TourEntity;
import at.fhtw.swen2.tutorial.persistence.entities.TourLogEntity;
import at.fhtw.swen2.tutorial.persistence.repositories.RouteRepository;
import at.fhtw.swen2.tutorial.persistence.repositories.TourLogRepository;
import at.fhtw.swen2.tutorial.service.RouteService;
import at.fhtw.swen2.tutorial.service.TourLogService;
import at.fhtw.swen2.tutorial.service.mapper.TourLogMapper;
import at.fhtw.swen2.tutorial.service.PersonService;
import at.fhtw.swen2.tutorial.service.mapper.TourMapper;
import at.fhtw.swen2.tutorial.service.model.Person;
import at.fhtw.swen2.tutorial.service.model.TourLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.RouteMatcher;

import java.util.List;

@Service
@Transactional
public class TourLogServiceImpl implements TourLogService {

    @Autowired
    private TourLogRepository tourLogRepository;

    @Autowired
    private TourLogMapper tourLogMapper;



    @Override
    public List<TourLog> getTourLogList() {     //get list from database using tourLogRepository

        return tourLogMapper.fromEntity(tourLogRepository.findAll());
    }

    @Override
    public TourLog addNew(TourLog tourLog) {
        if (tourLog == null){
            return null;
        }
        TourLogEntity entity = tourLogRepository.save(tourLogMapper.toEntity(tourLog));
        return tourLogMapper.fromEntity(entity);
    }
}
