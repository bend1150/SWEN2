package at.fhtw.swen2.tutorial.presentation.viewmodel;

import at.fhtw.swen2.tutorial.service.TourLogService;
import at.fhtw.swen2.tutorial.service.model.TourLog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TourLogInfoViewModel {
    @Autowired
    TourLogService tourLogService;
    public ObservableList<String> tourLogNames = FXCollections.observableArrayList();

    private List<TourLog> tourLogList = new ArrayList<>();

    public void showTourLogs(int tourId){
        tourLogNames.clear();
        tourLogList = tourLogService.getTourLogListByTourId(((long) tourId));
        for (TourLog t: tourLogList
             ) {
            tourLogNames.add(t.getDate());
        }
    }
}
