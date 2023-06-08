package at.fhtw.swen2.tutorial.presentation.viewmodel;

import at.fhtw.swen2.tutorial.presentation.view.RouteListController;
import at.fhtw.swen2.tutorial.presentation.view.TourEditController;
import at.fhtw.swen2.tutorial.service.PersonService;
import at.fhtw.swen2.tutorial.service.RouteService;
import at.fhtw.swen2.tutorial.service.model.Tour;
import at.fhtw.swen2.tutorial.service.pdf.PdfReport;
import javafx.beans.InvalidationListener;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

import static java.lang.Float.parseFloat;


@Component
public class EditRouteViewModel {
    private static final Logger logger = LogManager.getLogger(PdfReport.class);

    @Autowired
    TourEditController tourEditController;
    @Autowired
    private RouteService routeService;

    @Autowired
    private RouteListViewModel routeListViewModel;

    @Autowired
    TourInfoViewModel tourInfoViewModel;

    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty description = new SimpleStringProperty();
    private SimpleStringProperty origin = new SimpleStringProperty();
    private SimpleStringProperty destination = new SimpleStringProperty();
    private SimpleStringProperty transport = new SimpleStringProperty();
    private SimpleStringProperty distance = new SimpleStringProperty();
    private SimpleStringProperty time = new SimpleStringProperty();



    //dto object, that will be filled
    private Tour tour;

    public String getName() { return name.get(); }
    public StringProperty nameProperty() { return name; }
    public void setName(String name) { this.name.set(name); }

    public String getDescription() { return description.get(); }
    public StringProperty descriptionProperty() { return description; }
    public void setDescription(String description) { this.description.set(description); }

    public String getOrigin() { return origin.get(); }
    public StringProperty originProperty() { return origin; }
    public void setOrigin(String origin) { this.origin.set(origin); }

    public String getDestination() { return destination.get(); }
    public StringProperty destinationProperty() { return destination; }
    public void setDestination(String destination) { this.destination.set(destination); }

    public String getTransport() { return transport.get(); }
    public StringProperty transportProperty() { return transport; }
    public void setTransport(String transport) { this.transport.set(transport); }

    public String getDistance() { return distance.get(); }
    public StringProperty distanceProperty() { return distance; }
    public void setDistance(String distance) { this.distance.set(distance); }

    public String getTime() { return time.get(); }
    public StringProperty timeProperty() { return time; }
    public void setTime(String time) { this.time.set(time); }


    public void updateRoute(Tour selectedTour){
        if(getTime() == null || getDistance() == null){
            logger.error("Some fields have not been properly filled out");
            return;  //damit nicht erstellt wird
        }

        try{
            float floatDistance = parseFloat(getDistance());
            float floatTime = parseFloat(getTime());
        }
        catch(Exception ex){
            logger.error("Time and distance have to be written in numbers");
            return; // wieder falscher input
        }

        Tour backupTour = null;
        try{
            backupTour = selectedTour;
        }
        catch (Exception ex){
            logger.error("Error while updating Tour");
            return;
        }


        try{
            logger.info("updating route");

            selectedTour.setName(getName());
            selectedTour.setDescription(getDescription());
            selectedTour.setOrigin(getOrigin());
            selectedTour.setDestination(getDestination());
            selectedTour.setTransportType(getTransport());
            selectedTour.setDistance(Float.parseFloat(getDistance()));
            selectedTour.setTime(Float.parseFloat(getTime()));

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Tour>> violations = validator.validate(selectedTour);       //schaut notations nach im model -> @NotNull oder @NotBlank

            if(!violations.isEmpty()){
                logger.error("Some fields have not been properly filled out");
                routeListViewModel.updateTourList();        //keine ahnung warum, aber man muss die liste vom db aktualisieren sonst nicht update
                return;  //damit nicht erstellt wird
            }

            //save new
            routeService.update(selectedTour);

            routeListViewModel.updateTourList();
            tourInfoViewModel.updateInfo(selectedTour);
        }
        catch(Exception ex){
            logger.error("Error updating route");
            logger.error(ex);
        }


    }

    public void setProperties(int index){
        long id = routeListViewModel.tourList.get(index).getId();
        Tour tour = routeService.getById(id);

        setName(tour.getName());
        setDescription(tour.getDescription());
        setOrigin(tour.getOrigin());
        setDestination(tour.getDestination());
        setTransport(tour.getTransportType());
        setDistance(Float.toString(tour.getDistance()));
        setTime(Float.toString(tour.getTime()));
        
    }

    public void cancel(){
        //clean the form
        setName(null);
        setDescription(null);
        setOrigin(null);
        setDestination(null);
        setTransport(null);
        setDistance(null);
        setTime(null);
    }

    public void openDialog(){
        try{
            logger.info("opening tour edit window");

            // Load the FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/at/fhtw/swen2/tutorial/presentation/view/TourEditor.fxml"));
            fxmlLoader.setController(tourEditController);
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
            logger.error("Error opening tour edit window");
            logger.error(ex);
        }
    }
}
