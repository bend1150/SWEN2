package at.fhtw.swen2.tutorial.presentation.view;

import at.fhtw.swen2.tutorial.presentation.viewmodel.EditRouteViewModel;
import at.fhtw.swen2.tutorial.presentation.viewmodel.RouteListViewModel;
import at.fhtw.swen2.tutorial.service.model.Tour;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.script.Bindings;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class RouteListController implements Initializable {
    @FXML
    public ListView<Tour> tourList;

    @Autowired
    RouteListViewModel routeListViewModel;

    @Autowired
    TourController tourController;

    @Autowired
    TourEditController tourEditController;

    @Autowired
    EditRouteViewModel editRouteViewModel;

    public void addRoute(){
        //pack the bound data from the viewmodel into a dto and ship to service
        try{
            // Load the FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TourCreator.fxml"));
            fxmlLoader.setController(tourController);
            Parent root = fxmlLoader.load();

            // Create a new stage and set the scene
            Stage dialogStage = new Stage();
            dialogStage.setScene(new Scene(root));

            // Set the modality to WINDOW_MODAL
            dialogStage.initModality(Modality.APPLICATION_MODAL);

            // Set the title and show the stage
            dialogStage.setTitle("Custom Dialog");
            dialogStage.showAndWait();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }

    public void deleteRoute(){
        int selectedRouteIndex = routeListViewModel.getListIndex();
        routeListViewModel.deleteSelected(selectedRouteIndex);
    }

    public void editRoute(){
        int selectedRouteIndex = routeListViewModel.getListIndex();
        if(selectedRouteIndex == -1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Select a Tour to edit first");
            alert.showAndWait();
            return;
        }

        editRouteViewModel.setProperties(tourList.getSelectionModel().getSelectedIndex());

        try{
            // Load the FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TourEditor.fxml"));
            fxmlLoader.setController(tourEditController);
            Parent root = fxmlLoader.load();

            // Create a new stage and set the scene
            Stage dialogStage = new Stage();
            dialogStage.setScene(new Scene(root));

            // Set the modality to WINDOW_MODAL
            dialogStage.initModality(Modality.APPLICATION_MODAL);

            // Set the title and show the stage
            dialogStage.setTitle("Custom Dialog");
            dialogStage.showAndWait();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }




    @Override
    public void initialize(URL location, ResourceBundle rb){
        tourList.itemsProperty().bindBidirectional(routeListViewModel.tourListPropertyProperty());

        tourList.getSelectionModel().selectedIndexProperty().addListener(
            new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    int index = tourList.getSelectionModel().getSelectedIndex();
                    routeListViewModel.updateSelectedIndex(index);
                }
            }
        );

        //setzt tourList neu, sodass nur die Namen ausgegeben werden, obwohl in der TourList die Tour Objekte stecken
        tourList.setCellFactory(new Callback<ListView<Tour>, ListCell<Tour>>() {
            @Override
            public ListCell<Tour> call(ListView<Tour> param) {
                return new ListCell<>(){
                    @Override
                    public void updateItem(Tour tour, boolean empty){
                        super.updateItem(tour, empty);
                        if(empty || tour == null){
                            setText(null);
                        } else {
                            setText(tour.getName());
                        }
                    }
                };
            }
        });
    }

}
