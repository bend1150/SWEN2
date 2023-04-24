package at.fhtw.swen2.tutorial.presentation.view;

import at.fhtw.swen2.tutorial.presentation.viewmodel.NewRouteViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class TourController implements Initializable {

    @Autowired
    NewRouteViewModel newRouteViewModel;

    @FXML
    private TextField nameTextBox;
    @FXML
    private TextField descriptionTextBox;
    @FXML
    private TextField originTextBox;
    @FXML
    private TextField destinationTextBox;
    @FXML
    private TextField transportTextBox;
    @FXML
    private TextField distanceTextBox;
    @FXML
    private TextField timeTextBox;

    @Override
    public void initialize(URL location, ResourceBundle rb){
        nameTextBox.textProperty().bindBidirectional(newRouteViewModel.nameProperty());
        descriptionTextBox.textProperty().bindBidirectional(newRouteViewModel.descriptionProperty());
        originTextBox.textProperty().bindBidirectional(newRouteViewModel.originProperty());
        destinationTextBox.textProperty().bindBidirectional(newRouteViewModel.destinationProperty());
        transportTextBox.textProperty().bindBidirectional(newRouteViewModel.transportProperty());
        distanceTextBox.textProperty().bindBidirectional(newRouteViewModel.distanceProperty());
        timeTextBox.textProperty().bindBidirectional(newRouteViewModel.timeProperty());
    }

    public void submitButtonAction(){
        //pack the bound data from the viewmodel into a dto and ship to service
        newRouteViewModel.saveRoute();
    }


}
