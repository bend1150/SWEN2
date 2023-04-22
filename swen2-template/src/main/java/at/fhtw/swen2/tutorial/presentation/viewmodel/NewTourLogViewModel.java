package at.fhtw.swen2.tutorial.presentation.viewmodel;

import at.fhtw.swen2.tutorial.service.TourLogService;
import at.fhtw.swen2.tutorial.service.model.TourLog;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.Float.parseFloat;

@Component
public class NewTourLogViewModel {

    @Autowired
    private TourLogService tourLogService;



    private SimpleStringProperty tourId = new SimpleStringProperty();
    private SimpleStringProperty date = new SimpleStringProperty();
    private SimpleStringProperty time = new SimpleStringProperty();
    private SimpleStringProperty comment = new SimpleStringProperty();
    private SimpleStringProperty difficulty = new SimpleStringProperty();
    private SimpleStringProperty totalTime = new SimpleStringProperty();
    private SimpleStringProperty rating = new SimpleStringProperty();


    public String getTourId() {
        return tourId.get();
    }

    public SimpleStringProperty tourIdProperty() {
        return tourId;
    }

    public void setTourId(String tourId) {
        this.tourId.set(tourId);
    }
    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public String getComment() {
        return comment.get();
    }

    public SimpleStringProperty commentProperty() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

    public String getDifficulty() {
        return difficulty.get();
    }

    public SimpleStringProperty difficultyProperty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty.set(difficulty);
    }

    public String getTotalTime() {
        return totalTime.get();
    }

    public SimpleStringProperty totalTimeProperty() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime.set(totalTime);
    }

    public String getRating() {
        return rating.get();
    }

    public SimpleStringProperty ratingProperty() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating.set(rating);
    }



    private TourLog tourLog;





    public void saveTourLog(){
        tourLog = TourLog.builder()
                .tourId(Long.parseLong(getTourId()))
                .date(getDate())
                .time(parseFloat(getTime()))
                .comment(getComment())
                .difficulty(getDifficulty())
                .totalTime(parseFloat(getTotalTime()))
                .rating(parseFloat(getRating()))

                //.routeInformation(null)
                .build();

        tourLogService.addNew(tourLog);

        List<TourLog> tourLogs = tourLogService.getTourLogList();
        for (TourLog t: tourLogs
        ) {
            System.out.println(t.getTourId());
            System.out.println(t.getDate());
            System.out.println(t.getTime());
            System.out.println(t.getComment());
            System.out.println(t.getDifficulty());
            System.out.println(t.getTotalTime());
            System.out.println(t.getRating());

        }
    }
}
