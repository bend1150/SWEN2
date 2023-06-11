package at.fhtw.swen2.tutorial.presentation.viewmodel;

import at.fhtw.swen2.tutorial.presentation.view.RouteListController;
import at.fhtw.swen2.tutorial.service.TourLogService;
import at.fhtw.swen2.tutorial.service.model.Tour;
import at.fhtw.swen2.tutorial.service.model.TourLog;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.lang.Float.parseFloat;

@Component
@Data
public class TourLogInfoViewModel {
    private static final Logger logger = LoggerFactory.getLogger(RouteListController.class);

    @Autowired
    TourLogService tourLogService;

    private SimpleStringProperty date = new SimpleStringProperty();
    private SimpleStringProperty time = new SimpleStringProperty();
    private SimpleStringProperty comment = new SimpleStringProperty();
    private SimpleStringProperty difficulty = new SimpleStringProperty();
    private SimpleStringProperty totalTime = new SimpleStringProperty();
    private SimpleStringProperty rating = new SimpleStringProperty();


    public ObservableList<String> tourLogNames = FXCollections.observableArrayList();

    public void setTourLogList(List<TourLog> tourLogList) {
        this.tourLogList = tourLogList;
    }

    private List<TourLog> tourLogList = new ArrayList<>();

    private int selectedLogIndex = -1;
    private Tour selectedTour;

    public void showTourLogs(int tourId){           //tourLog wird angezeigt
        tourLogNames.clear();
        tourLogNames.add("Add new TourLog");

        tourLogList = tourLogService.getTourLogListByTourId(((long) tourId));
        for (TourLog t: tourLogList
             ) {
            tourLogNames.add(t.getDate());
        }
    }

    public void updateSelectedLog(){
        if(getTime() == "" || getTotalTime()==""|| getRating()=="" || getTime() == null || getTotalTime() == null || getRating() == null){
            logger.error("Some fields have not been properly filled out");
            return;  //damit nicht erstellt wird
        }

        try{
            float floatTime = parseFloat(getTime());
            float floatTotalTime = parseFloat(getTotalTime());
            float floatRating = parseFloat(getRating());
        }
        catch(Exception ex){
            logger.error("total time, time and rating must be numbers");
            return; // wieder falscher input
        }


        try{
            TourLog updatedLog = TourLog.builder()
                    .tourId(selectedTour.getId())
                    .date(getDate())
                    .time(Float.parseFloat(getTime()))
                    .comment(getComment())
                    .difficulty(getDifficulty())
                    .totalTime(Float.parseFloat(getTotalTime()))
                    .rating(Float.parseFloat(getRating()))
                    .build();

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<TourLog>> violations = validator.validate(updatedLog);       //schaut notations nach im model -> @NotNull oder @NotBlank

            if(!violations.isEmpty()){
                logger.error("Some fields have not been properly filled out");
                return;  //damit nicht erstellt wird
            }


            if(this.selectedLogIndex == -1){                    //neuer Tour Log erstellt
                logger.info("saving to tour nr: " + selectedTour.getId());
                tourLogService.addNew(updatedLog);
            }
            else {                                              //tour update
                logger.info("Updating to tour nr: " + selectedTour.getId());
                updatedLog.setId(tourLogList.get(selectedLogIndex).getId());
                logger.info("With ID: " + updatedLog.getId());
                tourLogService.updateByTourId(updatedLog);
            }
        }
        catch(Exception ex){
            logger.error("Error updating tourlog");
            logger.error("ERROR: ", ex);
        }


        //tourIds and indexes are one off or something for some reason
        showTourLogs(Math.toIntExact(selectedTour.getId()));
    }

    public void updateTextBoxes(int index){
        if(index == -1){
            clearTextBoxes();
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

    public void clearTextBoxes(){
        setDate("");
        setTime("");
        setComment("");
        setDifficulty("");
        setTotalTime("");
        setRating("");
    }

    public void deleteSelectedTourLog(){
        if(selectedLogIndex == -1 || selectedLogIndex > tourLogList.size()-1){
            return;
        }

        int index = this.selectedLogIndex;

        TourLog deletedLog = tourLogList.get(index);

        try{
            logger.info("Deleting TourLog with ID: " + deletedLog.getId());
            tourLogService.deleteById(deletedLog.getId());

            showTourLogs(Math.toIntExact(selectedTour.getId()));

            this.selectedLogIndex = -1;
            updateTextBoxes(selectedLogIndex);
        }
        catch(Exception ex){
            logger.error("Error deleting tour");
            logger.error("Error CODE:",ex);
        }

    }

    public void deleteAllTourLogs(){
        tourLogService.deleteByTourId(((long) this.selectedTour.getId()));
        tourLogNames.clear();
        clearTextBoxes();
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
