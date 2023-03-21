package at.fhtw.swen2.tutorial.presentation.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class RouteListController implements Initializable {
    @FXML
    ListView routeList;


    @Override
    public void initialize(URL location, ResourceBundle rb){

    }
}
