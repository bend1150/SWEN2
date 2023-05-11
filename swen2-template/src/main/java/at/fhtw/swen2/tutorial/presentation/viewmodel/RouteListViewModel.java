package at.fhtw.swen2.tutorial.presentation.viewmodel;

import at.fhtw.swen2.tutorial.presentation.view.TourCreatorController;
import at.fhtw.swen2.tutorial.service.RouteService;
import at.fhtw.swen2.tutorial.service.model.Tour;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static javafx.collections.FXCollections.observableArrayList;

@Component
@Data
public class RouteListViewModel {

    @Autowired
    TourCreatorController tourCreatorController;
    @Autowired
    RouteService routeService;

    @Autowired
    TourInfoViewModel tourInfoViewModel;

    @Autowired
    TourLogInfoViewModel tourLogInfoViewModel;

    //replicates the list on the left of the screen
    public ObservableList<Tour> tourList = FXCollections.observableArrayList();
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
        System.out.println("Deleting index: " + selectedIndex);

        if(selectedIndex == -1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Select a Tour to delete first");
            alert.showAndWait();
            return;
        }

        tourLogInfoViewModel.deleteAllTourLogs();

        long deletedId = tourList.get(selectedIndex).getId();

        routeService.deleteById(deletedId); //delete

        updateTourList();

        updateSelectedIndex(-1);
        tourInfoViewModel.clearInfo();
    }

    public void updateTourList(){
        List<Tour> newTourList = routeService.getRouteList();

        tourList.clear();
        tourList.setAll(newTourList);

        updateSelectedIndex(-1);
    }

    public void updateSelectedIndex(int index){
        listIndex = index;

        //if something was selected --> fetch the data
        if(listIndex == -1){
            return;
        }

        int id = Math.toIntExact(tourList.get(index).getId());


        //get the Tour info into the tour Info/The map
        tourInfoViewModel.updateInfo(tourList.get(index));

        //get the tourLog info into the tourLog table
        tourLogInfoViewModel.showTourLogs(id);
        tourLogInfoViewModel.setSelectedTour(tourList.get(index));
    }

    public void routeCreatorPage(){
        try{
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
            System.out.println(ex);
        }
    }

    public void test(){
        for (Tour t: tourList
        ) {
            System.out.println(t.getId());
        }
    }
}
