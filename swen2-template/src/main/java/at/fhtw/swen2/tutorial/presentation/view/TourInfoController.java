package at.fhtw.swen2.tutorial.presentation.view;

import at.fhtw.swen2.tutorial.presentation.viewmodel.TourInfoViewModel;
import at.fhtw.swen2.tutorial.service.model.Tour;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class TourInfoController  implements Initializable {
        @FXML
        TextField nameField;
        @FXML
        TextField originField;
        @FXML
        TextField destinationField;
        @FXML
        TextField transportField;
        @FXML
        TextField distanceField;
        @FXML
        TextArea descriptionField;
        @FXML
        TextField timeField;

        @Autowired
        TourInfoViewModel tourInfoViewModel;

        @Override
        public void initialize(URL location, ResourceBundle rb){
                nameField.textProperty().bindBidirectional(tourInfoViewModel.nameProperty());
                descriptionField.textProperty().bindBidirectional(tourInfoViewModel.descriptionProperty());
                originField.textProperty().bindBidirectional(tourInfoViewModel.originProperty());
                destinationField.textProperty().bindBidirectional(tourInfoViewModel.destinationProperty());
                transportField.textProperty().bindBidirectional(tourInfoViewModel.transportProperty());
                distanceField.textProperty().bindBidirectional(tourInfoViewModel.distanceProperty());
                timeField.textProperty().bindBidirectional(tourInfoViewModel.timeProperty());

        }


}
