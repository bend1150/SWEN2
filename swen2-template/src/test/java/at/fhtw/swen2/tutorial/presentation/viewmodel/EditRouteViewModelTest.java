package at.fhtw.swen2.tutorial.presentation.viewmodel;

import at.fhtw.swen2.tutorial.persistence.entities.TourEntity;
import at.fhtw.swen2.tutorial.presentation.view.TourEditController;
import at.fhtw.swen2.tutorial.presentation.view.TourInfoController;
import at.fhtw.swen2.tutorial.service.RouteService;
import at.fhtw.swen2.tutorial.service.impl.RouteServiceImpl;
import at.fhtw.swen2.tutorial.service.mapper.TourMapper;
import at.fhtw.swen2.tutorial.service.model.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.aspectj.lang.annotation.Before;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EditRouteViewModelTest {

    @Mock
    RouteServiceImpl routeService = new RouteServiceImpl();

    @InjectMocks
    private EditRouteViewModel editRouteViewModel;

    @Mock
    RouteListViewModel routeListViewModel;

    @Mock
    TourInfoViewModel tourInfoViewModel;


    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }           //Felder mit @Mock werden initialisiert fürn Test


    @Test
    void updateRoute_validInput_updatesTour() {
        Tour selectedTour = Tour.builder()
                .id(0l)
                .name("OldName")
                .description("TestDesc")
                .origin("Wien")
                .destination("Graz")
                .transportType("Bus")
                .distance(3)
                .time(3)
                .build();

        Mockito.doNothing().when(routeListViewModel).updateTourList();
        Mockito.doNothing().when(tourInfoViewModel).updateInfo(selectedTour);

        /*newRouteViewModel.setName("Test");
        newRouteViewModel.setDescription("TestDesc");
        newRouteViewModel.setOrigin("Wien");
        newRouteViewModel.setDestination("Graz");
        newRouteViewModel.setTransport("Bus");*/

        editRouteViewModel.setName("NewName");
        editRouteViewModel.setDescription("NewTestDec");
        editRouteViewModel.setOrigin("Graz");
        editRouteViewModel.setDestination("Wien");
        editRouteViewModel.setDescription("Nett hier");
        editRouteViewModel.setTransport("Zug");
        editRouteViewModel.setDistance("5");
        editRouteViewModel.setTime("10");

        Tour testTour = editRouteViewModel.updateRoute(selectedTour);

        //Tour updatedTour = routeService.getById(0l);

        assertEquals("NewName", testTour.getName());
    }

    @Test
    void updateRoute_validInput2_updatesTour() {
        Tour selectedTour = Tour.builder()
                .id(0l)
                .name("OldName")
                .description("TestDesc")
                .origin("Wien")
                .destination("Graz")
                .transportType("Bus")
                .distance(3)
                .time(3)
                .build();

        Mockito.doNothing().when(routeListViewModel).updateTourList();
        Mockito.doNothing().when(tourInfoViewModel).updateInfo(selectedTour);

        /*newRouteViewModel.setName("Test");
        newRouteViewModel.setDescription("TestDesc");
        newRouteViewModel.setOrigin("Wien");
        newRouteViewModel.setDestination("Graz");
        newRouteViewModel.setTransport("Bus");*/

        editRouteViewModel.setName("TestName");
        editRouteViewModel.setDescription("NewTestDec");
        editRouteViewModel.setOrigin("Graz");
        editRouteViewModel.setDestination("Wien");
        editRouteViewModel.setDescription("Nett hier");
        editRouteViewModel.setTransport("Zug");
        editRouteViewModel.setDistance("13456");
        editRouteViewModel.setTime("10");

        Tour testTour = editRouteViewModel.updateRoute(selectedTour);

        //Tour updatedTour = routeService.getById(0l);

        assertEquals("TestName", testTour.getName());
    }

    @Test
    void updateRoute_incompleteInput_noUpdate() {
        Tour selectedTour = Tour.builder()
                .id(0l)
                .name("OldName")
                .description("TestDesc")
                .origin("Wien")
                .destination("Graz")
                .transportType("Bus")
                .distance(3)
                .time(3)
                .build();

        Mockito.doNothing().when(routeListViewModel).updateTourList();

        /*newRouteViewModel.setName("Test");
        newRouteViewModel.setDescription("TestDesc");
        newRouteViewModel.setOrigin("Wien");
        newRouteViewModel.setDestination("Graz");
        newRouteViewModel.setTransport("Bus");*/

        editRouteViewModel.setName("NewName");
        editRouteViewModel.setDescription("NewTestDec");
        editRouteViewModel.setOrigin("");
        editRouteViewModel.setDestination("Wien");
        editRouteViewModel.setDescription("Nett hier");
        editRouteViewModel.setTransport("Zug");
        editRouteViewModel.setDistance("5");
        editRouteViewModel.setTime("10");

        Tour testTour = editRouteViewModel.updateRoute(selectedTour);

        //Tour updatedTour = routeService.getById(0l);

        assertEquals("OldName", testTour.getName());
    }

    @Test
    void updateRoute_incompleteInput2_noUpdate() {
        Tour selectedTour = Tour.builder()
                .id(0l)
                .name("OldName")
                .description("TestDesc")
                .origin("Wien")
                .destination("Graz")
                .transportType("Bus")
                .distance(3)
                .time(3)
                .build();

        /*newRouteViewModel.setName("Test");
        newRouteViewModel.setDescription("TestDesc");
        newRouteViewModel.setOrigin("Wien");
        newRouteViewModel.setDestination("Graz");
        newRouteViewModel.setTransport("Bus");*/

        editRouteViewModel.setName("NewName");
        editRouteViewModel.setDescription("NewTestDec");
        editRouteViewModel.setOrigin("Wien");
        editRouteViewModel.setDestination("Wien");
        editRouteViewModel.setDescription("Nett hier");
        editRouteViewModel.setTransport("Zug");
        editRouteViewModel.setDistance("");
        editRouteViewModel.setTime("");

        Tour testTour = editRouteViewModel.updateRoute(selectedTour);

        //Tour updatedTour = routeService.getById(0l);

        assertEquals("OldName", testTour.getName());
    }

    @Test
    void updateRoute_wrongInput_noUpdate() {
        Tour selectedTour = Tour.builder()
                .id(0l)
                .name("OldName")
                .description("TestDesc")
                .origin("Wien")
                .destination("Graz")
                .transportType("Bus")
                .distance(3)
                .time(3)
                .build();

        /*newRouteViewModel.setName("Test");
        newRouteViewModel.setDescription("TestDesc");
        newRouteViewModel.setOrigin("Wien");
        newRouteViewModel.setDestination("Graz");
        newRouteViewModel.setTransport("Bus");*/

        editRouteViewModel.setName("NewName");
        editRouteViewModel.setDescription("NewTestDec");
        editRouteViewModel.setOrigin("Graz");
        editRouteViewModel.setDestination("Wien");
        editRouteViewModel.setDescription("Nett hier");
        editRouteViewModel.setTransport("Zug");
        editRouteViewModel.setDistance("high");
        editRouteViewModel.setTime("10");

        Tour testTour = editRouteViewModel.updateRoute(selectedTour);

        //Tour updatedTour = routeService.getById(0l);

        assertEquals("OldName", testTour.getName());
    }

    @Test
    void updateRoute_wrongInput2_noUpdate() {
        Tour selectedTour = Tour.builder()
                .id(0l)
                .name("OldName")
                .description("TestDesc")
                .origin("Wien")
                .destination("Graz")
                .transportType("Bus")
                .distance(3)
                .time(3)
                .build();

        /*newRouteViewModel.setName("Test");
        newRouteViewModel.setDescription("TestDesc");
        newRouteViewModel.setOrigin("Wien");
        newRouteViewModel.setDestination("Graz");
        newRouteViewModel.setTransport("Bus");*/

        editRouteViewModel.setName("NewName");
        editRouteViewModel.setDescription("NewTestDec");
        editRouteViewModel.setOrigin("Graz");
        editRouteViewModel.setDestination("Wien");
        editRouteViewModel.setDescription("Nett hier");
        editRouteViewModel.setTransport("Zug");
        editRouteViewModel.setDistance("3");
        editRouteViewModel.setTime("sers");

        Tour testTour = editRouteViewModel.updateRoute(selectedTour);

        //Tour updatedTour = routeService.getById(0l);

        assertEquals("OldName", testTour.getName());
    }

    @Test
    void setProperties() {
        Tour tour = Tour.builder()
                .id(0l)
                .name("NameInList")
                .description("TestDesc")
                .origin("Wien")
                .destination("Graz")
                .transportType("Bus")
                .distance(3)
                .time(3)
                .build();

        ObservableList<Tour> tourList = FXCollections.observableArrayList();
        tourList.add(tour);

        when(routeListViewModel.getTourList()).thenReturn(tourList);
        when(routeService.getById(0)).thenReturn(tour);

        editRouteViewModel.setName("NameInWindow");
        editRouteViewModel.setDescription("NewTestDec");
        editRouteViewModel.setOrigin("Graz");
        editRouteViewModel.setDestination("Wien");
        editRouteViewModel.setDescription("Nett hier");
        editRouteViewModel.setTransport("Zug");
        editRouteViewModel.setDistance("9");
        editRouteViewModel.setTime("10");

        editRouteViewModel.setProperties(0);

        assertEquals("NameInList", editRouteViewModel.getName());
    }

    @Test
    void cancel() {
        editRouteViewModel.setName("NameInWindow");
        editRouteViewModel.setDescription("NewTestDec");
        editRouteViewModel.setOrigin("Graz");
        editRouteViewModel.setDestination("Wien");
        editRouteViewModel.setDescription("Nett hier");
        editRouteViewModel.setTransport("Zug");
        editRouteViewModel.setDistance("9");
        editRouteViewModel.setTime("10");

        editRouteViewModel.cancel();

        assertEquals(null, editRouteViewModel.getName());
        assertEquals(null, editRouteViewModel.getDescription());
        assertEquals(null, editRouteViewModel.getOrigin());
        assertEquals(null, editRouteViewModel.getDestination());
        assertEquals(null, editRouteViewModel.getDescription());
        assertEquals(null, editRouteViewModel.getTransport());
        assertEquals(null, editRouteViewModel.getDistance());
        assertEquals(null, editRouteViewModel.getTime());

    }
}