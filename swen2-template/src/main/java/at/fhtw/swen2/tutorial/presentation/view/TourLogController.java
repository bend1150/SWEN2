package at.fhtw.swen2.tutorial.presentation.view;

import at.fhtw.swen2.tutorial.presentation.viewmodel.NewTourLogViewModel;
import at.fhtw.swen2.tutorial.service.model.Tour;
import at.fhtw.swen2.tutorial.service.model.TourLog;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.util.Callback;
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


        //setzt tourLogList neu, sodass nur die Namen ausgegeben werden, obwohl in der TourLogList Objekte stecken
        tourLogList.setCellFactory(new Callback<ListView<TourLog>, ListCell<TourLog>>() {
            @Override
            public ListCell<TourLog> call(ListView<TourLog> param) {
                return new ListCell<>(){
                    @Override
                    public void updateItem(TourLog tourLog, boolean empty){
                        super.updateItem(tourLog, empty);
                        if(empty || tourLog == null){
                            setText(null);
                        } else {
                            setText(tourLog.getDate());
                        }
                    }
                };
            }
        });
    }
    public void tourLogSubmitButtonAction(){
        //pack the bound data from the viewmodel into a dto and ship to service
        newTourLogViewModel.saveTourLog();
    }

}
