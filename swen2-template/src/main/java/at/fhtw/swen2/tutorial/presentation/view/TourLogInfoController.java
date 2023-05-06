package at.fhtw.swen2.tutorial.presentation.view;

import at.fhtw.swen2.tutorial.presentation.viewmodel.TourLogInfoViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

public class TourLogInfoController implements Initializable {

    @FXML
    ComboBox tourLogComboBox;
    @Autowired
    TourLogInfoViewModel tourLogInfoViewModel;

    @Override
    public void initialize(URL location, ResourceBundle rb){
        tourLogInfoViewModel.test();
    }
}
