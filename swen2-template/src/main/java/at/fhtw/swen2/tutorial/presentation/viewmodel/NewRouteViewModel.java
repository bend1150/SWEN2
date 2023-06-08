package at.fhtw.swen2.tutorial.presentation.viewmodel;

import at.fhtw.swen2.tutorial.service.PersonService;
import at.fhtw.swen2.tutorial.service.RouteService;
import at.fhtw.swen2.tutorial.service.model.Tour;
import at.fhtw.swen2.tutorial.service.model.TourLog;
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
public class NewRouteViewModel {

    private static final Logger logger = LogManager.getLogger(NewRouteViewModel.class);

    @Autowired
    private RouteService routeService;

    @Autowired
    private RouteListViewModel routeListViewModel;

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

    public void saveRoute(){
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


        tour = Tour.builder()
                .name(getName())
                .description(getDescription())
                .origin(getOrigin())
                .destination(getDestination())
                .transportType(getTransport())
                .distance(parseFloat(getDistance()))
                .time(parseFloat(getTime()))
                //.routeInformation(null)
                .build();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Tour>> violations = validator.validate(tour);

        if(!violations.isEmpty()){
            logger.error("Some fields have not been properly filled out");
            return;  //damit nicht erstellt wird
        }

        routeService.addNew(tour);

        routeListViewModel.updateTourList();
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

}
