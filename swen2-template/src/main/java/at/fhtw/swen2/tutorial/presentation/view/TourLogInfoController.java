package at.fhtw.swen2.tutorial.presentation.view;

import at.fhtw.swen2.tutorial.presentation.viewmodel.TourLogInfoViewModel;
import at.fhtw.swen2.tutorial.service.TourLogService;
import at.fhtw.swen2.tutorial.service.model.TourLog;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class TourLogInfoController implements Initializable {

    @FXML
    ComboBox tourLogComboBox;
    @Autowired
    TourLogInfoViewModel tourLogInfoViewModel;

    @Autowired
    TourLogService tourLogService;

    @Override
    public void initialize(URL location, ResourceBundle rb){
        tourLogComboBox.itemsProperty().set(tourLogInfoViewModel.tourLogNames);


    }
}
