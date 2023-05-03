package at.fhtw.swen2.tutorial.presentation.view;

import at.fhtw.swen2.tutorial.presentation.viewmodel.RouteListViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.mapquest.client.MapQuestClient;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    private ListView<String> tourList;

    @Autowired
    RouteListViewModel routeListViewModel;


    @Override
    public void initialize(URL location, ResourceBundle rb){
        tourList.itemsProperty().bindBidirectional(routeListViewModel.tourListPropertyProperty());

        tourList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //check if left click
                if(event.getButton()== MouseButton.PRIMARY && event.getClickCount()==1){

                    String selectedTour = tourList.getSelectionModel().getSelectedItem();
                }
            }
        });
    }


    private void displayRoute(String selectedTour) {
        String [] logData = selectedTour.split(",");
        String startLocation = logData[0];
        String endLocation = logData[1];

        //MapQuestClient
    }

}
