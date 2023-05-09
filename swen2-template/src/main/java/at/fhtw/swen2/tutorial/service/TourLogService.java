package at.fhtw.swen2.tutorial.service;

import at.fhtw.swen2.tutorial.service.model.Tour;
import at.fhtw.swen2.tutorial.service.model.TourLog;

import java.util.List;

public interface TourLogService {

    List<TourLog> getTourLogList();

    TourLog addNew(TourLog tourLog);

    List<TourLog> getTourLogListByTourId(Long tourId);

    void updateByTourId(TourLog updatedTourLog);
}
