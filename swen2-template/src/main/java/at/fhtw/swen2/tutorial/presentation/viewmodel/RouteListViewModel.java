package at.fhtw.swen2.tutorial.presentation.viewmodel;

import at.fhtw.swen2.tutorial.service.model.Tour;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Component;
import javafx.scene.image.Image;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static javafx.collections.FXCollections.observableArrayList;

@Component
public class RouteListViewModel {


    public ObservableList<String> tourList = FXCollections.observableArrayList();
    private SimpleListProperty tourListProperty = new SimpleListProperty(tourList);
    private ObjectProperty<Image> tourImageProperty = new SimpleObjectProperty<>();
    public void setTourImageProperty(Image image) {
        tourImageProperty.set(image);
    }

    public ObjectProperty<Image> tourImageProperty() {
        return tourImageProperty;
    }

    private List<Tour> tours;   //ben




    public List<Tour> getTours(){   //ben
        return tours;
    }


    public Object getTourListProperty() {
        return tourListProperty.get();
    }



    public SimpleListProperty tourListPropertyProperty() {
        return tourListProperty;
    }

    public ObservableList<String> getTourList() {
        return tourList;
    }

    public void setTourList(ObservableList<String> tourList) {
        this.tourList = tourList;
    }


   /* public void updateTourList(List<Tour> tours){       // b4 changes
        tourList.clear();
        List<String> tourNames = new ArrayList<>();
        for (Tour tour: tours
             ) {
            tourNames.add(tour.getName());
        }
        tourList.setAll(tourNames);
        //update table
    }*/
   public void updateTourList(List<Tour> tours){
       this.tours = tours;
       tourList.clear();
       List<String> tourNames = new ArrayList<>();
       for (Tour tour: tours) {
           tourNames.add(tour.getName());
       }
       tourList.setAll(tourNames);
       //update table
   }

    public void printTourDetails(Tour tour){ //test
        System.out.println("Origin: " + tour.getOrigin());
        System.out.println(("Dest: " + tour.getDestination()));
    }
}
