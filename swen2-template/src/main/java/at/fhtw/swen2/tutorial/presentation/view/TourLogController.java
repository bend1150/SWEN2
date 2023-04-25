package at.fhtw.swen2.tutorial.presentation.view;

import at.fhtw.swen2.tutorial.presentation.viewmodel.NewTourLogViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;


@Component
public class TourLogController implements Initializable {

    @Autowired
    NewTourLogViewModel newTourLogViewModel;

    @FXML
    private TextField dateTextBox;

    @FXML
    private TextField timeTextBox;

    @FXML
    private TextField commentTextBox;

    @FXML
    private TextField difficultyTextBox;

    @FXML
    private TextField totalTimeTextBox;

    @FXML
    private TextField ratingTextBox;

    @FXML
    private ListView tourLogList;



    @Override
    public void initialize(URL location, ResourceBundle rb) {

        dateTextBox.textProperty().bindBidirectional(newTourLogViewModel.dateProperty());
        timeTextBox.textProperty().bindBidirectional(newTourLogViewModel.timeProperty());
        commentTextBox.textProperty().bindBidirectional(newTourLogViewModel.commentProperty());
        difficultyTextBox.textProperty().bindBidirectional(newTourLogViewModel.difficultyProperty());
        totalTimeTextBox.textProperty().bindBidirectional(newTourLogViewModel.totalTimeProperty());
        ratingTextBox.textProperty().bindBidirectional(newTourLogViewModel.ratingProperty());

        tourLogList.itemsProperty().bindBidirectional(newTourLogViewModel.logListPropertyProperty());
    }
    public void tourLogSubmitButtonAction(){
        //pack the bound data from the viewmodel into a dto and ship to service
        newTourLogViewModel.saveTourLog();
    }

}
