package at.fhtw.swen2.tutorial.presentation.view;

import at.fhtw.swen2.tutorial.presentation.viewmodel.EditRouteViewModel;
import at.fhtw.swen2.tutorial.presentation.viewmodel.NewRouteViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class TourEditController implements Initializable {

    @Autowired
    EditRouteViewModel editRouteViewModel;

    @Autowired
    RouteListController routeListController;

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

    @FXML
    private Button submitButton;

    @Override
    public void initialize(URL location, ResourceBundle rb){
        nameTextBox.textProperty().bindBidirectional(editRouteViewModel.nameProperty());
        descriptionTextBox.textProperty().bindBidirectional(editRouteViewModel.descriptionProperty());
        originTextBox.textProperty().bindBidirectional(editRouteViewModel.originProperty());
        destinationTextBox.textProperty().bindBidirectional(editRouteViewModel.destinationProperty());
        transportTextBox.textProperty().bindBidirectional(editRouteViewModel.transportProperty());
        distanceTextBox.textProperty().bindBidirectional(editRouteViewModel.distanceProperty());
        timeTextBox.textProperty().bindBidirectional(editRouteViewModel.timeProperty());

    }

    public void submitButtonAction(){
        editRouteViewModel.updateRoute(routeListController.tourList.getSelectionModel().getSelectedItem());
    }


}
