package at.fhtw.swen2.tutorial.presentation.viewmodel;
import at.fhtw.swen2.tutorial.service.model.Tour;
import at.fhtw.swen2.tutorial.service.model.TourLog;
import at.fhtw.swen2.tutorial.service.pdf.PdfReport;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class TourInfoViewModel {
    private static final Logger logger = LogManager.getLogger(PdfReport.class);

    @Autowired
    TourLogInfoViewModel tourLogInfoViewModel;

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


    public void updateInfo(Tour tour){
        setName(tour.getName());
        setDescription(tour.getDescription());
        setOrigin(tour.getOrigin());
        setDestination(tour.getDestination());
        setTransport(tour.getTransportType());
        setDistance(Float.toString(tour.getDistance()));
        setTime(Float.toString(tour.getTime()));
    }

    public void clearInfo(){
        setName("");
        setDescription("");
        setOrigin("");
        setDestination("");
        setTransport("");
        setDistance("");
        setTime("");

        tourLogInfoViewModel.setSelectedTour(null);
    }

    public void generateReport(){
        Tour tour = tourLogInfoViewModel.getSelectedTour();
        List<TourLog> tourLogs = tourLogInfoViewModel.getTourLogList();
        //image = ...

        if(tour == null){
            logger.info("tour is null");
            return;
        }
        PdfReport pdfReport = new PdfReport();
        pdfReport.createReport(tour, tourLogs); //pass Image too later
    }
}
