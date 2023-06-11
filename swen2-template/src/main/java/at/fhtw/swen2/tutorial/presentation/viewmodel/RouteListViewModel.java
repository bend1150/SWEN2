package at.fhtw.swen2.tutorial.presentation.viewmodel;

import at.fhtw.swen2.tutorial.presentation.view.TourCreatorController;
import at.fhtw.swen2.tutorial.service.RouteService;
import at.fhtw.swen2.tutorial.service.model.Person;
import at.fhtw.swen2.tutorial.service.model.Tour;
import at.fhtw.swen2.tutorial.service.pdf.PdfReport;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static javafx.collections.FXCollections.observableArrayList;

@Component
@Data
public class RouteListViewModel {

    private static final Logger logger = LogManager.getLogger(PdfReport.class);

    @Autowired
    TourCreatorController tourCreatorController;
    @Autowired
    RouteService routeService;

    @Autowired
    TourInfoViewModel tourInfoViewModel;

    @Autowired
    TourLogInfoViewModel tourLogInfoViewModel;

    //replicates the list on the left of the screen
    private ObservableList<Tour> tourList = FXCollections.observableArrayList();
    private SimpleListProperty tourListProperty = new SimpleListProperty(tourList);

    private int listIndex = -1;

    public Object getTourListProperty() {
        return tourListProperty.get();
    }


    public SimpleListProperty tourListPropertyProperty() {
        return tourListProperty;
    }

    public ObservableList<Tour> getTourList() {
        return tourList;
    }

    public void setTourList(ObservableList<Tour> tourList) {
        this.tourList = tourList;
    }


    public void addTourList(Tour tour){
        tourList.add(tour);
    }


    public void deleteSelected(int selectedIndex){

        try{
            logger.info("Deleting index: " + selectedIndex);
            if(selectedIndex < -1){
                return;
            }

            if(selectedIndex == -1){
                popup();
                return;
            }

            tourLogInfoViewModel.deleteAllTourLogs();

            long deletedId = getTourList().get(selectedIndex).getId();

            routeService.deleteById(deletedId); //delete by id

            updateTourList();

            updateSelectedIndex(-1);
            tourInfoViewModel.clearInfo();
        }
        catch (Exception ex){
            logger.error("Error deleting selected route");
            logger.error(ex);
        }

    }

    public void popup(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Select a Tour to delete first");
        alert.showAndWait();
    }

    public void updateTourList(){           //von DB genomment
        List<Tour> newTourList = routeService.getRouteList();

        tourList.clear();
        tourList.setAll(newTourList);

        updateSelectedIndex(-1);        //setzt ausgewählte index zurück
    }

    public void updateSelectedIndex(int index){
        listIndex = index;

        //if something was selected --> fetch the data
        if(listIndex == -1){
            return;
        }

        int id = Math.toIntExact(tourList.get(index).getId());


        //get the Tour info into the tour Info/The map
        tourInfoViewModel.updateInfo(tourList.get(index));      //info wird angezeigt

        //get the tourLog info into the tourLog table
        tourLogInfoViewModel.showTourLogs(id);                  //Tourlogs werden angezeigt
        tourLogInfoViewModel.setSelectedTour(tourList.get(index));      //set TourLogInfo werden angepasst
    }

    public void routeCreatorPage(){
        try{
            logger.info("opening tour creator page");

            // Load the FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/at/fhtw/swen2/tutorial/presentation/view/TourCreator.fxml"));
            fxmlLoader.setController(tourCreatorController);
            Parent root = fxmlLoader.load();

            // Create a new stage and set the scene
            Stage dialogStage = new Stage();
            dialogStage.setScene(new Scene(root));

            // Set the modality to WINDOW_MODAL
            dialogStage.initModality(Modality.APPLICATION_MODAL);

            // Set the title and show the stage
            dialogStage.setTitle("Custom Dialog");
            dialogStage.showAndWait();
        }
        catch(Exception ex){
            logger.error("Error while opening tour creator page");
            logger.error(ex);
        }
    }

    public void filterList(String searchText){
        if(searchText.isEmpty()){
            updateTourList();
            return;
        }

        List<Tour> filteredList = routeService.filter(searchText);

        tourList.clear();
        tourList.setAll(filteredList);

        updateSelectedIndex(-1);
    }

}