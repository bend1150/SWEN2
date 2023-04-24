package at.fhtw.swen2.tutorial.presentation.view;

import at.fhtw.swen2.tutorial.presentation.viewmodel.RouteListViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.script.Bindings;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class RouteListController implements Initializable {
    @FXML
    private ListView<String> tourList;

    @Autowired
    RouteListViewModel routeListViewModel;


    @Override
    public void initialize(URL location, ResourceBundle rb){
        tourList.itemsProperty().bindBidirectional(routeListViewModel.tourListPropertyProperty());
    }

}
