package at.fhtw.swen2.tutorial.presentation.view;

import at.fhtw.swen2.tutorial.presentation.viewmodel.NewTourLogViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;


@Component
public class TourLogController implements Initializable {

    @Autowired
    NewTourLogViewModel tourLogViewModel;

    @FXML
    private TextField tourid;

    @FXML
    private TextField date;

    @FXML
    private TextField time;

    @FXML
    private TextField comment;

    @FXML
    private TextField difficulty;

    @FXML
    private TextField totalTime;

    @FXML
    private TextField rating;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //tourid.textProperty().bindBidirectional(NewTourLogViewModel());

    }
}
