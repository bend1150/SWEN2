package at.fhtw.swen2.tutorial.presentation.viewmodel;

import at.fhtw.swen2.tutorial.presentation.view.RouteListController;
import at.fhtw.swen2.tutorial.service.PersonService;
import at.fhtw.swen2.tutorial.service.RouteService;
import at.fhtw.swen2.tutorial.service.model.Tour;
import javafx.beans.InvalidationListener;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.Float.parseFloat;


@Component
public class EditRouteViewModel {

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


    public void updateRoute(Tour selectedTour){

        //delete old
        routeService.deleteById(selectedTour.getId());

        //save new
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

        routeService.addNew(tour);

        routeListViewModel.updateTourList();
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
}