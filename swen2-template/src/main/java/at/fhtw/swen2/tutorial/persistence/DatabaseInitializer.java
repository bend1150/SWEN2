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
        TourLog test1 = TourLog.builder()
                .comment("Hallo1")
                .date("test1")
                .tourId(0)
                .difficulty("high")
                .time(123)
                .totalTime(13)
                .rating(1)
                .build();

        TourLog test2 = TourLog.builder()
                .comment("Hallo2")
                .date("test2")
                .tourId(0)
                .difficulty("high")
                .time(123)
                .totalTime(13)
                .rating(1)
                .build();

        TourLog test3 = TourLog.builder()
                .comment("Hallo3")
                .date("test3")
                .tourId(1)
                .difficulty("high")
                .time(123)
                .totalTime(13)
                .rating(1)
                .build();

        tourLogService.addNew(test1);
        tourLogService.addNew(test2);
        tourLogService.addNew(test3);
    }
}
