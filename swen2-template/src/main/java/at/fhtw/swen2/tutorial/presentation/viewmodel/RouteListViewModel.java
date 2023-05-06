package at.fhtw.swen2.tutorial.presentation.viewmodel;

import at.fhtw.swen2.tutorial.presentation.view.RouteListController;
import at.fhtw.swen2.tutorial.service.RouteService;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import lombok.Data;
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
@Data
public class RouteListViewModel {

    @Autowired
    RouteService routeService;

    @Autowired
    TourInfoViewModel tourInfoViewModel;

    @Autowired
    TourLogInfoViewModel tourLogInfoViewModel;

    //replicates the list on the left of the screen
    public ObservableList<Tour> tourList = FXCollections.observableArrayList();
    private SimpleListProperty tourListProperty = new SimpleListProperty(tourList);

    private int listIndex = -1;

    public Object getTourListProperty() {
        return tourListProperty.get();
    }


    public SimpleListProperty tourListPropertyProperty() {
        return tourListProperty;
    }

    public ObservableList<Tour> getTourList() {
        return tourList;
    }

    public void setTourList(ObservableList<Tour> tourList) {
        this.tourList = tourList;
    }


    public void addTourList(Tour tour){
        tourList.add(tour);
    }


    public void deleteSelected(int selectedIndex){
        System.out.println("Deleting index: " + selectedIndex);
        if(selectedIndex == -1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Select a Tour to delete first");
            alert.showAndWait();
            return;
        }
        long deletedId = tourList.get(selectedIndex).getId();

        routeService.deleteById(deletedId); //delete

        updateTourList();

        updateSelectedIndex(-1);
        tourInfoViewModel.clearInfo();
    }

    public void updateTourList(){
        List<Tour> newTourList = routeService.getRouteList();

        tourList.clear();
        tourList.setAll(newTourList);

        updateSelectedIndex(-1);
    }

    public void updateSelectedIndex(int index){
        listIndex = index;

        //if something was selected --> fetch the data
        if(listIndex == -1){
            return;
        }

        //get the Tour info into the tour Info/The map
        tourInfoViewModel.updateInfo(tourList.get(index));

        //get the tourLog info into the tourLog table
        tourLogInfoViewModel.showTourLogs(index);
    }
}
