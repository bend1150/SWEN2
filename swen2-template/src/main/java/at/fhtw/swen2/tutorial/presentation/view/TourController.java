package at.fhtw.swen2.tutorial.presentation.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Text;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class TourController implements Initializable {

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
        System.out.println("Test");
    }

    public void submitButtonAction(){
        System.out.println("Hi");
    }

}
