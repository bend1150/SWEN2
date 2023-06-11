package at.fhtw.swen2.tutorial.presentation.viewmodel;

import at.fhtw.swen2.tutorial.service.RouteService;
import at.fhtw.swen2.tutorial.service.impl.RouteServiceImpl;
import at.fhtw.swen2.tutorial.service.model.Tour;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class NewRouteViewModelTest {

    @InjectMocks
    private NewRouteViewModel newRouteViewModel;

    @Mock
    RouteService routeService;

    @Mock
    RouteListViewModel routeListViewModel;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveRoute_validInput_doUpdate() {
        newRouteViewModel.setName("TestRoute");
        newRouteViewModel.setDescription("nice");
        newRouteViewModel.setOrigin("Wien");
        newRouteViewModel.setDestination("Berlin");
        newRouteViewModel.setTransport("Zug");
        newRouteViewModel.setDistance("6");
        newRouteViewModel.setTime("123");

        when(routeService.addNew(any(Tour.class))).thenReturn(null);    //-> return null if addNew() is called
        Mockito.doNothing().when(routeListViewModel).updateTourList();      // do nun if updateTourList() is called

        Tour testTour = newRouteViewModel.saveRoute();

        assertEquals("TestRoute", testTour.getName());
    }

    @Test
    void saveRoute_validInput2_doUpdate() {
        newRouteViewModel.setName("NewRoute");
        newRouteViewModel.setDescription("nice");
        newRouteViewModel.setOrigin("Wien");
        newRouteViewModel.setDestination("Berlin");
        newRouteViewModel.setTransport("Zug");
        newRouteViewModel.setDistance("6");
        newRouteViewModel.setTime("123");

        when(routeService.addNew(any(Tour.class))).thenReturn(null);
        Mockito.doNothing().when(routeListViewModel).updateTourList();

        Tour testTour = newRouteViewModel.saveRoute();

        assertEquals("NewRoute", testTour.getName());
    }

    @Test
    void saveRoute_incompleteInput_noUpdate() {
        newRouteViewModel.setName("TestRoute");
        newRouteViewModel.setDescription("nice");
        newRouteViewModel.setOrigin("Wien");
        newRouteViewModel.setDestination("Berlin");
        newRouteViewModel.setTransport("");
        newRouteViewModel.setDistance("6");
        newRouteViewModel.setTime("123");

        when(routeService.addNew(any(Tour.class))).thenReturn(null);
        Mockito.doNothing().when(routeListViewModel).updateTourList();

        Tour testTour = newRouteViewModel.saveRoute();

        assertNull(testTour);
    }

    @Test
    void saveRoute_incompleteInput2_noUpdate() {
        newRouteViewModel.setName("TestRoute");
        newRouteViewModel.setDescription("nice");
        newRouteViewModel.setOrigin("Wien");
        newRouteViewModel.setDestination("Berlin");
        newRouteViewModel.setTransport("Bus");
        newRouteViewModel.setDistance("6");
        newRouteViewModel.setTime("");

        when(routeService.addNew(any(Tour.class))).thenReturn(null);
        Mockito.doNothing().when(routeListViewModel).updateTourList();

        Tour testTour = newRouteViewModel.saveRoute();

        assertNull(testTour);
    }

    @Test
    void saveRoute_invalidInput_noUpdate() {
        newRouteViewModel.setName("TestRoute");
        newRouteViewModel.setDescription("nice");
        newRouteViewModel.setOrigin("Wien");
        newRouteViewModel.setDestination("Berlin");
        newRouteViewModel.setTransport("Bus");
        newRouteViewModel.setDistance("Hello");
        newRouteViewModel.setTime("123");

        when(routeService.addNew(any(Tour.class))).thenReturn(null);
        Mockito.doNothing().when(routeListViewModel).updateTourList();

        Tour testTour = newRouteViewModel.saveRoute();

        assertNull(testTour);
    }

    @Test
    void saveRoute_invalidInput2_noUpdate() {
        newRouteViewModel.setName("TestRoute");
        newRouteViewModel.setDescription("nice");
        newRouteViewModel.setOrigin("Wien");
        newRouteViewModel.setDestination("Berlin");
        newRouteViewModel.setTransport("Bus");
        newRouteViewModel.setDistance("5");
        newRouteViewModel.setTime("ViertelNach");

        when(routeService.addNew(any(Tour.class))).thenReturn(null);
        Mockito.doNothing().when(routeListViewModel).updateTourList();

        Tour testTour = newRouteViewModel.saveRoute();

        assertNull(testTour);
    }

    @Test
    void cancel() {
        newRouteViewModel.setName("NameInWindow");
        newRouteViewModel.setDescription("NewTestDec");
        newRouteViewModel.setOrigin("Graz");
        newRouteViewModel.setDestination("Wien");
        newRouteViewModel.setDescription("Nett hier");
        newRouteViewModel.setTransport("Zug");
        newRouteViewModel.setDistance("9");
        newRouteViewModel.setTime("10");

        newRouteViewModel.cancel();

        assertNull(newRouteViewModel.getName());
        assertNull(newRouteViewModel.getDescription());
        assertNull(newRouteViewModel.getOrigin());
        assertNull(newRouteViewModel.getDestination());
        assertNull(newRouteViewModel.getDescription());
        assertNull(newRouteViewModel.getTransport());
        assertNull(newRouteViewModel.getDistance());
        assertNull(newRouteViewModel.getTime());
    }
}