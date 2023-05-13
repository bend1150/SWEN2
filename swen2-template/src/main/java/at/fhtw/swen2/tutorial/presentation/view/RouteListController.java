package at.fhtw.swen2.tutorial.presentation.view;
import at.fhtw.swen2.tutorial.service.MapQuestService;
import at.fhtw.swen2.tutorial.service.model.Tour;

import at.fhtw.swen2.tutorial.presentation.viewmodel.RouteListViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javafx.scene.image.Image;

import javax.script.Bindings;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component
public class RouteListController implements Initializable {


    private static final Logger logger = LoggerFactory.getLogger(RouteListController.class);
    @FXML
    private ListView<String> tourList;
    @FXML
    private ImageView imageView;



    @Autowired
    RouteListViewModel routeListViewModel;

    @Autowired
    private MapQuestService mapQuestService;




    @Override
    public void initialize(URL location, ResourceBundle rb){
        tourList.itemsProperty().bindBidirectional(routeListViewModel.tourListPropertyProperty());
        //imageView.imageProperty().bindBidirectional(routeListViewModel.tourImageProperty());



        tourList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //check if left click
                if(event.getButton()== MouseButton.PRIMARY && event.getClickCount()==1){
                    String selectedTour = tourList.getSelectionModel().getSelectedItem();
                    Tour selectedTourObject = routeListViewModel.getTours().stream().filter(t -> t.getName().equals(selectedTour)).findFirst().orElse(null);
                    if(selectedTourObject != null){
                        String origin = selectedTourObject.getOrigin();
                        String destination = selectedTourObject.getDestination();
                        System.out.println("Selected tour: " + selectedTour + ", Origin: " + origin + ", Destination: " + destination);
                        logger.info("Getting route for origin={}, destination={}", origin, destination);


                        try{
                            Image tourImage = mapQuestService.getImage(origin,destination);
                        //imageView.setImage(tourImage);
                            imageView.setImage(tourImage);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    }
                }
            }
        });
    }




}
