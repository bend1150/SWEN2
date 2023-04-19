package at.fhtw.swen2.tutorial.presentation.viewmodel;

import at.fhtw.swen2.tutorial.presentation.view.RouteListController;
import at.fhtw.swen2.tutorial.service.model.Tour;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Float.parseFloat;
import static javafx.collections.FXCollections.observableArrayList;

@Component
public class RouteListViewModel {

    public ObservableList<String> tourList = FXCollections.observableArrayList();
    private SimpleListProperty tourListProperty = new SimpleListProperty(tourList);


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


    public void updateTourList(List<Tour> tours){
        tourList.clear();
        List<String> tourNames = new ArrayList<>();
        for (Tour tour: tours
             ) {
            tourNames.add(tour.getName());
        }

        //update table
    }
}
