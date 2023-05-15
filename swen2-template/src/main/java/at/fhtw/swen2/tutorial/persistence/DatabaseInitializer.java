package at.fhtw.swen2.tutorial.persistence;

import at.fhtw.swen2.tutorial.persistence.entities.PersonEntity;
import at.fhtw.swen2.tutorial.persistence.repositories.PersonRepository;
import at.fhtw.swen2.tutorial.persistence.repositories.RouteRepository;
import at.fhtw.swen2.tutorial.service.TourLogService;
import at.fhtw.swen2.tutorial.service.model.TourLog;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseInitializer implements InitializingBean {

    @Autowired
    private TourLogService tourLogService;

    @Override
    public void afterPropertiesSet() throws Exception {
        TourLog test = TourLog.builder()
                .comment("Hallo")
                .date("test")
                .tourId(1)
                .difficulty("high")
                .time(123f)
                .totalTime(13f)
                .rating(1f)
                .build();

        tourLogService.addNew(test);
    }
}
