package at.fhtw.swen2.tutorial.presentation.view;
import at.fhtw.swen2.tutorial.service.MapQuestService;
import at.fhtw.swen2.tutorial.presentation.viewmodel.EditRouteViewModel;
import at.fhtw.swen2.tutorial.presentation.viewmodel.RouteListViewModel;
import at.fhtw.swen2.tutorial.service.model.Tour;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import javax.script.Bindings;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import javafx.event.EventHandler;




@Component
public class RouteListController implements Initializable {
    @FXML
    public ListView<Tour> tourList;
    @FXML
    private ImageView imageView;

    private static final Logger logger = LoggerFactory.getLogger(RouteListController.class);


    @Autowired
    RouteListViewModel routeListViewModel;

    @Autowired
    EditRouteViewModel editRouteViewModel;
    @Autowired
    private MapQuestService mapQuestService;

    public void addRoute(){
        routeListViewModel.routeCreatorPage();
    }

    public void deleteRoute(){
        int selectedRouteIndex = routeListViewModel.getListIndex();
        routeListViewModel.deleteSelected(selectedRouteIndex);
    }

    public void editRoute(){
        int selectedRouteIndex = routeListViewModel.getListIndex();
        if(selectedRouteIndex == -1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Select a Tour to edit first");
            alert.showAndWait();
            return;
        }

        editRouteViewModel.setProperties(tourList.getSelectionModel().getSelectedIndex());

        editRouteViewModel.openDialog();
    }




    @Override
    public void initialize(URL location, ResourceBundle rb){
        tourList.itemsProperty().bindBidirectional(routeListViewModel.tourListPropertyProperty());

        tourList.getSelectionModel().selectedIndexProperty().addListener(
            new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    int index = tourList.getSelectionModel().getSelectedIndex();
                    routeListViewModel.updateSelectedIndex(index);
                }
            }
        );

        //setzt tourList neu, sodass nur die Namen ausgegeben werden, obwohl in der TourList die Tour Objekte stecken
        tourList.setCellFactory(new Callback<ListView<Tour>, ListCell<Tour>>() {
            @Override
            public ListCell<Tour> call(ListView<Tour> param) {
                return new ListCell<>(){
                    @Override
                    public void updateItem(Tour tour, boolean empty){
                        super.updateItem(tour, empty);
                        if(empty || tour == null){
                            setText(null);
                        } else {
                            setText(tour.getName());
                        }
                    }
                };
            }
        });
/*
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
        });*/

        tourList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //check if left click
                if(event.getButton()== MouseButton.PRIMARY && event.getClickCount()==1){
                    Tour selectedTour = tourList.getSelectionModel().getSelectedItem();
                    if (selectedTour != null) {
                        String selectedTourName = selectedTour.getName();
                        String origin = selectedTour.getOrigin();
                        String destination = selectedTour.getDestination();
                        logger.info("Selected tour: " + selectedTourName + ", Origin: " + origin + ", Destination: " + destination);
                        logger.info("Getting route for origin={}, destination={}", origin, destination);

                        try{
                            Image tourImage = mapQuestService.getImage(origin,destination);
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
