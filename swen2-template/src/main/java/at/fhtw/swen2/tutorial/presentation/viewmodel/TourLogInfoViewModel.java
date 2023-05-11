package at.fhtw.swen2.tutorial.presentation.viewmodel;

import at.fhtw.swen2.tutorial.service.TourLogService;
import at.fhtw.swen2.tutorial.service.model.Tour;
import at.fhtw.swen2.tutorial.service.model.TourLog;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class TourLogInfoViewModel {
    @Autowired
    TourLogService tourLogService;

    private SimpleStringProperty date = new SimpleStringProperty();
    private SimpleStringProperty time = new SimpleStringProperty();
    private SimpleStringProperty comment = new SimpleStringProperty();
    private SimpleStringProperty difficulty = new SimpleStringProperty();
    private SimpleStringProperty totalTime = new SimpleStringProperty();
    private SimpleStringProperty rating = new SimpleStringProperty();


    public ObservableList<String> tourLogNames = FXCollections.observableArrayList();

    private List<TourLog> tourLogList = new ArrayList<>();

    private int selectedLogIndex = -1;
    private Tour selectedTour;

    public void showTourLogs(int tourId){
        tourLogNames.clear();
        tourLogNames.add("...");

        tourLogList = tourLogService.getTourLogListByTourId(((long) tourId));
        for (TourLog t: tourLogList
             ) {
            tourLogNames.add(t.getDate());
        }
    }

    public void updateSelectedLog(){
        TourLog updatedLog = TourLog.builder()
                .tourId(selectedTour.getId())
                .date(getDate())
                .time(Float.parseFloat(getTime()))
                .comment(getComment())
                .difficulty(getDifficulty())
                .totalTime(Float.parseFloat(getTotalTime()))
                .rating(Float.parseFloat(getRating()))
                .build();


        if(this.selectedLogIndex == -1){
            System.out.println("saving to tour nr: " + selectedTour.getId());
            tourLogService.addNew(updatedLog);
        }
        else {
            System.out.println("Updating to tour nr: " + selectedTour.getId());
            updatedLog.setId(tourLogList.get(selectedLogIndex).getId());
            System.out.println("With ID: " + updatedLog.getId());
            tourLogService.updateByTourId(updatedLog);
        }

        //tourIds and indexes are one off or something for some reason
        showTourLogs(Math.toIntExact(selectedTour.getId()));
    }

    public void updateTextBoxes(int index){
        if(index == -1){
            setDate("");
            setTime("");
            setComment("");
            setDifficulty("");
            setTotalTime("");
            setRating("");
            return;
        }

        TourLog tourLog = tourLogList.get(index);

        setDate(tourLog.getDate());
        setTime(Float.toString(tourLog.getTime()));
        setComment(tourLog.getComment());
        setDifficulty(tourLog.getDifficulty());
        setTotalTime(Float.toString(tourLog.getTotalTime()));
        setRating(Float.toString(tourLog.getRating()));
    }

    public void deleteSelectedTourLog(){
        if(selectedLogIndex == -1 || selectedLogIndex > tourLogList.size()-1){
            return;
        }

        int index = this.selectedLogIndex;

        TourLog deletedLog = tourLogList.get(index);

        tourLogService.deleteById(deletedLog.getId());
        System.out.println("Deleted TourLog with ID: " + deletedLog.getId());

        showTourLogs(Math.toIntExact(selectedTour.getId()));

        this.selectedLogIndex = -1;
        updateTextBoxes(selectedLogIndex);
    }

    public void deleteAllTourLogs(){
        tourLogService.deleteByTourId(((long) this.selectedTour.getId()));
        showTourLogs(-1);
    }


    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }


    public String getTime() {
        return time.get();
    }

    public StringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public String getComment() {
        return comment.get();
    }

    public StringProperty commentProperty() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

    public String getDifficulty() {
        return difficulty.get();
    }

    public StringProperty difficultyProperty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty.set(difficulty);
    }

    public String getTotalTime() {
        return totalTime.get();
    }

    public StringProperty totalTimeProperty() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime.set(totalTime);
    }

    public String getRating() {
        return rating.get();
    }

    public StringProperty ratingProperty() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating.set(rating);
    }

}
