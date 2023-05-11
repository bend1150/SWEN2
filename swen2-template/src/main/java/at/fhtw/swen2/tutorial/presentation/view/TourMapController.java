package at.fhtw.swen2.tutorial.presentation.view;

import at.fhtw.swen2.tutorial.presentation.viewmodel.TourMapViewModel;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class TourMapController implements Initializable {

    @FXML
    ImageView mapImageView;
    @Autowired
    TourMapViewModel tourMapViewModel;

    @Override
    public void initialize(URL location, ResourceBundle rb){

    }

}
