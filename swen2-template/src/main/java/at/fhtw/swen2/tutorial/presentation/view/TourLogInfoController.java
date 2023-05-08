package at.fhtw.swen2.tutorial.presentation.view;

import at.fhtw.swen2.tutorial.presentation.viewmodel.TourLogInfoViewModel;
import at.fhtw.swen2.tutorial.service.TourLogService;
import at.fhtw.swen2.tutorial.service.model.TourLog;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class TourLogInfoController implements Initializable {
    @FXML
    TextField dateTextBox;
    @FXML
    TextField timeTextBox;
    @FXML
    TextArea commentTextBox;
    @FXML
    TextField totalTimeTextBox;
    @FXML
    TextField difficultyTextBox;
    @FXML
    TextField ratingTextBox;

    @FXML
    ComboBox tourLogComboBox;
    @Autowired
    TourLogInfoViewModel tourLogInfoViewModel;

    @Autowired
    TourLogService tourLogService;

    @Override
    public void initialize(URL location, ResourceBundle rb){
        //event listener to LogComboBox
        tourLogComboBox.getSelectionModel().selectedIndexProperty().addListener((options, oldValue, newValue) -> {
            if(tourLogComboBox.getSelectionModel().getSelectedItem() == null){
                return;
            }

            if(tourLogComboBox.getSelectionModel().getSelectedItem().equals("...")){
                //empty the data, create new tourLog on buttonClick
            }else {
                //index -1, because the first index is initialized to ...
                tourLogInfoViewModel.printNameOfSelected(tourLogComboBox.getSelectionModel().getSelectedIndex() - 1);
            }

        });


        tourLogComboBox.itemsProperty().set(tourLogInfoViewModel.tourLogNames);

        dateTextBox.textProperty().bindBidirectional(tourLogInfoViewModel.dateProperty());
        timeTextBox.textProperty().bindBidirectional(tourLogInfoViewModel.timeProperty());
        commentTextBox.textProperty().bindBidirectional(tourLogInfoViewModel.commentProperty());
        difficultyTextBox.textProperty().bindBidirectional(tourLogInfoViewModel.difficultyProperty());
        totalTimeTextBox.textProperty().bindBidirectional(tourLogInfoViewModel.totalTimeProperty());
        ratingTextBox.textProperty().bindBidirectional(tourLogInfoViewModel.ratingProperty());

    }

}
